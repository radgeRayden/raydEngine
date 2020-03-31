spice member-typeof (T member)
    let anchor = ('anchor `T)
    T as:= type
    member as:= Symbol

    if ((T < Struct) or (T < CStruct))
        for elem in ('elements T)
            let k v = ('keyof elem)
            if (k == member)
                return `v
        hide-traceback;
        error@ anchor "while querying type information"
            .. "field " (repr member) " not found in type " (repr T) "."

    else
        hide-traceback;
        error@ ('anchor `T) "while querying type information" "not a struct type."

run-stage;

inline expand-inline (f args...)
    static-typify
        fn ()
            f args...

fn run-tests ()
    using import struct
    using import enum
    using import testing
    struct A
        A : i32
        B : i32
        C : u64
    test (constant? (member-typeof A 'A))
    test ((member-typeof A 'B) == i32)
    test-compiler-error
        member-typeof A 'doesnt-exist

    # it doesn't make sense to ask for the type of a plain enum field; the type is the enum
    enum B plain
        A
        B
        C
    test-compiler-error
        member-typeof B 'A

static-if main-module?
    run-tests;

do
    let
        member-typeof
        expand-inline
        run-tests
    locals;
