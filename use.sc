# registers the framework directory in the global module search path
let AppSettings = (import radlib.app-settings)
let lib-suffix =
    if AppSettings.ReleaseMode?
        ""
    else
        "_d"

switch operating-system
case 'linux
    load-library "libX11.so"
    load-library (.. module-dir "/foreign/lib/libraydengine" lib-suffix ".so")
    load-library (.. module-dir "/foreign/lib/libglfw.so")
    load-library (.. module-dir "/foreign/lib/libsoloud_x64" lib-suffix ".so")
case 'windows
    load-library (.. module-dir "/foreign/lib/libraydengine" lib-suffix ".dll")
    load-library (.. module-dir "/foreign/lib/glfw.dll")
    load-library (.. module-dir "/foreign/lib/soloud_x64" lib-suffix ".dll")
default
    error "Unsupported OS"

let new-path =
    list
        module-dir .. "/?.sc"
        module-dir .. "/?/init.sc"

'set-symbol package 'path
    package.path .. new-path

(Scope) # require for use in REPL
