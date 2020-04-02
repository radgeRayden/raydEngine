fn remove-prefix (input prefix)
    imply input string
    imply prefix string
    rslice input (countof prefix)

fn join-strings (...)
    let memcpy =
        extern 'llvm.memcpy.p0i8.p0i8.i64
            function void (mutable rawstring) rawstring i64 bool

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
                    "ABC is {ABC}, CDE is {CDE}, and the sum is {(+ ABC CDE)}. This other string is {str}"
                "ABC is 123, CDE is 345, and the sum is 468. This other string is banana"


    ;

static-if main-module?
    run-tests;

do
    let join-strings interpolate remove-prefix
    locals;
