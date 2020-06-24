using import radlib.core-extensions
using import radlib.foreign

# import C functions and sanitize the scope
define-scope glad
    let header =
        include
            options
                .. "-I" module-dir "/include"
            "glad/glad.h"
    using header.extern
    using header.typedef
    using header.define filter "^GL_"
  
let gl = (sanitize-scope glad "^gl[A-Z]")
run-stage;

fn init! ()
    let status = (glad.gladLoadGL)
    if (status == 0)
        let puts = (extern 'puts (function i32 rawstring))
        puts "failed to initialize opengl"
        exit 1

.. gl
    do
        let init!
        locals;
