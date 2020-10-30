let bindgen = (import .scopes-binding-generator.gensc)

let filter-pattern =  "^PHYSFS_(?=[^A-Z])"
vvv bind physfs-header
do
    let header =
        include "../src/physfs/src/physfs.h"
    vvv bind extern
    do
        using header.extern filter filter-pattern
        locals;
    vvv bind typedef
    do
        using header.typedef filter filter-pattern
        locals;
    vvv bind enum
    do
        using header.enum filter filter-pattern
        locals;
    vvv bind struct
    do
        using header.struct filter filter-pattern
        locals;
    vvv bind union
    do
        using header.union filter filter-pattern
        locals;
    vvv bind define
    do
        using header.define filter "^PHYSFS_"
        locals;
    unlet header
    locals;

vvv bind transformers
do
    fn symbol-transformer (sym)
        let match? start end = ('match? filter-pattern sym)
        if match?
            rslice sym end
        else
            sym
    locals;

bindgen.from-include-scope physfs-header transformers
