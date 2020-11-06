typedef RECStruct < CStruct
    inline __== (A B)
        static-if (A == B)
            inline (self other)
                va-map
                    inline (field)
                        let k = (keyof field)
                        let va vb =
                            getattr self k
                            getattr other k
                        va == vb
                    elementsof (storageof A)

    inline __hash (self)
        let fhashes... =
            va-map
                inline (f)
                    let k = (keyof f)
                    let v = (getattr self k)
                    hash v
                elementsof (storageof self)
        let head tail = (va-split 1 fhashes...)
        va-lfold (head)
            inline (__ current computed)
                hash computed current
            (tail)

typedef RECEnum < CEnum
    inline __typecall (cls)
        bitcast 0 cls

do
    let CStruct = RECStruct
    let CEnum = RECEnum
    locals;
