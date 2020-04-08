fn remove-prefix (input prefix)
    imply input string
    imply prefix string
    rslice input (countof prefix)

let memcpy =
    extern 'llvm.memcpy.p0i8.p0i8.i64
        function void (mutable rawstring) rawstring i64 bool

fn join-strings (...)
    static-if ((va-countof ...) == 1)
        let str = ...
        # if only to ensure the "always return new string" invariant.
        # NOTE: At the moment this doesn't do anything except waste
        # time because of string interning, but it will be relevant once
        # globalstrings branch is merged into core.
        return (string (str as rawstring))
    else
        let result-size =
            va-lfold 0
                inline (__ substring size)
                    # typechecking!
                    imply substring string
                    + size (countof substring)
                ...

        using import Array
        local strmem = ((Array i8))
        'resize strmem result-size

        local i = 0:usize
        va-map
            inline (s)
                if ((countof s) == 0)
                    return;
                memcpy
                    & (strmem @ i)     # destination
                    s as rawstring     # source
                    (countof s) as i64 # copy size
                    false
                i += (countof s)
            ...
        string strmem result-size

fn join-strings-array (strings)
    let count = (countof strings)
    assert (count > 0)
    if (count == 1)
        return (string ((strings @ 0) as rawstring))
    local result-size = 0:usize
    for str in strings
        result-size += (countof str)

    using import Array
    using import itertools
    local strmem = ((Array i8))
    'resize strmem result-size

    local i = 0:usize
    for str in strings
        let size = (countof str)
        if (size == 0)
            continue;
        memcpy
            & (strmem @ i)
            str as rawstring
            size as i64
            false
        i += size
    string strmem result-size

sugar interpolate (str)
    str as:= string

    fn any->string (values...)
        # unfortunately it's the only way to deal with varargs that I could think of
        join-strings
            va-map
                inline (value)
                    static-if ((typeof value) == string)
                        value
                    else
                        tostring value
                values...

    let chunks =
        loop (str chunks = str '())
            let match? start end = ('match? "\\$\\{.*?\\}" str) # matches ${...}
            if (not match?)
                break ('reverse (cons str chunks))
            let lhs = (lslice str start)
            let code = (slice str (start + 2) (end - 1)) # cuts off the special chars
            let parsed-code = (sc_parse_from_string code)
            # copy anchor from source string, adding the index as offset
            # NOTE: this doesn't work. Maybe I need to tag everything individually?
            # let parsed-code =
            #     'tag parsed-code
            #         source-anchor
            _
                rslice str end
                cons
                    cons (qq [any->string]) (parsed-code as list)
                    cons lhs chunks
    cons (qq [join-strings]) chunks

# simple string replacement - no pattern
fn... replace (str : string, substring : string, substitution : string)
    using import Array
    let cstring = (include "string.h")
    let cstring = cstring.extern

    # first we walk the string finding matches and recording their positions;
    let rawsource = (str as rawstring)
    local match-positions : (Array usize) # indices of every substring found
    local match-ptr = (cstring.strstr rawsource substring)

    # nothing to replace
    if (match-ptr == null)
        return str

    while (match-ptr != null)
        let relative-index = ((ptrtoint match-ptr usize) - (ptrtoint rawsource usize))
        'append match-positions relative-index
        match-ptr =
            cstring.strstr
                # search starting from just after the last match
                & (rawsource @ (relative-index + (countof substring)))
                substring

    # then we allocate a new string with the computed size and fill it in
    local new-string-mem : (Array i8)
    discard-size := ((countof substring) * (countof match-positions))
    substitution-size := ((countof substitution) * (countof match-positions))
    result-size := ((countof str) - discard-size + substitution-size)

    # edge case: whole string replaced by nothing
    if (result-size == 0)
        return ""
    'resize new-string-mem result-size

    let copy-position source-position =
        fold (copy-position source-position = 0:usize 0:usize) for match-position in match-positions
            # copy non altered string chunk
            copy-size := (match-position - source-position)
            memcpy
                & (new-string-mem @ copy-position)         # destination
                & (rawsource @ source-position)            # source
                copy-size as i64  # amount
                false
            copy-position := (copy-position + copy-size)

            # fixes edge case: substitution is empty, end of string.
            if ((countof substitution) > 0)
                memcpy
                    & (new-string-mem @ copy-position)
                    substitution
                    (countof substitution) as i64
                    false
            _
                (copy-position + (countof substitution))
                (match-position + (countof substring))

    # now we copy the tail of the string in
    copy-size := ((countof str) - source-position)
    if (copy-size > 0) # avoid copy-position being out of bounds
        memcpy
            & (new-string-mem @ copy-position)
            & (rawsource @ source-position)
            copy-size as i64
            false
       
    string new-string-mem result-size

run-stage;

fn run-tests ()
    using import testing
    test
        (remove-prefix "sg_query_buffer_overflow" "sg_") == "query_buffer_overflow"

    do
        inline test-join (expected-output inputs...)
            test
                (join-strings inputs...) == expected-output
            test
                (join-strings-array (local arr = (arrayof string inputs...))) == expected-output

        test-join "abcdefghi" "abc" "def" "ghi"
        test-join "abc" "a" "" "b" "" "c" ""
        test-join "abc" "abc"
        test-join "" ""

    do
        let ABC = 123
        let CDE = 345
        let str = "banana"

        test
            ==
                interpolate
                    "ABC is ${ABC}, CDE is ${CDE}, and the sum is ${(+ ABC CDE)}. This other string is ${str}"
                "ABC is 123, CDE is 345, and the sum is 468. This other string is banana"

        let varargs... = 1 2 3 4
        test
            ==
                interpolate
                    "some ... ${varargs...} for you!"
                "some ... 1234 for you!"

    do
        test
            ==
                replace "abc def ghi jkl " " " "_"
                "abc_def_ghi_jkl_"
        test
            ==
                replace "abc def ghi jkl" "def " "9"
                "abc 9ghi jkl"
        test
            ==
                replace "abcdef" "not in string" "cdef"
                "abcdef"
        test
            ==
                replace "abcdef" "abcdef" ""
                ""
        test
            ==
                replace "0abcdef" "abcdef" ""
                "0"
        test
            ==
                replace "abc000def" "000" ""
                "abcdef"


    ;

static-if main-module?
    run-tests;

do
    let join-strings interpolate remove-prefix replace
    locals;
