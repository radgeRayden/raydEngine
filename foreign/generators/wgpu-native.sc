using import radlib.foreign
let bindgen = (import .scopes-binding-generator.gensc)

# this can be automated by using the runtime Scope functions
vvv bind wgpu-header
do
    let header =
        foreign "../src/wgpu-native/ffi/wgpu.h"
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

    let type-filter = "^WGPU"
    vvv bind typedef
    do
        using header.typedef filter type-filter
        locals;
    vvv bind enum
    do
        using header.enum filter type-filter
        locals;
    vvv bind struct
    do
        using header.struct filter type-filter
        locals;
    vvv bind union
    do
        using header.union filter type-filter
        locals;

    vvv bind define
    do
        using header.define filter type-filter
        locals;

    let extern-filter = "^wgpu_"
    vvv bind extern
    do
        using header.extern filter extern-filter
        locals;

    let define =
        fold (scope = define) for k v in header.extern
            let k = (k as Symbol as string)
            let match? start end = ('match? constant-wrapper-regexp k)
            if match?
                'bind scope (Symbol (rslice k end)) `(v)
            else
                scope

    unlet header
    locals;

vvv bind transformers
do
    fn symbol-transformer (sym)
        let match? start end = ('match? "^(wgpu_|WGPU)" sym)
        if match?
            rslice sym end
        else
            sym
    fn enum-field-transformer (fname)
        let match? start end = ('match? "^WGPU.+?_" fname)
        if match?
            rslice fname end
        else
            fname
    locals;

bindgen.from-include-scope wgpu-header transformers
