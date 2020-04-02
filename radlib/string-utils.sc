fn remove-prefix (input prefix)
    imply input string
    imply prefix string
    rslice input (countof prefix)

let memcpy =
    extern 'llvm.memcpy.p0i8.p0i8.i64
        function void (mutable rawstring) rawstring i64 bool

fn join-strings (...)
    let result-size =
        va-lfold 0
            inline (__ substring size)
                + size (countof substring)
            ...

    using import Array
    local strmem = ((Array i8))
    'resize strmem result-size

    local i = 0
    va-map
        inline (s)
            if ((countof s) == 0)
                return;
            memcpy
                & (strmem @ i)     # destination
                s as rawstring     # source
                (countof s) as i64 # copy size
                false
            i += ((countof s) as i32)
        ...
    string strmem result-size

sugar interpolate (str)
    source-anchor := ('anchor str)
    str as:= string

    fn any->string (value)
        static-if ((typeof value) == string)
            value
        else
            tostring value

    let chunks =
        loop (str chunks = str '())
            let match? start end = ('match? "\\$\\{.+?\\}" str) # matches ${...}
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
fn replace (str substring substitution)
    using import Array
    let cstring = (include "string.h")
    let cstring = cstring.extern

    # first we walk the string finding matches and recording their positions;
    let rawsource = (str as rawstring)
    local match-positions : (Array usize) # indices of every substring found
    local match-ptr = (cstring.strstr rawsource substring)
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
    'resize new-string-mem ((countof str) - discard-size + substitution-size)

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
    memcpy
        & (new-string-mem @ copy-position)
        & (rawsource @ source-position)
        copy-size as i64
        false
       
    string new-string-mem

run-stage;

fn run-tests ()
    using import testing
    test
        (remove-prefix "sg_query_buffer_overflow" "sg_") == "query_buffer_overflow"
    do
        let ABC = 123
        let CDE = 345
        let str = "banana"

        test
            ==
                join-strings "abc" "def" "ghi"
                "abcdefghi"
        test
            ==
                interpolate
                    "ABC is ${ABC}, CDE is ${CDE}, and the sum is ${(+ ABC CDE)}. This other string is ${str}"
                "ABC is 123, CDE is 345, and the sum is 468. This other string is banana"

    do
        test
            ==
                replace "abc def ghi jkl" " " "_"
                "abc_def_ghi_jkl"
        test
            ==
                replace "abc def ghi jkl" "def " "9"
                "abc 9ghi jkl"
    ;

static-if main-module?
    run-tests;

do
    let join-strings interpolate remove-prefix
    locals;
