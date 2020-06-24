let AppSettings = (import radlib.app-settings)
using import radlib.foreign
using import radlib.core-extensions

define-scope wgpu-native
    let header =
        include "include/wgpu.h"
    using header.extern filter "^wgpu_"
    using header.typedef filter "^WGPU"
    using header.define filter "^WGPU"
    locals;

# turn enums into scopes with pruned names
let wgpu-native =
    fold (scope = wgpu-native) for k v in wgpu-native.header.typedef
        v as:= type
        if (('superof v) == CEnum)
            let subscope =
                fold (scope = (Scope)) for k v in ('symbols v)
                    'bind scope k v

            # because of the excess of enums being passed into structs
            'set-symbol v '__typecall
                inline (cls)
                    bitcast 0 cls

            'bind scope k (sanitize-scope subscope "^.+?_")
        else
            scope

sanitize-scope wgpu-native "^(wgpu_|WGPU)"
