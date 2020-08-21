using import radlib.core-extensions
using import radlib.foreign

run-stage;
define-scope volk
    let header =
        foreign "volk/volk.h"
            options (.. "-I" module-dir "/src")
            with-constants
                VK_API_VERSION_1_0
                VK_API_VERSION_1_1
                VK_API_VERSION_1_2
    using header.extern
    using header.enum
    using header.define
    using header.union
    using header.typedef
    using header.struct

sanitize-scope volk "^(vk|Vk)"
