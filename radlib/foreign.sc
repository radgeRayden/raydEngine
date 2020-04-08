using import .core-extensions
using import .string-utils

fn typename->Ctypename (sym)
    let T =
        try
            ('@ (globals) (sym as Symbol))
        else
            sym
    if (('typeof T) == type)
        T as:= type
        if (T < integer)
            signedness := (? ('signed? T) "signed" "unsigned")
            let _type =
                switch ('bitcount T)
                case 8
                    "char"
                case 16
                    "short int"
                case 32
                    "int"
                case 64
                    "long int"
                default
                    error "unsupported type translation"
            (.. signedness " " _type)
        elseif (T < real)
            let _type =
                switch ('bitcount T)
                case 32
                    "float"
                case 64
                    "double"
                default
                    error "unsupported type translation"
            _type
        else
            error "unsupported type translation"
    else
        tostring (T as Symbol)

let wrapper-prefix = "scopes_wrapper__"

fn gen-C-arglist (args)
    argcount := ('argcount args)
    if (argcount == 0)
        ""
    fold (result = "") for i arg in (enumerate ('args args))
        arg as:= string
        last? := (i == (argcount - 1))
        if last?
            result .. arg
        else
            .. result arg ", "

# runtime dependant
fn gen-macro-wrapper-fn (macro args)
    argcount := ('argcount args)
    using import itertools
    let args =
        ->> ('args args)
            map typename->Ctypename
            Value.arglist-sink argcount
    let dummy-values =
        ->> ('args args)
            map
                inline (argT)
                    interpolate "(${argT as string}){0}"
            Value.arglist-sink argcount

    let fn-args =
        ->> (enumerate ('args args))
            map
                inline (i argT)
                    interpolate "${argT as string} arg${i}"
            Value.arglist-sink argcount

    let forwarded =
        ->> (range argcount)
            map
                inline (i)
                    "arg" .. (tostring i)
            Value.arglist-sink argcount

    interpolate
        """"typeof(${macro}(${gen-C-arglist dummy-values}))
            ${wrapper-prefix}${macro} (${gen-C-arglist fn-args}) {
                return ${macro}(${gen-C-arglist forwarded});
            }

fn gen-constant-wrapper-fn (macro)
    interpolate
        """"typeof(${macro}) ${wrapper-prefix}${macro} () {
                return ${macro};
            }

sugar foreign (args...)
    let args result include-args code =
        loop (args result include-args code = args... '() '() "")
            if (empty? args)
                break args result include-args code
            sugar-match args
            case ((header as string) rest...)
                # the \n at the end is to signal to `include` that this is to be compiled
                # NOTE: enveloping the includestr in quotes here is a choice!
                # I can't guarantee it'll always be the correct one, but I haven't seen a good
                # argument that you can't just always use quotes instead of angled brackets.
                incstr := (.. "#include \"" header "\"\n")
                let code =
                    .. incstr code # include string must go first!
                _ rest... result include-args code
            case (('with-constants defines...) rest...)
                let at next = (decons defines...)
                let wrappers =
                    loop (_define rest wrapper-code = at next "")
                        code := (wrapper-code .. (gen-constant-wrapper-fn (_define as Symbol)))
                        if (empty? rest)
                            break code
                        let at next = (decons rest)
                        _ at next code
                # wrappers have to go at the 'bottom' to preserve the includes
                code := (code .. wrappers)
                _ rest... result include-args code
            case (('with-macros macros...) rest...)
                let at next = (decons macros...)
                let wrappers =
                    loop (_define rest wrapper-code = at next "")
                        let code =
                            if (('typeof _define) == list)
                                using import itertools
                                macro := (_define as list)
                                let name args = (decons macro)
                                let arglist =
                                    ->> args (Value.arglist-sink (countof args))

                                wrapper-code .. (gen-macro-wrapper-fn (name as Symbol) arglist)
                            else
                                let arglist = (sc_argument_list_new 0 null)
                                wrapper-code .. (gen-macro-wrapper-fn (_define as Symbol) arglist)

                        if (empty? rest)
                            break code

                        let at next = (decons rest)
                        _ at next code

                _ rest... result include-args (code .. wrappers)
            default
                let at next = (decons args)
                include-args := (cons at include-args)
                _ (next as list) result include-args code
   
    include-args := (cons code include-args)
    _
        qq [(cons 'include include-args)]
        next-expr

# rebinds names in scope without their C prefixes (common in libraries), while
# leaving the original names accessible.
inline sanitize-scope (scope prefix-patterns...)
    fold (scope = scope) for k v in scope
        k as:= Symbol
        key-name := (k as string)

        let new-name =
            va-lfold key-name
                inline (__ignore pattern computed-name)
                    let match? start count = ('match? pattern key-name)
                    if match?
                        # just remove the prefix (we assume pattern always matches start of line)
                        rslice key-name count
                    else
                        computed-name
                prefix-patterns...

        if (new-name == key-name)
            scope
        else
            'bind scope (Symbol new-name) v

locals;

# foreign import sugar
# Motivation: offer a syntax for importing C libraries that takes care of repetitive tasks like
# renaming scopes, 'merging' headers, writing stubs for macros and creating subscopes.
#
# Mockup of what an import could look like:

# foreign
#     # headers are included in the same traslation unit, in order.
#     include "headerA.h" "headerB.h" "headerC.h"
#         # compiler options
#         options "-DHEADERA_IMPLEMENTATION"
#     with-macros
#         # wraps macros and turns them into values, calling with provided arguments if appropriate.
#         # Some macros have no arguments and only return a valua that would be inconvenient to
#         # construct manually, those can be passed as a Symbol instead of a key-value pair.
#         VERSION = (GET_VERSION 1 2 3)
#         PRECOMPUTED_VALUE
#     with-scope libABC
#         # syntax: from [subscopes] matching [match-fn] apply [transformation-fn].
#         # will iterate on all kv pairs in each provided subscope, select the ones that match,
#         # then transform (for example rename the symbol or wrap the value) and append to the
#         # new scope (in this case libABC).
#         # Possible subscopes to be used with "from" are:
#           - extern
#           - define
#           - const
#           - struct
#           - typedef
#           - union
#           - enum
#         from extern matching match-fn apply transformation-fn
#     # NOTE: with scope can be repeated as much as needed, one for each subscope in the resulting
#     # module.
# # The final module scope looks like this:
# let module =
#     foreign ... #etc

# module.libABC
# module.extern # and define, const, etc

# # then on the binding code the user can organize things a bit better:
# vvv bind module =
# do
#     let cmodule =
#         foreign ... # etc
#     # one possibility: just returning one of the submodules
#     libABC
#     # another is to manipulate the scope further, if there is some semantic meaning to have
#     # sub namespaces.
#     do
#         let libABC = cmodule.libABC
#         using cmodule.extern
#         using cmodule.typedef filter "cmod_"
#         locals;
