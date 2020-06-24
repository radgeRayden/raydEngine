using import radlib.foreign
using import radlib.core-extensions
define-scope stbi
    let header =
        include
            options (.. "-I" module-dir "/include")
            "stb_image.h"

    using header.extern filter "^stbi_"

sanitize-scope stbi "^stbi_"
