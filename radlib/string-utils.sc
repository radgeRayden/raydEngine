fn remove-prefix (input prefix)
    imply input string
    imply prefix string
    rslice input (countof prefix)

fn run-tests ()
    using import testing
    test
        (remove-prefix "sg_query_buffer_overflow" "sg_") == "query_buffer_overflow"
    ;

static-if main-module?
    run-tests;

locals;
