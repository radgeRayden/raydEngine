let AppSettings = (import radlib.app-settings)
using import radlib.foreign
using import radlib.core-extensions

define-scope wgpu-native
    let header =
        foreign "include/wgpu.h"
            with-constants
                WGPUTextureUsage_COPY_SRC
                WGPUTextureUsage_COPY_DST
                WGPUTextureUsage_SAMPLED
                WGPUTextureUsage_STORAGE
                WGPUTextureUsage_OUTPUT_ATTACHMENT

                WGPUBufferUsage_MAP_READ
                WGPUBufferUsage_MAP_WRITE
                WGPUBufferUsage_COPY_SRC
                WGPUBufferUsage_COPY_DST
                WGPUBufferUsage_INDEX
                WGPUBufferUsage_VERTEX
                WGPUBufferUsage_UNIFORM
                WGPUBufferUsage_STORAGE
                WGPUBufferUsage_INDIRECT
                
                WGPUShaderStage_NONE
                WGPUShaderStage_VERTEX
                WGPUShaderStage_FRAGMENT
                WGPUShaderStage_COMPUTE

                WGPUColorWrite_RED
                WGPUColorWrite_GREEN
                WGPUColorWrite_BLUE
                WGPUColorWrite_ALPHA
                WGPUColorWrite_COLOR
                WGPUColorWrite_ALL

                # WGPUFeatures_ANISOTROPIC_FILTERING
                WGPUFeatures_MAPPABLE_PRIMARY_BUFFERS
                # WGPUFeatures_TEXTURE_BINDING_ARRAY
                WGPUFeatures_ALL_WEBGPU
                WGPUFeatures_ALL_UNSAFE
                WGPUFeatures_ALL_NATIVE

                WGPUColor_TRANSPARENT
                WGPUColor_BLACK
                WGPUColor_WHITE
                WGPUColor_RED
                WGPUColor_GREEN
                WGPUColor_BLUE

                WGPUOrigin3d_ZERO
                
    using header.extern
    using header.typedef filter "^WGPU"
    using header.define filter "^WGPU"
    locals;

# turn enums into scopes with pruned names
let wgpu-native =
    fold (scope = wgpu-native) for k v in wgpu-native.header.enum
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

let wgpu = (sanitize-scope wgpu-native "^(wgpu_|WGPU)")
# run-stage;
# for k v in wgpu
#     print k v
