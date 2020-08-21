using import radlib.core-extensions
using import radlib.foreign
load-library "./libvolk.so"

define-scope volk
    let header =
        include "volk/volk.h"
    using header.extern
    using header.enum
    using header.define
    using header.union
    using header.typedef
    using header.struct

sanitize-scope volk "^(vk|Vk|VK)"
