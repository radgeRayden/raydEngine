# registers the framework directory in the global module search path
let new-path =
    list
        module-dir .. "/?.sc"
        module-dir .. "/?/init.sc"

'set-symbol package 'path
    package.path .. new-path
;
