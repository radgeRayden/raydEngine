load-library (.. module-dir "/lib/raydengine.so")
let stbi =
    include
        options (.. "-I" module-dir "/include")
        "stb/stb_image.h"

let stbi =
    do
        using stbi.extern filter "^stbi_"
        locals;

fold (scope = stbi) for k v in stbi
    let name = ((k as Symbol) as string)
    if ('match? "^stbi_" name)
        let new-name = (rslice name (countof "stbi_"))
        'bind scope (Symbol new-name) v
    else
        scope
