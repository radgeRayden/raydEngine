using import radlib.core-extensions
using import radlib.foreign

# matches glfw or GLFW if there's no `_` afterwards. This keeps CONSTANTS intact.
let filter-pattern =  "^(glfw|GLFW)(?=[^_])"
run-stage;
define-scope glfw
    let header =
        include
            options "-I./include"
            "GLFW/glfw3.h"
    using header.extern filter filter-pattern
    using header.typedef filter filter-pattern
    using header.define filter "^GLFW_"
    locals;

let glfw = (sanitize-scope glfw filter-pattern)
