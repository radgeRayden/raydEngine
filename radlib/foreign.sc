import .string-utils

sugar foreign (args...)
    fn wrap-macro (name args...)
        let argcount = (va-countof args...)

    fn gen-code (cfilename code opts scope)
        let scope =
            do
                hide-traceback;
                (sc_import_c cfilename code opts scope)
        `scope

    let modulename = (('@ sugar-scope 'module-path) as string)
    loop (args modulename ext opts includestr scope = args... modulename ".c" '() "" (nullof Scope))
        sugar-match args
        case (('using (name as Symbol)) rest...)
            let value = ((sc_expand name '() sugar-scope) as Scope)
            repeat rest... modulename ext opts includestr value
        case (('extern "C++") rest...)
            if (modulename == ".cpp")
                hide-traceback;
                error "duplicate 'extern \"C++\"'"
            repeat rest... modulename ".cpp" opts includestr scope
        case (('options opts...) rest...)
            let opts =
                loop (outopts inopts = '() opts...)
                    if (empty? inopts)
                        break ('reverse outopts)
                    let at next = (decons inopts)
                    let val =
                        do
                            let expr = (sc_expand at '() sugar-scope)
                            sc_prove expr
                    if (('typeof val) != string)
                        error "option arguments must evaluate to constant strings"
                    val as:= string
                    outopts := (cons val outopts)
                    repeat outopts next
            repeat rest... modulename ext opts includestr scope
        case ((s as string) rest...)
            if (not (empty? includestr))
                hide-traceback;
                error "duplicate include string"
            repeat rest... modulename ext opts s scope
        case ()
            let sz = (countof includestr)
            if (sz == 0)
                hide-traceback;
                error "include string is empty"
            let includestr =
                if (includestr @ (sz - 1) == 10:i8)
                    # code block
                    includestr
                else (.. "#include \"" includestr "\"")
            return
                gen-code (.. modulename ext) includestr opts scope
                next-expr
        default
            hide-traceback;
            error (.. "invalid syntax: " (repr args))

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
