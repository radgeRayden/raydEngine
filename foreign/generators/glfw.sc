let bindgen = (import .scopes-binding-generator.gensc)

let filter-pattern =  "^(glfw|GLFW)(?=[^_])"
vvv bind glfw-header
do
    let header =
        include "../src/glfw/include/GLFW/glfw3.h"
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
        using header.define filter "^GLFW_"
        locals;
    unlet header
    locals;

vvv bind transformers
do
    fn symbol-transformer (sym)
        let match? start end = ('match? "^(glfw|GLFW(?!_))" sym)
        if match?
            rslice sym end
        else
            sym
    locals;

bindgen.from-include-scope glfw-header transformers
