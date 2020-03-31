load-library (.. module-dir "/lib/raydengine.so")
# import C functions and sanitize the scope
vvv bind glad
do
    let glad =
        include
            options
                .. "-I" module-dir "/include"
            "glad/glad.h"
    do
        using glad.extern
        using glad.typedef
        using glad.define
        locals;
   
let gl =
    fold (scope = (Scope)) for k v in glad
        let name = ((k as Symbol) as string)
        if ('match? "^(gl[A-Z])" name )
            let new-name = (rslice name (countof "gl"))
            'bind scope (Symbol new-name) v
        elseif ('match? "^GL" name)
            'bind scope k v 
        else scope

run-stage;

fn init! ()
    let status = (glad.gladLoadGL)
    if (status == 0)
        error "failed to initialize openGL"

.. gl
    do
        let init!
        locals;
