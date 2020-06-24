# registers the framework directory in the global module search path
let AppSettings = (import radlib.app-settings)
let lib-suffix =
    if AppSettings.ReleaseMode?
        ""
    else
        "_d"
let lib-extension =
    switch operating-system
    case 'linux
        ".so"
    case 'windows
        ".dll"
    default
        error "Unsupported OS"

load-library (.. module-dir "/foreign/lib/libraydengine" lib-suffix lib-extension)

let new-path =
    list
        module-dir .. "/?.sc"
        module-dir .. "/?/init.sc"

'set-symbol package 'path
    package.path .. new-path
;
