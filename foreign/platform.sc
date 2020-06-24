# headers.sc imports all symbols that must coexist in the same translation unit, to avoid
# redeclarations that would make types incompatible.
let platform-define =
    static-match operating-system
    case 'windows
        "RAYDENGINE_WINDOWS"
    case 'linux
        "RAYDENGINE_LINUX"
    default
        ""
let exports =
    include
        options
            .. "-I" module-dir "/include"
            "-D" .. platform-define
        "include/platform.h"
