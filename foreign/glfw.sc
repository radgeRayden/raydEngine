switch operating-system
case 'linux
    load-library (.. module-dir "/lib/libglfw.so")
case 'windows
    load-library (.. module-dir "/lib/glfw.dll")
pass 'macos
default
    error "Unsupported OS."

using import radlib.core-extensions
using import radlib.foreign

# matches glfw or GLFW if there's no `_` afterwards. This keeps CONSTANTS intact.
let filter-pattern =  "^(glfw|GLFW)(?=[^_])"
run-stage;
define-scope glfw
    import .platform
    using platform.extern filter filter-pattern
    using platform.typedef filter filter-pattern
    using platform.define filter "^GLFW_"
    let X11Display = platform.typedef.X11Display
    locals;

let glfw = (sanitize-scope glfw filter-pattern)
