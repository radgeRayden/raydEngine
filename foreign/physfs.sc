using import radlib.core-extensions
using import radlib.foreign

define-scope physfs
    let header =
        include (options  (.. "-I" module-dir "/include"))
            "physfs.h"
    using header.extern
    using header.typedef
    using header.define
    using header.const

let physfs = (sanitize-scope physfs "^PHYSFS_(?=[^A-Z])")
