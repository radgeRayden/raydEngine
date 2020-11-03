using import .cimport-helpers

typedef SCGenPointer < pointer
    inline __imply (A B)
        inline (self)
            imply (storagecast self) B
    inline __rimply (A B)
        inline (self other)
            imply self (storageof B)

let type-buffer = (alloca-array type 128)
let WGPUShaderSource = (sc_typename_type "WGPUShaderSource" CStruct)
let WGPURasterizationStateDescriptor = (sc_typename_type "WGPURasterizationStateDescriptor" CStruct)
let WGPUBindGroupId = (sc_typename_type "WGPUBindGroupId" integer)
let WGPUDeviceId = (sc_typename_type "WGPUDeviceId" integer)
let WGPUCAdapterInfo = (sc_typename_type "WGPUCAdapterInfo" CStruct)
let WGPUBindGroupLayoutEntry = (sc_typename_type "WGPUBindGroupLayoutEntry" CStruct)
let WGPURenderBundleEncoderDescriptor = (sc_typename_type "WGPURenderBundleEncoderDescriptor" CStruct)
let WGPUCommandEncoderDescriptor = (sc_typename_type "WGPUCommandEncoderDescriptor" CStruct)
let WGPURenderPipelineId = (sc_typename_type "WGPURenderPipelineId" integer)
let WGPURequestAdapterCallback = (sc_typename_type "WGPURequestAdapterCallback" SCGenPointer)
let WGPUBackendBit = (sc_typename_type "WGPUBackendBit" integer)
let WGPUId_SwapChain_Dummy = (sc_typename_type "WGPUId_SwapChain_Dummy" integer)
let WGPUNonZeroU64 = (sc_typename_type "WGPUNonZeroU64" integer)
let WGPURenderPassDepthStencilAttachmentDescriptor = (sc_typename_type "WGPURenderPassDepthStencilAttachmentDescriptor" CStruct)
let WGPUChainedStruct = (sc_typename_type "WGPUChainedStruct" CStruct)
let WGPUId_BindGroup_Dummy = (sc_typename_type "WGPUId_BindGroup_Dummy" integer)
let WGPUTextureDimension = (sc_typename_type "WGPUTextureDimension" CEnum)
let WGPUSwapChainDescriptor = (sc_typename_type "WGPUSwapChainDescriptor" CStruct)
let WGPULogCallback = (sc_typename_type "WGPULogCallback" SCGenPointer)
let WGPUId_Sampler_Dummy = (sc_typename_type "WGPUId_Sampler_Dummy" integer)
let WGPUFilterMode = (sc_typename_type "WGPUFilterMode" CEnum)
let WGPUSwapChainId = (sc_typename_type "WGPUSwapChainId" integer)
let WGPUCDeviceType = (sc_typename_type "WGPUCDeviceType" integer)
let WGPUTextureViewDimension = (sc_typename_type "WGPUTextureViewDimension" CEnum)
let WGPUId_Device_Dummy = (sc_typename_type "WGPUId_Device_Dummy" integer)
let WGPUSwapChainOutput = (sc_typename_type "WGPUSwapChainOutput" CStruct)
let WGPUCommandBufferDescriptor = (sc_typename_type "WGPUCommandBufferDescriptor" CStruct)
let WGPUId_Texture_Dummy = (sc_typename_type "WGPUId_Texture_Dummy" integer)
let WGPULabel = (sc_typename_type "WGPULabel" SCGenPointer)
let WGPUCullMode = (sc_typename_type "WGPUCullMode" CEnum)
let WGPURenderBundleEncoderId = (sc_typename_type "WGPURenderBundleEncoderId" SCGenPointer)
let WGPUStencilStateFaceDescriptor = (sc_typename_type "WGPUStencilStateFaceDescriptor" CStruct)
let WGPUCompareFunction = (sc_typename_type "WGPUCompareFunction" CEnum)
let WGPUQueueId = (sc_typename_type "WGPUQueueId" integer)
let WGPUOption_AdapterId = (sc_typename_type "WGPUOption_AdapterId" integer)
let WGPUTextureCopyView = (sc_typename_type "WGPUTextureCopyView" CStruct)
let WGPUVertexBufferLayoutDescriptor = (sc_typename_type "WGPUVertexBufferLayoutDescriptor" CStruct)
let WGPUOption_SurfaceId = (sc_typename_type "WGPUOption_SurfaceId" integer)
let WGPUBindingType = (sc_typename_type "WGPUBindingType" CEnum)
let WGPUBindGroupDescriptor = (sc_typename_type "WGPUBindGroupDescriptor" CStruct)
let WGPUOption_SamplerId = (sc_typename_type "WGPUOption_SamplerId" integer)
let WGPUDynamicOffset = (sc_typename_type "WGPUDynamicOffset" integer)
let WGPUSamplerDescriptor = (sc_typename_type "WGPUSamplerDescriptor" CStruct)
let WGPUVertexFormat = (sc_typename_type "WGPUVertexFormat" CEnum)
let WGPUInputStepMode = (sc_typename_type "WGPUInputStepMode" CEnum)
let WGPUOption_NonZeroU64 = (sc_typename_type "WGPUOption_NonZeroU64" integer)
let WGPUTextureAspect = (sc_typename_type "WGPUTextureAspect" CEnum)
let WGPURenderPassColorAttachmentDescriptor = (sc_typename_type "WGPURenderPassColorAttachmentDescriptor" CStruct)
let WGPUOption_NonZeroU32 = (sc_typename_type "WGPUOption_NonZeroU32" integer)
let WGPUTextureViewDescriptor = (sc_typename_type "WGPUTextureViewDescriptor" CStruct)
let WGPUSurfaceId = (sc_typename_type "WGPUSurfaceId" integer)
let WGPUTextureViewId = (sc_typename_type "WGPUTextureViewId" integer)
let WGPULoadOp = (sc_typename_type "WGPULoadOp" CEnum)
let WGPURenderPassDescriptor = (sc_typename_type "WGPURenderPassDescriptor" CStruct)
let WGPUCommandBufferId = (sc_typename_type "WGPUCommandBufferId" integer)
let WGPURenderPass = (sc_typename_type "WGPURenderPass" CStruct)
let WGPUBindGroupLayoutId = (sc_typename_type "WGPUBindGroupLayoutId" integer)
let WGPUComputePipelineDescriptor = (sc_typename_type "WGPUComputePipelineDescriptor" CStruct)
let WGPUBindGroupLayoutDescriptor = (sc_typename_type "WGPUBindGroupLayoutDescriptor" CStruct)
let WGPUPassChannel_f32 = (sc_typename_type "WGPUPassChannel_f32" CStruct)
let WGPURenderPassColorAttachmentDescriptorBase_TextureViewId = (sc_typename_type "WGPURenderPassColorAttachmentDescriptorBase_TextureViewId" CStruct)
let WGPUPresentMode = (sc_typename_type "WGPUPresentMode" CEnum)
let WGPUAdapterId = (sc_typename_type "WGPUAdapterId" integer)
let WGPUBindGroupEntry = (sc_typename_type "WGPUBindGroupEntry" CStruct)
let WGPUId_RenderBundle = (sc_typename_type "WGPUId_RenderBundle" integer)
let WGPURawString = (sc_typename_type "WGPURawString" SCGenPointer)
let WGPUTextureId = (sc_typename_type "WGPUTextureId" integer)
let WGPUSwapChainStatus = (sc_typename_type "WGPUSwapChainStatus" CEnum)
let WGPUBufferMapCallback = (sc_typename_type "WGPUBufferMapCallback" SCGenPointer)
let WGPUBlendOperation = (sc_typename_type "WGPUBlendOperation" CEnum)
let WGPUTextureDescriptor = (sc_typename_type "WGPUTextureDescriptor" CStruct)
let WGPUShaderLocation = (sc_typename_type "WGPUShaderLocation" integer)
let WGPUSType = (sc_typename_type "WGPUSType" integer)
let WGPUComputePipelineId = (sc_typename_type "WGPUComputePipelineId" integer)
let WGPUCommandEncoderId = (sc_typename_type "WGPUCommandEncoderId" integer)
let WGPUTextureFormat = (sc_typename_type "WGPUTextureFormat" CEnum)
let WGPUId_Buffer_Dummy = (sc_typename_type "WGPUId_Buffer_Dummy" integer)
let WGPUCDeviceType = (sc_typename_type "WGPUCDeviceType" CEnum)
let WGPUPassChannel_u32 = (sc_typename_type "WGPUPassChannel_u32" CStruct)
let WGPUBackend = (sc_typename_type "WGPUBackend" integer)
let WGPUOption_BufferSize = (sc_typename_type "WGPUOption_BufferSize" integer)
let WGPUStencilOperation = (sc_typename_type "WGPUStencilOperation" CEnum)
let WGPUTextureUsage = (sc_typename_type "WGPUTextureUsage" integer)
let WGPUShaderStage = (sc_typename_type "WGPUShaderStage" integer)
let WGPUBufferMapAsyncStatus = (sc_typename_type "WGPUBufferMapAsyncStatus" CEnum)
let WGPUIndexFormat = (sc_typename_type "WGPUIndexFormat" CEnum)
let WGPUBufferDescriptor = (sc_typename_type "WGPUBufferDescriptor" CStruct)
let WGPUBufferId = (sc_typename_type "WGPUBufferId" integer)
let WGPUBufferAddress = (sc_typename_type "WGPUBufferAddress" integer)
let WGPUVertexAttributeDescriptor = (sc_typename_type "WGPUVertexAttributeDescriptor" CStruct)
let WGPUId_Surface = (sc_typename_type "WGPUId_Surface" integer)
let WGPUPrimitiveTopology = (sc_typename_type "WGPUPrimitiveTopology" CEnum)
let WGPUComputePassDescriptor = (sc_typename_type "WGPUComputePassDescriptor" CStruct)
let WGPUBlendFactor = (sc_typename_type "WGPUBlendFactor" CEnum)
let WGPUId_ComputePipeline_Dummy = (sc_typename_type "WGPUId_ComputePipeline_Dummy" integer)
let WGPULogLevel = (sc_typename_type "WGPULogLevel" CEnum)
let WGPUBlendDescriptor = (sc_typename_type "WGPUBlendDescriptor" CStruct)
let WGPURenderPipelineDescriptor = (sc_typename_type "WGPURenderPipelineDescriptor" CStruct)
let WGPUAddressMode = (sc_typename_type "WGPUAddressMode" CEnum)
let WGPUAnisotropicSamplerDescriptorExt = (sc_typename_type "WGPUAnisotropicSamplerDescriptorExt" CStruct)
let WGPUStoreOp = (sc_typename_type "WGPUStoreOp" CEnum)
let WGPUPipelineLayoutDescriptor = (sc_typename_type "WGPUPipelineLayoutDescriptor" CStruct)
let WGPURequestAdapterOptions = (sc_typename_type "WGPURequestAdapterOptions" CStruct)
let WGPUTextureComponentType = (sc_typename_type "WGPUTextureComponentType" CEnum)
let WGPUBufferUsage = (sc_typename_type "WGPUBufferUsage" integer)
let WGPUId_RenderPipeline_Dummy = (sc_typename_type "WGPUId_RenderPipeline_Dummy" integer)
let WGPUCLimits = (sc_typename_type "WGPUCLimits" CStruct)
let WGPUColor = (sc_typename_type "WGPUColor" CStruct)
let WGPUPassChannel_Color = (sc_typename_type "WGPUPassChannel_Color" CStruct)
let WGPUId_BindGroupLayout_Dummy = (sc_typename_type "WGPUId_BindGroupLayout_Dummy" integer)
let WGPUColorWrite = (sc_typename_type "WGPUColorWrite" integer)
let WGPUId_Adapter_Dummy = (sc_typename_type "WGPUId_Adapter_Dummy" integer)
let WGPUOption_BufferId = (sc_typename_type "WGPUOption_BufferId" integer)
let WGPUSType = (sc_typename_type "WGPUSType" CEnum)
let WGPUOption_TextureViewId = (sc_typename_type "WGPUOption_TextureViewId" integer)
let WGPUComputePass = (sc_typename_type "WGPUComputePass" CStruct)
let WGPUVertexStateDescriptor = (sc_typename_type "WGPUVertexStateDescriptor" CStruct)
let WGPUPipelineLayoutId = (sc_typename_type "WGPUPipelineLayoutId" integer)
let WGPUBufferCopyView = (sc_typename_type "WGPUBufferCopyView" CStruct)
let WGPUId_CommandBuffer_Dummy = (sc_typename_type "WGPUId_CommandBuffer_Dummy" integer)
let WGPUId_TextureView_Dummy = (sc_typename_type "WGPUId_TextureView_Dummy" integer)
let WGPUTextureDataLayout = (sc_typename_type "WGPUTextureDataLayout" CStruct)
let WGPURenderBundleEncoder = (sc_typename_type "WGPURenderBundleEncoder" CStruct)
let WGPUId_PipelineLayout_Dummy = (sc_typename_type "WGPUId_PipelineLayout_Dummy" integer)
let WGPUId_ShaderModule_Dummy = (sc_typename_type "WGPUId_ShaderModule_Dummy" integer)
let WGPUBackend = (sc_typename_type "WGPUBackend" CEnum)
let WGPUProgrammableStageDescriptor = (sc_typename_type "WGPUProgrammableStageDescriptor" CStruct)
let WGPUBindingType = (sc_typename_type "WGPUBindingType" integer)
let WGPUPowerPreference = (sc_typename_type "WGPUPowerPreference" CEnum)
let WGPURenderBundleDescriptor_Label = (sc_typename_type "WGPURenderBundleDescriptor_Label" CStruct)
let WGPURenderBundleId = (sc_typename_type "WGPURenderBundleId" integer)
let WGPUExtent3d = (sc_typename_type "WGPUExtent3d" CStruct)
let WGPUShaderModuleId = (sc_typename_type "WGPUShaderModuleId" integer)
let WGPUOrigin3d = (sc_typename_type "WGPUOrigin3d" CStruct)
let WGPUColorStateDescriptor = (sc_typename_type "WGPUColorStateDescriptor" CStruct)
let WGPUFrontFace = (sc_typename_type "WGPUFrontFace" CEnum)
let WGPURenderPassDepthStencilAttachmentDescriptorBase_TextureViewId = (sc_typename_type "WGPURenderPassDepthStencilAttachmentDescriptorBase_TextureViewId" CStruct)
let WGPUDepthStencilStateDescriptor = (sc_typename_type "WGPUDepthStencilStateDescriptor" CStruct)
let WGPUSamplerId = (sc_typename_type "WGPUSamplerId" integer)
let WGPUFeatures = (sc_typename_type "WGPUFeatures" integer)
let WGPUBufferSize = (sc_typename_type "WGPUBufferSize" integer)
sc_typename_type_set_storage WGPUAddressMode i32 typename-flag-plain
sc_type_set_symbol WGPUAddressMode 'ClampToEdge `(bitcast 0 WGPUAddressMode)
sc_type_set_symbol WGPUAddressMode 'Repeat `(bitcast 1 WGPUAddressMode)
sc_type_set_symbol WGPUAddressMode 'MirrorRepeat `(bitcast 2 WGPUAddressMode)

sc_typename_type_set_storage WGPUBackend i32 typename-flag-plain
sc_type_set_symbol WGPUBackend 'Empty `(bitcast 0 WGPUBackend)
sc_type_set_symbol WGPUBackend 'Vulkan `(bitcast 1 WGPUBackend)
sc_type_set_symbol WGPUBackend 'Metal `(bitcast 2 WGPUBackend)
sc_type_set_symbol WGPUBackend 'Dx12 `(bitcast 3 WGPUBackend)
sc_type_set_symbol WGPUBackend 'Dx11 `(bitcast 4 WGPUBackend)
sc_type_set_symbol WGPUBackend 'Gl `(bitcast 5 WGPUBackend)
sc_type_set_symbol WGPUBackend 'BrowserWebGpu `(bitcast 6 WGPUBackend)

sc_typename_type_set_storage WGPUBindingType i32 typename-flag-plain
sc_type_set_symbol WGPUBindingType 'UniformBuffer `(bitcast 0 WGPUBindingType)
sc_type_set_symbol WGPUBindingType 'StorageBuffer `(bitcast 1 WGPUBindingType)
sc_type_set_symbol WGPUBindingType 'ReadonlyStorageBuffer `(bitcast 2 WGPUBindingType)
sc_type_set_symbol WGPUBindingType 'Sampler `(bitcast 3 WGPUBindingType)
sc_type_set_symbol WGPUBindingType 'ComparisonSampler `(bitcast 4 WGPUBindingType)
sc_type_set_symbol WGPUBindingType 'SampledTexture `(bitcast 5 WGPUBindingType)
sc_type_set_symbol WGPUBindingType 'ReadonlyStorageTexture `(bitcast 6 WGPUBindingType)
sc_type_set_symbol WGPUBindingType 'WriteonlyStorageTexture `(bitcast 7 WGPUBindingType)

sc_typename_type_set_storage WGPUBlendFactor i32 typename-flag-plain
sc_type_set_symbol WGPUBlendFactor 'Zero `(bitcast 0 WGPUBlendFactor)
sc_type_set_symbol WGPUBlendFactor 'One `(bitcast 1 WGPUBlendFactor)
sc_type_set_symbol WGPUBlendFactor 'SrcColor `(bitcast 2 WGPUBlendFactor)
sc_type_set_symbol WGPUBlendFactor 'OneMinusSrcColor `(bitcast 3 WGPUBlendFactor)
sc_type_set_symbol WGPUBlendFactor 'SrcAlpha `(bitcast 4 WGPUBlendFactor)
sc_type_set_symbol WGPUBlendFactor 'OneMinusSrcAlpha `(bitcast 5 WGPUBlendFactor)
sc_type_set_symbol WGPUBlendFactor 'DstColor `(bitcast 6 WGPUBlendFactor)
sc_type_set_symbol WGPUBlendFactor 'OneMinusDstColor `(bitcast 7 WGPUBlendFactor)
sc_type_set_symbol WGPUBlendFactor 'DstAlpha `(bitcast 8 WGPUBlendFactor)
sc_type_set_symbol WGPUBlendFactor 'OneMinusDstAlpha `(bitcast 9 WGPUBlendFactor)
sc_type_set_symbol WGPUBlendFactor 'SrcAlphaSaturated `(bitcast 10 WGPUBlendFactor)
sc_type_set_symbol WGPUBlendFactor 'BlendColor `(bitcast 11 WGPUBlendFactor)
sc_type_set_symbol WGPUBlendFactor 'OneMinusBlendColor `(bitcast 12 WGPUBlendFactor)

sc_typename_type_set_storage WGPUBlendOperation i32 typename-flag-plain
sc_type_set_symbol WGPUBlendOperation 'Add `(bitcast 0 WGPUBlendOperation)
sc_type_set_symbol WGPUBlendOperation 'Subtract `(bitcast 1 WGPUBlendOperation)
sc_type_set_symbol WGPUBlendOperation 'ReverseSubtract `(bitcast 2 WGPUBlendOperation)
sc_type_set_symbol WGPUBlendOperation 'Min `(bitcast 3 WGPUBlendOperation)
sc_type_set_symbol WGPUBlendOperation 'Max `(bitcast 4 WGPUBlendOperation)

sc_typename_type_set_storage WGPUBufferMapAsyncStatus i32 typename-flag-plain
sc_type_set_symbol WGPUBufferMapAsyncStatus 'Success `(bitcast 0 WGPUBufferMapAsyncStatus)
sc_type_set_symbol WGPUBufferMapAsyncStatus 'Error `(bitcast 1 WGPUBufferMapAsyncStatus)
sc_type_set_symbol WGPUBufferMapAsyncStatus 'Unknown `(bitcast 2 WGPUBufferMapAsyncStatus)
sc_type_set_symbol WGPUBufferMapAsyncStatus 'ContextLost `(bitcast 3 WGPUBufferMapAsyncStatus)

sc_typename_type_set_storage WGPUCDeviceType i32 typename-flag-plain
sc_type_set_symbol WGPUCDeviceType 'Other `(bitcast 0 WGPUCDeviceType)
sc_type_set_symbol WGPUCDeviceType 'IntegratedGpu `(bitcast 1 WGPUCDeviceType)
sc_type_set_symbol WGPUCDeviceType 'DiscreteGpu `(bitcast 2 WGPUCDeviceType)
sc_type_set_symbol WGPUCDeviceType 'VirtualGpu `(bitcast 3 WGPUCDeviceType)
sc_type_set_symbol WGPUCDeviceType 'Cpu `(bitcast 4 WGPUCDeviceType)

sc_typename_type_set_storage WGPUCompareFunction i32 typename-flag-plain
sc_type_set_symbol WGPUCompareFunction 'Undefined `(bitcast 0 WGPUCompareFunction)
sc_type_set_symbol WGPUCompareFunction 'Never `(bitcast 1 WGPUCompareFunction)
sc_type_set_symbol WGPUCompareFunction 'Less `(bitcast 2 WGPUCompareFunction)
sc_type_set_symbol WGPUCompareFunction 'Equal `(bitcast 3 WGPUCompareFunction)
sc_type_set_symbol WGPUCompareFunction 'LessEqual `(bitcast 4 WGPUCompareFunction)
sc_type_set_symbol WGPUCompareFunction 'Greater `(bitcast 5 WGPUCompareFunction)
sc_type_set_symbol WGPUCompareFunction 'NotEqual `(bitcast 6 WGPUCompareFunction)
sc_type_set_symbol WGPUCompareFunction 'GreaterEqual `(bitcast 7 WGPUCompareFunction)
sc_type_set_symbol WGPUCompareFunction 'Always `(bitcast 8 WGPUCompareFunction)

sc_typename_type_set_storage WGPUCullMode i32 typename-flag-plain
sc_type_set_symbol WGPUCullMode 'None `(bitcast 0 WGPUCullMode)
sc_type_set_symbol WGPUCullMode 'Front `(bitcast 1 WGPUCullMode)
sc_type_set_symbol WGPUCullMode 'Back `(bitcast 2 WGPUCullMode)

sc_typename_type_set_storage WGPUFilterMode i32 typename-flag-plain
sc_type_set_symbol WGPUFilterMode 'Nearest `(bitcast 0 WGPUFilterMode)
sc_type_set_symbol WGPUFilterMode 'Linear `(bitcast 1 WGPUFilterMode)

sc_typename_type_set_storage WGPUFrontFace i32 typename-flag-plain
sc_type_set_symbol WGPUFrontFace 'Ccw `(bitcast 0 WGPUFrontFace)
sc_type_set_symbol WGPUFrontFace 'Cw `(bitcast 1 WGPUFrontFace)

sc_typename_type_set_storage WGPUIndexFormat i32 typename-flag-plain
sc_type_set_symbol WGPUIndexFormat 'Uint16 `(bitcast 0 WGPUIndexFormat)
sc_type_set_symbol WGPUIndexFormat 'Uint32 `(bitcast 1 WGPUIndexFormat)

sc_typename_type_set_storage WGPUInputStepMode i32 typename-flag-plain
sc_type_set_symbol WGPUInputStepMode 'Vertex `(bitcast 0 WGPUInputStepMode)
sc_type_set_symbol WGPUInputStepMode 'Instance `(bitcast 1 WGPUInputStepMode)

sc_typename_type_set_storage WGPULoadOp i32 typename-flag-plain
sc_type_set_symbol WGPULoadOp 'Clear `(bitcast 0 WGPULoadOp)
sc_type_set_symbol WGPULoadOp 'Load `(bitcast 1 WGPULoadOp)

sc_typename_type_set_storage WGPULogLevel i32 typename-flag-plain
sc_type_set_symbol WGPULogLevel 'Off `(bitcast 0 WGPULogLevel)
sc_type_set_symbol WGPULogLevel 'Error `(bitcast 1 WGPULogLevel)
sc_type_set_symbol WGPULogLevel 'Warn `(bitcast 2 WGPULogLevel)
sc_type_set_symbol WGPULogLevel 'Info `(bitcast 3 WGPULogLevel)
sc_type_set_symbol WGPULogLevel 'Debug `(bitcast 4 WGPULogLevel)
sc_type_set_symbol WGPULogLevel 'Trace `(bitcast 5 WGPULogLevel)

sc_typename_type_set_storage WGPUPowerPreference i32 typename-flag-plain
sc_type_set_symbol WGPUPowerPreference 'Default `(bitcast 0 WGPUPowerPreference)
sc_type_set_symbol WGPUPowerPreference 'LowPower `(bitcast 1 WGPUPowerPreference)
sc_type_set_symbol WGPUPowerPreference 'HighPerformance `(bitcast 2 WGPUPowerPreference)

sc_typename_type_set_storage WGPUPresentMode i32 typename-flag-plain
sc_type_set_symbol WGPUPresentMode 'Immediate `(bitcast 0 WGPUPresentMode)
sc_type_set_symbol WGPUPresentMode 'Mailbox `(bitcast 1 WGPUPresentMode)
sc_type_set_symbol WGPUPresentMode 'Fifo `(bitcast 2 WGPUPresentMode)

sc_typename_type_set_storage WGPUPrimitiveTopology i32 typename-flag-plain
sc_type_set_symbol WGPUPrimitiveTopology 'PointList `(bitcast 0 WGPUPrimitiveTopology)
sc_type_set_symbol WGPUPrimitiveTopology 'LineList `(bitcast 1 WGPUPrimitiveTopology)
sc_type_set_symbol WGPUPrimitiveTopology 'LineStrip `(bitcast 2 WGPUPrimitiveTopology)
sc_type_set_symbol WGPUPrimitiveTopology 'TriangleList `(bitcast 3 WGPUPrimitiveTopology)
sc_type_set_symbol WGPUPrimitiveTopology 'TriangleStrip `(bitcast 4 WGPUPrimitiveTopology)

sc_typename_type_set_storage WGPUSType i32 typename-flag-plain
sc_type_set_symbol WGPUSType 'Invalid `(bitcast 0 WGPUSType)
sc_type_set_symbol WGPUSType 'SurfaceDescriptorFromMetalLayer `(bitcast 1 WGPUSType)
sc_type_set_symbol WGPUSType 'SurfaceDescriptorFromWindowsHWND `(bitcast 2 WGPUSType)
sc_type_set_symbol WGPUSType 'SurfaceDescriptorFromXlib `(bitcast 3 WGPUSType)
sc_type_set_symbol WGPUSType 'SurfaceDescriptorFromHTMLCanvasId `(bitcast 4 WGPUSType)
sc_type_set_symbol WGPUSType 'ShaderModuleSPIRVDescriptor `(bitcast 5 WGPUSType)
sc_type_set_symbol WGPUSType 'ShaderModuleWGSLDescriptor `(bitcast 6 WGPUSType)
sc_type_set_symbol WGPUSType 'AnisotropicFiltering `(bitcast 268435456 WGPUSType)
sc_type_set_symbol WGPUSType 'Force32 `(bitcast 2147483647 WGPUSType)

sc_typename_type_set_storage WGPUStencilOperation i32 typename-flag-plain
sc_type_set_symbol WGPUStencilOperation 'Keep `(bitcast 0 WGPUStencilOperation)
sc_type_set_symbol WGPUStencilOperation 'Zero `(bitcast 1 WGPUStencilOperation)
sc_type_set_symbol WGPUStencilOperation 'Replace `(bitcast 2 WGPUStencilOperation)
sc_type_set_symbol WGPUStencilOperation 'Invert `(bitcast 3 WGPUStencilOperation)
sc_type_set_symbol WGPUStencilOperation 'IncrementClamp `(bitcast 4 WGPUStencilOperation)
sc_type_set_symbol WGPUStencilOperation 'DecrementClamp `(bitcast 5 WGPUStencilOperation)
sc_type_set_symbol WGPUStencilOperation 'IncrementWrap `(bitcast 6 WGPUStencilOperation)
sc_type_set_symbol WGPUStencilOperation 'DecrementWrap `(bitcast 7 WGPUStencilOperation)

sc_typename_type_set_storage WGPUStoreOp i32 typename-flag-plain
sc_type_set_symbol WGPUStoreOp 'Clear `(bitcast 0 WGPUStoreOp)
sc_type_set_symbol WGPUStoreOp 'Store `(bitcast 1 WGPUStoreOp)

sc_typename_type_set_storage WGPUSwapChainStatus i32 typename-flag-plain
sc_type_set_symbol WGPUSwapChainStatus 'Good `(bitcast 0 WGPUSwapChainStatus)
sc_type_set_symbol WGPUSwapChainStatus 'Suboptimal `(bitcast 1 WGPUSwapChainStatus)
sc_type_set_symbol WGPUSwapChainStatus 'Timeout `(bitcast 2 WGPUSwapChainStatus)
sc_type_set_symbol WGPUSwapChainStatus 'Outdated `(bitcast 3 WGPUSwapChainStatus)
sc_type_set_symbol WGPUSwapChainStatus 'Lost `(bitcast 4 WGPUSwapChainStatus)
sc_type_set_symbol WGPUSwapChainStatus 'OutOfMemory `(bitcast 5 WGPUSwapChainStatus)

sc_typename_type_set_storage WGPUTextureAspect i32 typename-flag-plain
sc_type_set_symbol WGPUTextureAspect 'All `(bitcast 0 WGPUTextureAspect)
sc_type_set_symbol WGPUTextureAspect 'StencilOnly `(bitcast 1 WGPUTextureAspect)
sc_type_set_symbol WGPUTextureAspect 'DepthOnly `(bitcast 2 WGPUTextureAspect)

sc_typename_type_set_storage WGPUTextureComponentType i32 typename-flag-plain
sc_type_set_symbol WGPUTextureComponentType 'Float `(bitcast 0 WGPUTextureComponentType)
sc_type_set_symbol WGPUTextureComponentType 'Sint `(bitcast 1 WGPUTextureComponentType)
sc_type_set_symbol WGPUTextureComponentType 'Uint `(bitcast 2 WGPUTextureComponentType)

sc_typename_type_set_storage WGPUTextureDimension i32 typename-flag-plain
sc_type_set_symbol WGPUTextureDimension 'D1 `(bitcast 0 WGPUTextureDimension)
sc_type_set_symbol WGPUTextureDimension 'D2 `(bitcast 1 WGPUTextureDimension)
sc_type_set_symbol WGPUTextureDimension 'D3 `(bitcast 2 WGPUTextureDimension)

sc_typename_type_set_storage WGPUTextureFormat i32 typename-flag-plain
sc_type_set_symbol WGPUTextureFormat 'R8Unorm `(bitcast 0 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'R8Snorm `(bitcast 1 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'R8Uint `(bitcast 2 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'R8Sint `(bitcast 3 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'R16Uint `(bitcast 4 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'R16Sint `(bitcast 5 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'R16Float `(bitcast 6 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'Rg8Unorm `(bitcast 7 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'Rg8Snorm `(bitcast 8 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'Rg8Uint `(bitcast 9 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'Rg8Sint `(bitcast 10 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'R32Uint `(bitcast 11 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'R32Sint `(bitcast 12 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'R32Float `(bitcast 13 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'Rg16Uint `(bitcast 14 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'Rg16Sint `(bitcast 15 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'Rg16Float `(bitcast 16 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'Rgba8Unorm `(bitcast 17 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'Rgba8UnormSrgb `(bitcast 18 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'Rgba8Snorm `(bitcast 19 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'Rgba8Uint `(bitcast 20 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'Rgba8Sint `(bitcast 21 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'Bgra8Unorm `(bitcast 22 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'Bgra8UnormSrgb `(bitcast 23 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'Rgb10a2Unorm `(bitcast 24 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'Rg11b10Float `(bitcast 25 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'Rg32Uint `(bitcast 26 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'Rg32Sint `(bitcast 27 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'Rg32Float `(bitcast 28 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'Rgba16Uint `(bitcast 29 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'Rgba16Sint `(bitcast 30 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'Rgba16Float `(bitcast 31 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'Rgba32Uint `(bitcast 32 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'Rgba32Sint `(bitcast 33 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'Rgba32Float `(bitcast 34 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'Depth32Float `(bitcast 35 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'Depth24Plus `(bitcast 36 WGPUTextureFormat)
sc_type_set_symbol WGPUTextureFormat 'Depth24PlusStencil8 `(bitcast 37 WGPUTextureFormat)

sc_typename_type_set_storage WGPUTextureViewDimension i32 typename-flag-plain
sc_type_set_symbol WGPUTextureViewDimension 'D1 `(bitcast 0 WGPUTextureViewDimension)
sc_type_set_symbol WGPUTextureViewDimension 'D2 `(bitcast 1 WGPUTextureViewDimension)
sc_type_set_symbol WGPUTextureViewDimension 'D2Array `(bitcast 2 WGPUTextureViewDimension)
sc_type_set_symbol WGPUTextureViewDimension 'Cube `(bitcast 3 WGPUTextureViewDimension)
sc_type_set_symbol WGPUTextureViewDimension 'CubeArray `(bitcast 4 WGPUTextureViewDimension)
sc_type_set_symbol WGPUTextureViewDimension 'D3 `(bitcast 5 WGPUTextureViewDimension)

sc_typename_type_set_storage WGPUVertexFormat i32 typename-flag-plain
sc_type_set_symbol WGPUVertexFormat 'Uchar2 `(bitcast 0 WGPUVertexFormat)
sc_type_set_symbol WGPUVertexFormat 'Uchar4 `(bitcast 1 WGPUVertexFormat)
sc_type_set_symbol WGPUVertexFormat 'Char2 `(bitcast 2 WGPUVertexFormat)
sc_type_set_symbol WGPUVertexFormat 'Char4 `(bitcast 3 WGPUVertexFormat)
sc_type_set_symbol WGPUVertexFormat 'Uchar2Norm `(bitcast 4 WGPUVertexFormat)
sc_type_set_symbol WGPUVertexFormat 'Uchar4Norm `(bitcast 5 WGPUVertexFormat)
sc_type_set_symbol WGPUVertexFormat 'Char2Norm `(bitcast 6 WGPUVertexFormat)
sc_type_set_symbol WGPUVertexFormat 'Char4Norm `(bitcast 7 WGPUVertexFormat)
sc_type_set_symbol WGPUVertexFormat 'Ushort2 `(bitcast 8 WGPUVertexFormat)
sc_type_set_symbol WGPUVertexFormat 'Ushort4 `(bitcast 9 WGPUVertexFormat)
sc_type_set_symbol WGPUVertexFormat 'Short2 `(bitcast 10 WGPUVertexFormat)
sc_type_set_symbol WGPUVertexFormat 'Short4 `(bitcast 11 WGPUVertexFormat)
sc_type_set_symbol WGPUVertexFormat 'Ushort2Norm `(bitcast 12 WGPUVertexFormat)
sc_type_set_symbol WGPUVertexFormat 'Ushort4Norm `(bitcast 13 WGPUVertexFormat)
sc_type_set_symbol WGPUVertexFormat 'Short2Norm `(bitcast 14 WGPUVertexFormat)
sc_type_set_symbol WGPUVertexFormat 'Short4Norm `(bitcast 15 WGPUVertexFormat)
sc_type_set_symbol WGPUVertexFormat 'Half2 `(bitcast 16 WGPUVertexFormat)
sc_type_set_symbol WGPUVertexFormat 'Half4 `(bitcast 17 WGPUVertexFormat)
sc_type_set_symbol WGPUVertexFormat 'Float `(bitcast 18 WGPUVertexFormat)
sc_type_set_symbol WGPUVertexFormat 'Float2 `(bitcast 19 WGPUVertexFormat)
sc_type_set_symbol WGPUVertexFormat 'Float3 `(bitcast 20 WGPUVertexFormat)
sc_type_set_symbol WGPUVertexFormat 'Float4 `(bitcast 21 WGPUVertexFormat)
sc_type_set_symbol WGPUVertexFormat 'Uint `(bitcast 22 WGPUVertexFormat)
sc_type_set_symbol WGPUVertexFormat 'Uint2 `(bitcast 23 WGPUVertexFormat)
sc_type_set_symbol WGPUVertexFormat 'Uint3 `(bitcast 24 WGPUVertexFormat)
sc_type_set_symbol WGPUVertexFormat 'Uint4 `(bitcast 25 WGPUVertexFormat)
sc_type_set_symbol WGPUVertexFormat 'Int `(bitcast 26 WGPUVertexFormat)
sc_type_set_symbol WGPUVertexFormat 'Int2 `(bitcast 27 WGPUVertexFormat)
sc_type_set_symbol WGPUVertexFormat 'Int3 `(bitcast 28 WGPUVertexFormat)
sc_type_set_symbol WGPUVertexFormat 'Int4 `(bitcast 29 WGPUVertexFormat)

let _gensc_@<WGPUChainedStruct> = (sc_pointer_type WGPUChainedStruct 2:u64 unnamed)
store (sc_key_type 'next _gensc_@<WGPUChainedStruct>) (getelementptr type-buffer 0)
store (sc_key_type 's_type u32) (getelementptr type-buffer 1)
sc_typename_type_set_storage WGPUChainedStruct (sc_tuple_type 2 type-buffer) typename-flag-plain
sc_typename_type_set_opaque WGPUComputePass
sc_typename_type_set_opaque WGPURenderBundleEncoder
sc_typename_type_set_opaque WGPURenderPass
let _gensc_mutable@<i8> = (sc_pointer_type i8 0:u64 unnamed)
store (sc_key_type 'name _gensc_mutable@<i8>) (getelementptr type-buffer 0)
store (sc_key_type 'name_length u64) (getelementptr type-buffer 1)
store (sc_key_type 'vendor u64) (getelementptr type-buffer 2)
store (sc_key_type 'device u64) (getelementptr type-buffer 3)
store (sc_key_type 'device_type u8) (getelementptr type-buffer 4)
store (sc_key_type 'backend u8) (getelementptr type-buffer 5)
sc_typename_type_set_storage WGPUCAdapterInfo (sc_tuple_type 6 type-buffer) typename-flag-plain
store (sc_key_type 'max_bind_groups u32) (getelementptr type-buffer 0)
sc_typename_type_set_storage WGPUCLimits (sc_tuple_type 1 type-buffer) typename-flag-plain
store (sc_key_type 'todo u32) (getelementptr type-buffer 0)
sc_typename_type_set_storage WGPUComputePassDescriptor (sc_tuple_type 1 type-buffer) typename-flag-plain
store (sc_key_type 'r f64) (getelementptr type-buffer 0)
store (sc_key_type 'g f64) (getelementptr type-buffer 1)
store (sc_key_type 'b f64) (getelementptr type-buffer 2)
store (sc_key_type 'a f64) (getelementptr type-buffer 3)
sc_typename_type_set_storage WGPUColor (sc_tuple_type 4 type-buffer) typename-flag-plain
store (sc_key_type 'load_op WGPULoadOp) (getelementptr type-buffer 0)
store (sc_key_type 'store_op WGPUStoreOp) (getelementptr type-buffer 1)
store (sc_key_type 'clear_value WGPUColor) (getelementptr type-buffer 2)
store (sc_key_type 'read_only bool) (getelementptr type-buffer 3)
sc_typename_type_set_storage WGPUPassChannel_Color (sc_tuple_type 4 type-buffer) typename-flag-plain
store (sc_key_type 'attachment u64) (getelementptr type-buffer 0)
store (sc_key_type 'resolve_target u64) (getelementptr type-buffer 1)
store (sc_key_type 'channel WGPUPassChannel_Color) (getelementptr type-buffer 2)
sc_typename_type_set_storage WGPURenderPassColorAttachmentDescriptorBase_TextureViewId (sc_tuple_type 3 type-buffer) typename-flag-plain
store (sc_key_type 'load_op WGPULoadOp) (getelementptr type-buffer 0)
store (sc_key_type 'store_op WGPUStoreOp) (getelementptr type-buffer 1)
store (sc_key_type 'clear_value f32) (getelementptr type-buffer 2)
store (sc_key_type 'read_only bool) (getelementptr type-buffer 3)
sc_typename_type_set_storage WGPUPassChannel_f32 (sc_tuple_type 4 type-buffer) typename-flag-plain
store (sc_key_type 'load_op WGPULoadOp) (getelementptr type-buffer 0)
store (sc_key_type 'store_op WGPUStoreOp) (getelementptr type-buffer 1)
store (sc_key_type 'clear_value u32) (getelementptr type-buffer 2)
store (sc_key_type 'read_only bool) (getelementptr type-buffer 3)
sc_typename_type_set_storage WGPUPassChannel_u32 (sc_tuple_type 4 type-buffer) typename-flag-plain
store (sc_key_type 'attachment u64) (getelementptr type-buffer 0)
store (sc_key_type 'depth WGPUPassChannel_f32) (getelementptr type-buffer 1)
store (sc_key_type 'stencil WGPUPassChannel_u32) (getelementptr type-buffer 2)
sc_typename_type_set_storage WGPURenderPassDepthStencilAttachmentDescriptorBase_TextureViewId (sc_tuple_type 3 type-buffer) typename-flag-plain
store (sc_key_type 'attachment u64) (getelementptr type-buffer 0)
store (sc_key_type 'resolve_target u64) (getelementptr type-buffer 1)
store (sc_key_type 'channel WGPUPassChannel_Color) (getelementptr type-buffer 2)
sc_typename_type_set_storage WGPURenderPassColorAttachmentDescriptor (sc_tuple_type 3 type-buffer) typename-flag-plain
let _gensc_@<WGPURenderPassColorAttachmentDescriptor> = (sc_pointer_type WGPURenderPassColorAttachmentDescriptor 2:u64 unnamed)
store (sc_key_type 'attachment u64) (getelementptr type-buffer 0)
store (sc_key_type 'depth WGPUPassChannel_f32) (getelementptr type-buffer 1)
store (sc_key_type 'stencil WGPUPassChannel_u32) (getelementptr type-buffer 2)
sc_typename_type_set_storage WGPURenderPassDepthStencilAttachmentDescriptor (sc_tuple_type 3 type-buffer) typename-flag-plain
let _gensc_@<WGPURenderPassDepthStencilAttachmentDescriptor> = (sc_pointer_type WGPURenderPassDepthStencilAttachmentDescriptor 2:u64 unnamed)
store (sc_key_type 'color_attachments _gensc_@<WGPURenderPassColorAttachmentDescriptor>) (getelementptr type-buffer 0)
store (sc_key_type 'color_attachments_length u64) (getelementptr type-buffer 1)
store (sc_key_type 'depth_stencil_attachment _gensc_@<WGPURenderPassDepthStencilAttachmentDescriptor>) (getelementptr type-buffer 2)
sc_typename_type_set_storage WGPURenderPassDescriptor (sc_tuple_type 3 type-buffer) typename-flag-plain
store (sc_key_type 'offset u64) (getelementptr type-buffer 0)
store (sc_key_type 'bytes_per_row u32) (getelementptr type-buffer 1)
store (sc_key_type 'rows_per_image u32) (getelementptr type-buffer 2)
sc_typename_type_set_storage WGPUTextureDataLayout (sc_tuple_type 3 type-buffer) typename-flag-plain
store (sc_key_type 'buffer u64) (getelementptr type-buffer 0)
store (sc_key_type 'layout WGPUTextureDataLayout) (getelementptr type-buffer 1)
sc_typename_type_set_storage WGPUBufferCopyView (sc_tuple_type 2 type-buffer) typename-flag-plain
store (sc_key_type 'x u32) (getelementptr type-buffer 0)
store (sc_key_type 'y u32) (getelementptr type-buffer 1)
store (sc_key_type 'z u32) (getelementptr type-buffer 2)
sc_typename_type_set_storage WGPUOrigin3d (sc_tuple_type 3 type-buffer) typename-flag-plain
store (sc_key_type 'texture u64) (getelementptr type-buffer 0)
store (sc_key_type 'mip_level u32) (getelementptr type-buffer 1)
store (sc_key_type 'origin WGPUOrigin3d) (getelementptr type-buffer 2)
sc_typename_type_set_storage WGPUTextureCopyView (sc_tuple_type 3 type-buffer) typename-flag-plain
store (sc_key_type 'width u32) (getelementptr type-buffer 0)
store (sc_key_type 'height u32) (getelementptr type-buffer 1)
store (sc_key_type 'depth u32) (getelementptr type-buffer 2)
sc_typename_type_set_storage WGPUExtent3d (sc_tuple_type 3 type-buffer) typename-flag-plain
store (sc_key_type 'todo u32) (getelementptr type-buffer 0)
sc_typename_type_set_storage WGPUCommandBufferDescriptor (sc_tuple_type 1 type-buffer) typename-flag-plain
store (sc_key_type 'binding u32) (getelementptr type-buffer 0)
store (sc_key_type 'buffer u64) (getelementptr type-buffer 1)
store (sc_key_type 'offset u64) (getelementptr type-buffer 2)
store (sc_key_type 'size u64) (getelementptr type-buffer 3)
store (sc_key_type 'sampler u64) (getelementptr type-buffer 4)
store (sc_key_type 'texture_view u64) (getelementptr type-buffer 5)
sc_typename_type_set_storage WGPUBindGroupEntry (sc_tuple_type 6 type-buffer) typename-flag-plain
sc_typename_type_set_storage WGPULabel (sc_pointer_type i8 2:u64 unnamed) typename-flag-plain
let _gensc_@<WGPUBindGroupEntry> = (sc_pointer_type WGPUBindGroupEntry 2:u64 unnamed)
store (sc_key_type 'label WGPULabel) (getelementptr type-buffer 0)
store (sc_key_type 'layout u64) (getelementptr type-buffer 1)
store (sc_key_type 'entries _gensc_@<WGPUBindGroupEntry>) (getelementptr type-buffer 2)
store (sc_key_type 'entries_length u64) (getelementptr type-buffer 3)
sc_typename_type_set_storage WGPUBindGroupDescriptor (sc_tuple_type 4 type-buffer) typename-flag-plain
store (sc_key_type 'binding u32) (getelementptr type-buffer 0)
store (sc_key_type 'visibility u32) (getelementptr type-buffer 1)
store (sc_key_type 'ty u32) (getelementptr type-buffer 2)
store (sc_key_type 'has_dynamic_offset bool) (getelementptr type-buffer 3)
store (sc_key_type 'min_buffer_binding_size u64) (getelementptr type-buffer 4)
store (sc_key_type 'multisampled bool) (getelementptr type-buffer 5)
store (sc_key_type 'view_dimension WGPUTextureViewDimension) (getelementptr type-buffer 6)
store (sc_key_type 'texture_component_type WGPUTextureComponentType) (getelementptr type-buffer 7)
store (sc_key_type 'storage_texture_format WGPUTextureFormat) (getelementptr type-buffer 8)
store (sc_key_type 'count u64) (getelementptr type-buffer 9)
sc_typename_type_set_storage WGPUBindGroupLayoutEntry (sc_tuple_type 10 type-buffer) typename-flag-plain
let _gensc_@<WGPUBindGroupLayoutEntry> = (sc_pointer_type WGPUBindGroupLayoutEntry 2:u64 unnamed)
store (sc_key_type 'label WGPULabel) (getelementptr type-buffer 0)
store (sc_key_type 'entries _gensc_@<WGPUBindGroupLayoutEntry>) (getelementptr type-buffer 1)
store (sc_key_type 'entries_length u64) (getelementptr type-buffer 2)
sc_typename_type_set_storage WGPUBindGroupLayoutDescriptor (sc_tuple_type 3 type-buffer) typename-flag-plain
store (sc_key_type 'label WGPULabel) (getelementptr type-buffer 0)
store (sc_key_type 'size u64) (getelementptr type-buffer 1)
store (sc_key_type 'usage u32) (getelementptr type-buffer 2)
store (sc_key_type 'mapped_at_creation bool) (getelementptr type-buffer 3)
sc_typename_type_set_storage WGPUBufferDescriptor (sc_tuple_type 4 type-buffer) typename-flag-plain
store (sc_key_type 'label WGPULabel) (getelementptr type-buffer 0)
sc_typename_type_set_storage WGPUCommandEncoderDescriptor (sc_tuple_type 1 type-buffer) typename-flag-plain
store (sc_key_type 'module u64) (getelementptr type-buffer 0)
store (sc_key_type 'entry_point WGPULabel) (getelementptr type-buffer 1)
sc_typename_type_set_storage WGPUProgrammableStageDescriptor (sc_tuple_type 2 type-buffer) typename-flag-plain
store (sc_key_type 'layout u64) (getelementptr type-buffer 0)
store (sc_key_type 'compute_stage WGPUProgrammableStageDescriptor) (getelementptr type-buffer 1)
sc_typename_type_set_storage WGPUComputePipelineDescriptor (sc_tuple_type 2 type-buffer) typename-flag-plain
let _gensc_@<u64> = (sc_pointer_type u64 2:u64 unnamed)
store (sc_key_type 'bind_group_layouts _gensc_@<u64>) (getelementptr type-buffer 0)
store (sc_key_type 'bind_group_layouts_length u64) (getelementptr type-buffer 1)
sc_typename_type_set_storage WGPUPipelineLayoutDescriptor (sc_tuple_type 2 type-buffer) typename-flag-plain
let _gensc_@<WGPUTextureFormat> = (sc_pointer_type WGPUTextureFormat 2:u64 unnamed)
store (sc_key_type 'label WGPULabel) (getelementptr type-buffer 0)
store (sc_key_type 'color_formats _gensc_@<WGPUTextureFormat>) (getelementptr type-buffer 1)
store (sc_key_type 'color_formats_length u64) (getelementptr type-buffer 2)
store (sc_key_type 'depth_stencil_format _gensc_@<WGPUTextureFormat>) (getelementptr type-buffer 3)
store (sc_key_type 'sample_count u32) (getelementptr type-buffer 4)
sc_typename_type_set_storage WGPURenderBundleEncoderDescriptor (sc_tuple_type 5 type-buffer) typename-flag-plain
store (sc_key_type 'front_face WGPUFrontFace) (getelementptr type-buffer 0)
store (sc_key_type 'cull_mode WGPUCullMode) (getelementptr type-buffer 1)
store (sc_key_type 'depth_bias i32) (getelementptr type-buffer 2)
store (sc_key_type 'depth_bias_slope_scale f32) (getelementptr type-buffer 3)
store (sc_key_type 'depth_bias_clamp f32) (getelementptr type-buffer 4)
sc_typename_type_set_storage WGPURasterizationStateDescriptor (sc_tuple_type 5 type-buffer) typename-flag-plain
store (sc_key_type 'src_factor WGPUBlendFactor) (getelementptr type-buffer 0)
store (sc_key_type 'dst_factor WGPUBlendFactor) (getelementptr type-buffer 1)
store (sc_key_type 'operation WGPUBlendOperation) (getelementptr type-buffer 2)
sc_typename_type_set_storage WGPUBlendDescriptor (sc_tuple_type 3 type-buffer) typename-flag-plain
store (sc_key_type 'format WGPUTextureFormat) (getelementptr type-buffer 0)
store (sc_key_type 'alpha_blend WGPUBlendDescriptor) (getelementptr type-buffer 1)
store (sc_key_type 'color_blend WGPUBlendDescriptor) (getelementptr type-buffer 2)
store (sc_key_type 'write_mask u32) (getelementptr type-buffer 3)
sc_typename_type_set_storage WGPUColorStateDescriptor (sc_tuple_type 4 type-buffer) typename-flag-plain
store (sc_key_type 'compare WGPUCompareFunction) (getelementptr type-buffer 0)
store (sc_key_type 'fail_op WGPUStencilOperation) (getelementptr type-buffer 1)
store (sc_key_type 'depth_fail_op WGPUStencilOperation) (getelementptr type-buffer 2)
store (sc_key_type 'pass_op WGPUStencilOperation) (getelementptr type-buffer 3)
sc_typename_type_set_storage WGPUStencilStateFaceDescriptor (sc_tuple_type 4 type-buffer) typename-flag-plain
store (sc_key_type 'format WGPUTextureFormat) (getelementptr type-buffer 0)
store (sc_key_type 'depth_write_enabled bool) (getelementptr type-buffer 1)
store (sc_key_type 'depth_compare WGPUCompareFunction) (getelementptr type-buffer 2)
store (sc_key_type 'stencil_front WGPUStencilStateFaceDescriptor) (getelementptr type-buffer 3)
store (sc_key_type 'stencil_back WGPUStencilStateFaceDescriptor) (getelementptr type-buffer 4)
store (sc_key_type 'stencil_read_mask u32) (getelementptr type-buffer 5)
store (sc_key_type 'stencil_write_mask u32) (getelementptr type-buffer 6)
sc_typename_type_set_storage WGPUDepthStencilStateDescriptor (sc_tuple_type 7 type-buffer) typename-flag-plain
store (sc_key_type 'offset u64) (getelementptr type-buffer 0)
store (sc_key_type 'format WGPUVertexFormat) (getelementptr type-buffer 1)
store (sc_key_type 'shader_location u32) (getelementptr type-buffer 2)
sc_typename_type_set_storage WGPUVertexAttributeDescriptor (sc_tuple_type 3 type-buffer) typename-flag-plain
let _gensc_@<WGPUVertexAttributeDescriptor> = (sc_pointer_type WGPUVertexAttributeDescriptor 2:u64 unnamed)
store (sc_key_type 'array_stride u64) (getelementptr type-buffer 0)
store (sc_key_type 'step_mode WGPUInputStepMode) (getelementptr type-buffer 1)
store (sc_key_type 'attributes _gensc_@<WGPUVertexAttributeDescriptor>) (getelementptr type-buffer 2)
store (sc_key_type 'attributes_length u64) (getelementptr type-buffer 3)
sc_typename_type_set_storage WGPUVertexBufferLayoutDescriptor (sc_tuple_type 4 type-buffer) typename-flag-plain
let _gensc_@<WGPUVertexBufferLayoutDescriptor> = (sc_pointer_type WGPUVertexBufferLayoutDescriptor 2:u64 unnamed)
store (sc_key_type 'index_format WGPUIndexFormat) (getelementptr type-buffer 0)
store (sc_key_type 'vertex_buffers _gensc_@<WGPUVertexBufferLayoutDescriptor>) (getelementptr type-buffer 1)
store (sc_key_type 'vertex_buffers_length u64) (getelementptr type-buffer 2)
sc_typename_type_set_storage WGPUVertexStateDescriptor (sc_tuple_type 3 type-buffer) typename-flag-plain
let _gensc_@<WGPUProgrammableStageDescriptor> = (sc_pointer_type WGPUProgrammableStageDescriptor 2:u64 unnamed)
let _gensc_@<WGPURasterizationStateDescriptor> = (sc_pointer_type WGPURasterizationStateDescriptor 2:u64 unnamed)
let _gensc_@<WGPUColorStateDescriptor> = (sc_pointer_type WGPUColorStateDescriptor 2:u64 unnamed)
let _gensc_@<WGPUDepthStencilStateDescriptor> = (sc_pointer_type WGPUDepthStencilStateDescriptor 2:u64 unnamed)
store (sc_key_type 'layout u64) (getelementptr type-buffer 0)
store (sc_key_type 'vertex_stage WGPUProgrammableStageDescriptor) (getelementptr type-buffer 1)
store (sc_key_type 'fragment_stage _gensc_@<WGPUProgrammableStageDescriptor>) (getelementptr type-buffer 2)
store (sc_key_type 'primitive_topology WGPUPrimitiveTopology) (getelementptr type-buffer 3)
store (sc_key_type 'rasterization_state _gensc_@<WGPURasterizationStateDescriptor>) (getelementptr type-buffer 4)
store (sc_key_type 'color_states _gensc_@<WGPUColorStateDescriptor>) (getelementptr type-buffer 5)
store (sc_key_type 'color_states_length u64) (getelementptr type-buffer 6)
store (sc_key_type 'depth_stencil_state _gensc_@<WGPUDepthStencilStateDescriptor>) (getelementptr type-buffer 7)
store (sc_key_type 'vertex_state WGPUVertexStateDescriptor) (getelementptr type-buffer 8)
store (sc_key_type 'sample_count u32) (getelementptr type-buffer 9)
store (sc_key_type 'sample_mask u32) (getelementptr type-buffer 10)
store (sc_key_type 'alpha_to_coverage_enabled bool) (getelementptr type-buffer 11)
sc_typename_type_set_storage WGPURenderPipelineDescriptor (sc_tuple_type 12 type-buffer) typename-flag-plain
store (sc_key_type 'next_in_chain _gensc_@<WGPUChainedStruct>) (getelementptr type-buffer 0)
store (sc_key_type 'label WGPULabel) (getelementptr type-buffer 1)
store (sc_key_type 'address_mode_u WGPUAddressMode) (getelementptr type-buffer 2)
store (sc_key_type 'address_mode_v WGPUAddressMode) (getelementptr type-buffer 3)
store (sc_key_type 'address_mode_w WGPUAddressMode) (getelementptr type-buffer 4)
store (sc_key_type 'mag_filter WGPUFilterMode) (getelementptr type-buffer 5)
store (sc_key_type 'min_filter WGPUFilterMode) (getelementptr type-buffer 6)
store (sc_key_type 'mipmap_filter WGPUFilterMode) (getelementptr type-buffer 7)
store (sc_key_type 'lod_min_clamp f32) (getelementptr type-buffer 8)
store (sc_key_type 'lod_max_clamp f32) (getelementptr type-buffer 9)
store (sc_key_type 'compare WGPUCompareFunction) (getelementptr type-buffer 10)
sc_typename_type_set_storage WGPUSamplerDescriptor (sc_tuple_type 11 type-buffer) typename-flag-plain
let _gensc_@<u32> = (sc_pointer_type u32 2:u64 unnamed)
store (sc_key_type 'bytes _gensc_@<u32>) (getelementptr type-buffer 0)
store (sc_key_type 'length u64) (getelementptr type-buffer 1)
sc_typename_type_set_storage WGPUShaderSource (sc_tuple_type 2 type-buffer) typename-flag-plain
store (sc_key_type 'usage u32) (getelementptr type-buffer 0)
store (sc_key_type 'format WGPUTextureFormat) (getelementptr type-buffer 1)
store (sc_key_type 'width u32) (getelementptr type-buffer 2)
store (sc_key_type 'height u32) (getelementptr type-buffer 3)
store (sc_key_type 'present_mode WGPUPresentMode) (getelementptr type-buffer 4)
sc_typename_type_set_storage WGPUSwapChainDescriptor (sc_tuple_type 5 type-buffer) typename-flag-plain
store (sc_key_type 'label WGPULabel) (getelementptr type-buffer 0)
store (sc_key_type 'size WGPUExtent3d) (getelementptr type-buffer 1)
store (sc_key_type 'mip_level_count u32) (getelementptr type-buffer 2)
store (sc_key_type 'sample_count u32) (getelementptr type-buffer 3)
store (sc_key_type 'dimension WGPUTextureDimension) (getelementptr type-buffer 4)
store (sc_key_type 'format WGPUTextureFormat) (getelementptr type-buffer 5)
store (sc_key_type 'usage u32) (getelementptr type-buffer 6)
sc_typename_type_set_storage WGPUTextureDescriptor (sc_tuple_type 7 type-buffer) typename-flag-plain
store (sc_key_type 'label WGPULabel) (getelementptr type-buffer 0)
sc_typename_type_set_storage WGPURenderBundleDescriptor_Label (sc_tuple_type 1 type-buffer) typename-flag-plain
store (sc_key_type 'power_preference WGPUPowerPreference) (getelementptr type-buffer 0)
store (sc_key_type 'compatible_surface u64) (getelementptr type-buffer 1)
sc_typename_type_set_storage WGPURequestAdapterOptions (sc_tuple_type 2 type-buffer) typename-flag-plain
store (sc_key_type 'status WGPUSwapChainStatus) (getelementptr type-buffer 0)
store (sc_key_type 'view_id u64) (getelementptr type-buffer 1)
sc_typename_type_set_storage WGPUSwapChainOutput (sc_tuple_type 2 type-buffer) typename-flag-plain
store (sc_key_type 'label WGPULabel) (getelementptr type-buffer 0)
store (sc_key_type 'format WGPUTextureFormat) (getelementptr type-buffer 1)
store (sc_key_type 'dimension WGPUTextureViewDimension) (getelementptr type-buffer 2)
store (sc_key_type 'aspect WGPUTextureAspect) (getelementptr type-buffer 3)
store (sc_key_type 'base_mip_level u32) (getelementptr type-buffer 4)
store (sc_key_type 'level_count u32) (getelementptr type-buffer 5)
store (sc_key_type 'base_array_layer u32) (getelementptr type-buffer 6)
store (sc_key_type 'array_layer_count u32) (getelementptr type-buffer 7)
sc_typename_type_set_storage WGPUTextureViewDescriptor (sc_tuple_type 8 type-buffer) typename-flag-plain
store (sc_key_type 'next_in_chain _gensc_@<WGPUChainedStruct>) (getelementptr type-buffer 0)
store (sc_key_type 's_type u32) (getelementptr type-buffer 1)
store (sc_key_type 'anisotropic_clamp u8) (getelementptr type-buffer 2)
sc_typename_type_set_storage WGPUAnisotropicSamplerDescriptorExt (sc_tuple_type 3 type-buffer) typename-flag-plain
sc_typename_type_set_storage WGPUNonZeroU64 u64 typename-flag-plain
sc_typename_type_set_storage WGPUOption_NonZeroU32 u64 typename-flag-plain
sc_typename_type_set_storage WGPUOption_NonZeroU64 u64 typename-flag-plain
sc_typename_type_set_storage WGPUOption_AdapterId u64 typename-flag-plain
sc_typename_type_set_storage WGPUOption_BufferId u64 typename-flag-plain
sc_typename_type_set_storage WGPUOption_SamplerId u64 typename-flag-plain
sc_typename_type_set_storage WGPUOption_SurfaceId u64 typename-flag-plain
sc_typename_type_set_storage WGPUOption_TextureViewId u64 typename-flag-plain
sc_typename_type_set_storage WGPUOption_BufferSize u64 typename-flag-plain
sc_typename_type_set_storage WGPUId_Adapter_Dummy u64 typename-flag-plain
sc_typename_type_set_storage WGPUAdapterId u64 typename-flag-plain
sc_typename_type_set_storage WGPUFeatures u64 typename-flag-plain
sc_typename_type_set_storage WGPUId_Device_Dummy u64 typename-flag-plain
sc_typename_type_set_storage WGPUDeviceId u64 typename-flag-plain
sc_typename_type_set_storage WGPUId_BindGroup_Dummy u64 typename-flag-plain
sc_typename_type_set_storage WGPUBindGroupId u64 typename-flag-plain
sc_typename_type_set_storage WGPUId_BindGroupLayout_Dummy u64 typename-flag-plain
sc_typename_type_set_storage WGPUBindGroupLayoutId u64 typename-flag-plain
sc_typename_type_set_storage WGPUId_Buffer_Dummy u64 typename-flag-plain
sc_typename_type_set_storage WGPUBufferId u64 typename-flag-plain
sc_typename_type_set_storage WGPUBufferAddress u64 typename-flag-plain
sc_typename_type_set_storage WGPUBufferSize u64 typename-flag-plain
let _gensc_mutable@<u8> = (sc_pointer_type u8 0:u64 unnamed)
store WGPUBufferMapAsyncStatus (getelementptr type-buffer 0)
store _gensc_mutable@<u8> (getelementptr type-buffer 1)
sc_typename_type_set_storage WGPUBufferMapCallback (sc_pointer_type (sc_function_type void 2 type-buffer) 2:u64 unnamed) typename-flag-plain
sc_typename_type_set_storage WGPUId_CommandBuffer_Dummy u64 typename-flag-plain
sc_typename_type_set_storage WGPUCommandBufferId u64 typename-flag-plain
sc_typename_type_set_storage WGPUCommandEncoderId u64 typename-flag-plain
sc_typename_type_set_storage WGPUId_TextureView_Dummy u64 typename-flag-plain
sc_typename_type_set_storage WGPUTextureViewId u64 typename-flag-plain
sc_typename_type_set_storage WGPUId_Texture_Dummy u64 typename-flag-plain
sc_typename_type_set_storage WGPUTextureId u64 typename-flag-plain
sc_typename_type_set_storage WGPURawString (sc_pointer_type i8 2:u64 unnamed) typename-flag-plain
sc_typename_type_set_storage WGPUDynamicOffset u32 typename-flag-plain
sc_typename_type_set_storage WGPUId_ComputePipeline_Dummy u64 typename-flag-plain
sc_typename_type_set_storage WGPUComputePipelineId u64 typename-flag-plain
sc_typename_type_set_storage WGPUId_Surface u64 typename-flag-plain
sc_typename_type_set_storage WGPUSurfaceId u64 typename-flag-plain
sc_typename_type_set_storage WGPUShaderStage u32 typename-flag-plain
sc_typename_type_set_storage WGPUBufferUsage u32 typename-flag-plain
sc_typename_type_set_storage WGPUId_PipelineLayout_Dummy u64 typename-flag-plain
sc_typename_type_set_storage WGPUPipelineLayoutId u64 typename-flag-plain
sc_typename_type_set_storage WGPUId_ShaderModule_Dummy u64 typename-flag-plain
sc_typename_type_set_storage WGPUShaderModuleId u64 typename-flag-plain
sc_typename_type_set_storage WGPURenderBundleEncoderId (sc_pointer_type WGPURenderBundleEncoder 0:u64 unnamed) typename-flag-plain
sc_typename_type_set_storage WGPUId_RenderPipeline_Dummy u64 typename-flag-plain
sc_typename_type_set_storage WGPURenderPipelineId u64 typename-flag-plain
sc_typename_type_set_storage WGPUColorWrite u32 typename-flag-plain
sc_typename_type_set_storage WGPUShaderLocation u32 typename-flag-plain
sc_typename_type_set_storage WGPUId_Sampler_Dummy u64 typename-flag-plain
sc_typename_type_set_storage WGPUSamplerId u64 typename-flag-plain
sc_typename_type_set_storage WGPUId_SwapChain_Dummy u64 typename-flag-plain
sc_typename_type_set_storage WGPUSwapChainId u64 typename-flag-plain
sc_typename_type_set_storage WGPUTextureUsage u32 typename-flag-plain
sc_typename_type_set_storage WGPUQueueId u64 typename-flag-plain
sc_typename_type_set_storage WGPUId_RenderBundle u64 typename-flag-plain
sc_typename_type_set_storage WGPURenderBundleId u64 typename-flag-plain
sc_typename_type_set_storage WGPUBackendBit u32 typename-flag-plain
let _gensc_@<void> = (sc_pointer_type void 2:u64 unnamed)
store u64 (getelementptr type-buffer 0)
store _gensc_@<void> (getelementptr type-buffer 1)
sc_typename_type_set_storage WGPURequestAdapterCallback (sc_pointer_type (sc_function_type void 2 type-buffer) 2:u64 unnamed) typename-flag-plain
store i32 (getelementptr type-buffer 0)
store WGPULabel (getelementptr type-buffer 1)
sc_typename_type_set_storage WGPULogCallback (sc_pointer_type (sc_function_type void 2 type-buffer) 2:u64 unnamed) typename-flag-plain
let _gensc_mutable@<WGPUCAdapterInfo> = (sc_pointer_type WGPUCAdapterInfo 0:u64 unnamed)
let _gensc_@<WGPUCLimits> = (sc_pointer_type WGPUCLimits 2:u64 unnamed)
let _gensc_mutable@<WGPUComputePass> = (sc_pointer_type WGPUComputePass 0:u64 unnamed)
let _gensc_@<WGPUComputePassDescriptor> = (sc_pointer_type WGPUComputePassDescriptor 2:u64 unnamed)
let _gensc_mutable@<WGPURenderPass> = (sc_pointer_type WGPURenderPass 0:u64 unnamed)
let _gensc_@<WGPURenderPassDescriptor> = (sc_pointer_type WGPURenderPassDescriptor 2:u64 unnamed)
let _gensc_@<WGPUBufferCopyView> = (sc_pointer_type WGPUBufferCopyView 2:u64 unnamed)
let _gensc_@<WGPUTextureCopyView> = (sc_pointer_type WGPUTextureCopyView 2:u64 unnamed)
let _gensc_@<WGPUExtent3d> = (sc_pointer_type WGPUExtent3d 2:u64 unnamed)
let _gensc_@<WGPUCommandBufferDescriptor> = (sc_pointer_type WGPUCommandBufferDescriptor 2:u64 unnamed)
let _gensc_mutable@<_gensc_@<void>> = (sc_pointer_type _gensc_@<void> 0:u64 unnamed)
let _gensc_@<WGPUBindGroupDescriptor> = (sc_pointer_type WGPUBindGroupDescriptor 2:u64 unnamed)
let _gensc_@<WGPUBindGroupLayoutDescriptor> = (sc_pointer_type WGPUBindGroupLayoutDescriptor 2:u64 unnamed)
let _gensc_@<WGPUBufferDescriptor> = (sc_pointer_type WGPUBufferDescriptor 2:u64 unnamed)
let _gensc_@<WGPUCommandEncoderDescriptor> = (sc_pointer_type WGPUCommandEncoderDescriptor 2:u64 unnamed)
let _gensc_@<WGPUComputePipelineDescriptor> = (sc_pointer_type WGPUComputePipelineDescriptor 2:u64 unnamed)
let _gensc_@<WGPUPipelineLayoutDescriptor> = (sc_pointer_type WGPUPipelineLayoutDescriptor 2:u64 unnamed)
let _gensc_@<WGPURenderBundleEncoderDescriptor> = (sc_pointer_type WGPURenderBundleEncoderDescriptor 2:u64 unnamed)
let _gensc_@<WGPURenderPipelineDescriptor> = (sc_pointer_type WGPURenderPipelineDescriptor 2:u64 unnamed)
let _gensc_@<WGPUSamplerDescriptor> = (sc_pointer_type WGPUSamplerDescriptor 2:u64 unnamed)
let _gensc_@<WGPUSwapChainDescriptor> = (sc_pointer_type WGPUSwapChainDescriptor 2:u64 unnamed)
let _gensc_@<WGPUTextureDescriptor> = (sc_pointer_type WGPUTextureDescriptor 2:u64 unnamed)
let _gensc_@<u8> = (sc_pointer_type u8 2:u64 unnamed)
let _gensc_@<WGPUTextureDataLayout> = (sc_pointer_type WGPUTextureDataLayout 2:u64 unnamed)
let _gensc_@<WGPURenderBundleDescriptor_Label> = (sc_pointer_type WGPURenderBundleDescriptor_Label 2:u64 unnamed)
let _gensc_@<WGPUColor> = (sc_pointer_type WGPUColor 2:u64 unnamed)
let _gensc_@<WGPURequestAdapterOptions> = (sc_pointer_type WGPURequestAdapterOptions 2:u64 unnamed)
let _gensc_@<WGPUTextureViewDescriptor> = (sc_pointer_type WGPUTextureViewDescriptor 2:u64 unnamed)
store u64 (getelementptr type-buffer 0)
let wgpu_adapter_destroy = (sc_global_new 'wgpu_adapter_destroy (sc_function_type void 1 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
let wgpu_adapter_features = (sc_global_new 'wgpu_adapter_features (sc_function_type u64 1 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
store _gensc_mutable@<WGPUCAdapterInfo> (getelementptr type-buffer 1)
let wgpu_adapter_get_info = (sc_global_new 'wgpu_adapter_get_info (sc_function_type void 2 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
let wgpu_adapter_limits = (sc_global_new 'wgpu_adapter_limits (sc_function_type WGPUCLimits 1 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
store u64 (getelementptr type-buffer 1)
store _gensc_@<WGPUCLimits> (getelementptr type-buffer 2)
store bool (getelementptr type-buffer 3)
store WGPULabel (getelementptr type-buffer 4)
let wgpu_adapter_request_device = (sc_global_new 'wgpu_adapter_request_device (sc_function_type u64 5 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
let wgpu_bind_group_destroy = (sc_global_new 'wgpu_bind_group_destroy (sc_function_type void 1 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
let wgpu_bind_group_layout_destroy = (sc_global_new 'wgpu_bind_group_layout_destroy (sc_function_type void 1 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
let wgpu_buffer_destroy = (sc_global_new 'wgpu_buffer_destroy (sc_function_type void 1 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
store u64 (getelementptr type-buffer 1)
store u64 (getelementptr type-buffer 2)
let wgpu_buffer_get_mapped_range = (sc_global_new 'wgpu_buffer_get_mapped_range (sc_function_type _gensc_mutable@<u8> 3 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
store u64 (getelementptr type-buffer 1)
store u64 (getelementptr type-buffer 2)
store WGPUBufferMapCallback (getelementptr type-buffer 3)
store _gensc_mutable@<u8> (getelementptr type-buffer 4)
let wgpu_buffer_map_read_async = (sc_global_new 'wgpu_buffer_map_read_async (sc_function_type void 5 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
store u64 (getelementptr type-buffer 1)
store u64 (getelementptr type-buffer 2)
store WGPUBufferMapCallback (getelementptr type-buffer 3)
store _gensc_mutable@<u8> (getelementptr type-buffer 4)
let wgpu_buffer_map_write_async = (sc_global_new 'wgpu_buffer_map_write_async (sc_function_type void 5 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
let wgpu_buffer_unmap = (sc_global_new 'wgpu_buffer_unmap (sc_function_type void 1 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
let wgpu_command_buffer_destroy = (sc_global_new 'wgpu_command_buffer_destroy (sc_function_type void 1 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
store _gensc_@<WGPUComputePassDescriptor> (getelementptr type-buffer 1)
let wgpu_command_encoder_begin_compute_pass = (sc_global_new 'wgpu_command_encoder_begin_compute_pass (sc_function_type _gensc_mutable@<WGPUComputePass> 2 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
store _gensc_@<WGPURenderPassDescriptor> (getelementptr type-buffer 1)
let wgpu_command_encoder_begin_render_pass = (sc_global_new 'wgpu_command_encoder_begin_render_pass (sc_function_type _gensc_mutable@<WGPURenderPass> 2 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
store u64 (getelementptr type-buffer 1)
store u64 (getelementptr type-buffer 2)
store u64 (getelementptr type-buffer 3)
store u64 (getelementptr type-buffer 4)
store u64 (getelementptr type-buffer 5)
let wgpu_command_encoder_copy_buffer_to_buffer = (sc_global_new 'wgpu_command_encoder_copy_buffer_to_buffer (sc_function_type void 6 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
store _gensc_@<WGPUBufferCopyView> (getelementptr type-buffer 1)
store _gensc_@<WGPUTextureCopyView> (getelementptr type-buffer 2)
store _gensc_@<WGPUExtent3d> (getelementptr type-buffer 3)
let wgpu_command_encoder_copy_buffer_to_texture = (sc_global_new 'wgpu_command_encoder_copy_buffer_to_texture (sc_function_type void 4 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
store _gensc_@<WGPUTextureCopyView> (getelementptr type-buffer 1)
store _gensc_@<WGPUBufferCopyView> (getelementptr type-buffer 2)
store _gensc_@<WGPUExtent3d> (getelementptr type-buffer 3)
let wgpu_command_encoder_copy_texture_to_buffer = (sc_global_new 'wgpu_command_encoder_copy_texture_to_buffer (sc_function_type void 4 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
store _gensc_@<WGPUTextureCopyView> (getelementptr type-buffer 1)
store _gensc_@<WGPUTextureCopyView> (getelementptr type-buffer 2)
store _gensc_@<WGPUExtent3d> (getelementptr type-buffer 3)
let wgpu_command_encoder_copy_texture_to_texture = (sc_global_new 'wgpu_command_encoder_copy_texture_to_texture (sc_function_type void 4 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
let wgpu_command_encoder_destroy = (sc_global_new 'wgpu_command_encoder_destroy (sc_function_type void 1 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
store _gensc_@<WGPUCommandBufferDescriptor> (getelementptr type-buffer 1)
let wgpu_command_encoder_finish = (sc_global_new 'wgpu_command_encoder_finish (sc_function_type u64 2 type-buffer) 6 unnamed)
store _gensc_mutable@<WGPUComputePass> (getelementptr type-buffer 0)
let wgpu_compute_pass_destroy = (sc_global_new 'wgpu_compute_pass_destroy (sc_function_type void 1 type-buffer) 6 unnamed)
store _gensc_mutable@<WGPUComputePass> (getelementptr type-buffer 0)
store u32 (getelementptr type-buffer 1)
store u32 (getelementptr type-buffer 2)
store u32 (getelementptr type-buffer 3)
let wgpu_compute_pass_dispatch = (sc_global_new 'wgpu_compute_pass_dispatch (sc_function_type void 4 type-buffer) 6 unnamed)
store _gensc_mutable@<WGPUComputePass> (getelementptr type-buffer 0)
store u64 (getelementptr type-buffer 1)
store u64 (getelementptr type-buffer 2)
let wgpu_compute_pass_dispatch_indirect = (sc_global_new 'wgpu_compute_pass_dispatch_indirect (sc_function_type void 3 type-buffer) 6 unnamed)
store _gensc_mutable@<WGPUComputePass> (getelementptr type-buffer 0)
let wgpu_compute_pass_end_pass = (sc_global_new 'wgpu_compute_pass_end_pass (sc_function_type void 1 type-buffer) 6 unnamed)
store _gensc_mutable@<WGPUComputePass> (getelementptr type-buffer 0)
store WGPULabel (getelementptr type-buffer 1)
store u32 (getelementptr type-buffer 2)
let wgpu_compute_pass_insert_debug_marker = (sc_global_new 'wgpu_compute_pass_insert_debug_marker (sc_function_type void 3 type-buffer) 6 unnamed)
store _gensc_mutable@<WGPUComputePass> (getelementptr type-buffer 0)
let wgpu_compute_pass_pop_debug_group = (sc_global_new 'wgpu_compute_pass_pop_debug_group (sc_function_type void 1 type-buffer) 6 unnamed)
store _gensc_mutable@<WGPUComputePass> (getelementptr type-buffer 0)
store WGPULabel (getelementptr type-buffer 1)
store u32 (getelementptr type-buffer 2)
let wgpu_compute_pass_push_debug_group = (sc_global_new 'wgpu_compute_pass_push_debug_group (sc_function_type void 3 type-buffer) 6 unnamed)
store _gensc_mutable@<WGPUComputePass> (getelementptr type-buffer 0)
store u32 (getelementptr type-buffer 1)
store u64 (getelementptr type-buffer 2)
store _gensc_@<u32> (getelementptr type-buffer 3)
store u64 (getelementptr type-buffer 4)
let wgpu_compute_pass_set_bind_group = (sc_global_new 'wgpu_compute_pass_set_bind_group (sc_function_type void 5 type-buffer) 6 unnamed)
store _gensc_mutable@<WGPUComputePass> (getelementptr type-buffer 0)
store u64 (getelementptr type-buffer 1)
let wgpu_compute_pass_set_pipeline = (sc_global_new 'wgpu_compute_pass_set_pipeline (sc_function_type void 2 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
let wgpu_compute_pipeline_destroy = (sc_global_new 'wgpu_compute_pipeline_destroy (sc_function_type void 1 type-buffer) 6 unnamed)
store _gensc_@<void> (getelementptr type-buffer 0)
let wgpu_create_surface_from_android = (sc_global_new 'wgpu_create_surface_from_android (sc_function_type u64 1 type-buffer) 6 unnamed)
store _gensc_@<void> (getelementptr type-buffer 0)
let wgpu_create_surface_from_metal_layer = (sc_global_new 'wgpu_create_surface_from_metal_layer (sc_function_type u64 1 type-buffer) 6 unnamed)
store _gensc_@<void> (getelementptr type-buffer 0)
store _gensc_@<void> (getelementptr type-buffer 1)
let wgpu_create_surface_from_wayland = (sc_global_new 'wgpu_create_surface_from_wayland (sc_function_type u64 2 type-buffer) 6 unnamed)
store _gensc_@<void> (getelementptr type-buffer 0)
store _gensc_@<void> (getelementptr type-buffer 1)
let wgpu_create_surface_from_windows_hwnd = (sc_global_new 'wgpu_create_surface_from_windows_hwnd (sc_function_type u64 2 type-buffer) 6 unnamed)
store _gensc_mutable@<_gensc_@<void>> (getelementptr type-buffer 0)
store u64 (getelementptr type-buffer 1)
let wgpu_create_surface_from_xlib = (sc_global_new 'wgpu_create_surface_from_xlib (sc_function_type u64 2 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
store _gensc_@<WGPUBindGroupDescriptor> (getelementptr type-buffer 1)
let wgpu_device_create_bind_group = (sc_global_new 'wgpu_device_create_bind_group (sc_function_type u64 2 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
store _gensc_@<WGPUBindGroupLayoutDescriptor> (getelementptr type-buffer 1)
let wgpu_device_create_bind_group_layout = (sc_global_new 'wgpu_device_create_bind_group_layout (sc_function_type u64 2 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
store _gensc_@<WGPUBufferDescriptor> (getelementptr type-buffer 1)
let wgpu_device_create_buffer = (sc_global_new 'wgpu_device_create_buffer (sc_function_type u64 2 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
store _gensc_@<WGPUCommandEncoderDescriptor> (getelementptr type-buffer 1)
let wgpu_device_create_command_encoder = (sc_global_new 'wgpu_device_create_command_encoder (sc_function_type u64 2 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
store _gensc_@<WGPUComputePipelineDescriptor> (getelementptr type-buffer 1)
let wgpu_device_create_compute_pipeline = (sc_global_new 'wgpu_device_create_compute_pipeline (sc_function_type u64 2 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
store _gensc_@<WGPUPipelineLayoutDescriptor> (getelementptr type-buffer 1)
let wgpu_device_create_pipeline_layout = (sc_global_new 'wgpu_device_create_pipeline_layout (sc_function_type u64 2 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
store _gensc_@<WGPURenderBundleEncoderDescriptor> (getelementptr type-buffer 1)
let wgpu_device_create_render_bundle_encoder = (sc_global_new 'wgpu_device_create_render_bundle_encoder (sc_function_type WGPURenderBundleEncoderId 2 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
store _gensc_@<WGPURenderPipelineDescriptor> (getelementptr type-buffer 1)
let wgpu_device_create_render_pipeline = (sc_global_new 'wgpu_device_create_render_pipeline (sc_function_type u64 2 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
store _gensc_@<WGPUSamplerDescriptor> (getelementptr type-buffer 1)
let wgpu_device_create_sampler = (sc_global_new 'wgpu_device_create_sampler (sc_function_type u64 2 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
store WGPUShaderSource (getelementptr type-buffer 1)
let wgpu_device_create_shader_module = (sc_global_new 'wgpu_device_create_shader_module (sc_function_type u64 2 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
store u64 (getelementptr type-buffer 1)
store _gensc_@<WGPUSwapChainDescriptor> (getelementptr type-buffer 2)
let wgpu_device_create_swap_chain = (sc_global_new 'wgpu_device_create_swap_chain (sc_function_type u64 3 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
store _gensc_@<WGPUTextureDescriptor> (getelementptr type-buffer 1)
let wgpu_device_create_texture = (sc_global_new 'wgpu_device_create_texture (sc_function_type u64 2 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
let wgpu_device_destroy = (sc_global_new 'wgpu_device_destroy (sc_function_type void 1 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
let wgpu_device_features = (sc_global_new 'wgpu_device_features (sc_function_type u64 1 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
let wgpu_device_get_default_queue = (sc_global_new 'wgpu_device_get_default_queue (sc_function_type u64 1 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
let wgpu_device_limits = (sc_global_new 'wgpu_device_limits (sc_function_type WGPUCLimits 1 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
store bool (getelementptr type-buffer 1)
let wgpu_device_poll = (sc_global_new 'wgpu_device_poll (sc_function_type void 2 type-buffer) 6 unnamed)
let wgpu_get_version = (sc_global_new 'wgpu_get_version (sc_function_type u32 0 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
let wgpu_pipeline_layout_destroy = (sc_global_new 'wgpu_pipeline_layout_destroy (sc_function_type void 1 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
store _gensc_@<u64> (getelementptr type-buffer 1)
store u64 (getelementptr type-buffer 2)
let wgpu_queue_submit = (sc_global_new 'wgpu_queue_submit (sc_function_type void 3 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
store u64 (getelementptr type-buffer 1)
store u64 (getelementptr type-buffer 2)
store _gensc_@<u8> (getelementptr type-buffer 3)
store u64 (getelementptr type-buffer 4)
let wgpu_queue_write_buffer = (sc_global_new 'wgpu_queue_write_buffer (sc_function_type void 5 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
store _gensc_@<WGPUTextureCopyView> (getelementptr type-buffer 1)
store _gensc_@<u8> (getelementptr type-buffer 2)
store u64 (getelementptr type-buffer 3)
store _gensc_@<WGPUTextureDataLayout> (getelementptr type-buffer 4)
store _gensc_@<WGPUExtent3d> (getelementptr type-buffer 5)
let wgpu_queue_write_texture = (sc_global_new 'wgpu_queue_write_texture (sc_function_type void 6 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
let wgpu_render_bundle_destroy = (sc_global_new 'wgpu_render_bundle_destroy (sc_function_type void 1 type-buffer) 6 unnamed)
store WGPURenderBundleEncoderId (getelementptr type-buffer 0)
store u32 (getelementptr type-buffer 1)
store u32 (getelementptr type-buffer 2)
store u32 (getelementptr type-buffer 3)
store u32 (getelementptr type-buffer 4)
let wgpu_render_bundle_draw = (sc_global_new 'wgpu_render_bundle_draw (sc_function_type void 5 type-buffer) 6 unnamed)
store WGPURenderBundleEncoderId (getelementptr type-buffer 0)
store u32 (getelementptr type-buffer 1)
store u32 (getelementptr type-buffer 2)
store u32 (getelementptr type-buffer 3)
store i32 (getelementptr type-buffer 4)
store u32 (getelementptr type-buffer 5)
let wgpu_render_bundle_draw_indexed = (sc_global_new 'wgpu_render_bundle_draw_indexed (sc_function_type void 6 type-buffer) 6 unnamed)
store WGPURenderBundleEncoderId (getelementptr type-buffer 0)
store u64 (getelementptr type-buffer 1)
store u64 (getelementptr type-buffer 2)
let wgpu_render_bundle_draw_indirect = (sc_global_new 'wgpu_render_bundle_draw_indirect (sc_function_type void 3 type-buffer) 6 unnamed)
store WGPURenderBundleEncoderId (getelementptr type-buffer 0)
store _gensc_@<WGPURenderBundleDescriptor_Label> (getelementptr type-buffer 1)
let wgpu_render_bundle_encoder_finish = (sc_global_new 'wgpu_render_bundle_encoder_finish (sc_function_type u64 2 type-buffer) 6 unnamed)
store WGPURenderBundleEncoderId (getelementptr type-buffer 0)
store WGPULabel (getelementptr type-buffer 1)
let wgpu_render_bundle_insert_debug_marker = (sc_global_new 'wgpu_render_bundle_insert_debug_marker (sc_function_type void 2 type-buffer) 6 unnamed)
store WGPURenderBundleEncoderId (getelementptr type-buffer 0)
let wgpu_render_bundle_pop_debug_group = (sc_global_new 'wgpu_render_bundle_pop_debug_group (sc_function_type void 1 type-buffer) 6 unnamed)
store WGPURenderBundleEncoderId (getelementptr type-buffer 0)
store WGPULabel (getelementptr type-buffer 1)
let wgpu_render_bundle_push_debug_group = (sc_global_new 'wgpu_render_bundle_push_debug_group (sc_function_type void 2 type-buffer) 6 unnamed)
store WGPURenderBundleEncoderId (getelementptr type-buffer 0)
store u32 (getelementptr type-buffer 1)
store u64 (getelementptr type-buffer 2)
store _gensc_@<u32> (getelementptr type-buffer 3)
store u64 (getelementptr type-buffer 4)
let wgpu_render_bundle_set_bind_group = (sc_global_new 'wgpu_render_bundle_set_bind_group (sc_function_type void 5 type-buffer) 6 unnamed)
store WGPURenderBundleEncoderId (getelementptr type-buffer 0)
store u64 (getelementptr type-buffer 1)
store u64 (getelementptr type-buffer 2)
store u64 (getelementptr type-buffer 3)
let wgpu_render_bundle_set_index_buffer = (sc_global_new 'wgpu_render_bundle_set_index_buffer (sc_function_type void 4 type-buffer) 6 unnamed)
store WGPURenderBundleEncoderId (getelementptr type-buffer 0)
store u64 (getelementptr type-buffer 1)
let wgpu_render_bundle_set_pipeline = (sc_global_new 'wgpu_render_bundle_set_pipeline (sc_function_type void 2 type-buffer) 6 unnamed)
store WGPURenderBundleEncoderId (getelementptr type-buffer 0)
store u32 (getelementptr type-buffer 1)
store u64 (getelementptr type-buffer 2)
store u64 (getelementptr type-buffer 3)
store u64 (getelementptr type-buffer 4)
let wgpu_render_bundle_set_vertex_buffer = (sc_global_new 'wgpu_render_bundle_set_vertex_buffer (sc_function_type void 5 type-buffer) 6 unnamed)
store WGPURenderBundleEncoderId (getelementptr type-buffer 0)
store u64 (getelementptr type-buffer 1)
store u64 (getelementptr type-buffer 2)
let wgpu_render_pass_bundle_indexed_indirect = (sc_global_new 'wgpu_render_pass_bundle_indexed_indirect (sc_function_type void 3 type-buffer) 6 unnamed)
store _gensc_mutable@<WGPURenderPass> (getelementptr type-buffer 0)
let wgpu_render_pass_destroy = (sc_global_new 'wgpu_render_pass_destroy (sc_function_type void 1 type-buffer) 6 unnamed)
store _gensc_mutable@<WGPURenderPass> (getelementptr type-buffer 0)
store u32 (getelementptr type-buffer 1)
store u32 (getelementptr type-buffer 2)
store u32 (getelementptr type-buffer 3)
store u32 (getelementptr type-buffer 4)
let wgpu_render_pass_draw = (sc_global_new 'wgpu_render_pass_draw (sc_function_type void 5 type-buffer) 6 unnamed)
store _gensc_mutable@<WGPURenderPass> (getelementptr type-buffer 0)
store u32 (getelementptr type-buffer 1)
store u32 (getelementptr type-buffer 2)
store u32 (getelementptr type-buffer 3)
store i32 (getelementptr type-buffer 4)
store u32 (getelementptr type-buffer 5)
let wgpu_render_pass_draw_indexed = (sc_global_new 'wgpu_render_pass_draw_indexed (sc_function_type void 6 type-buffer) 6 unnamed)
store _gensc_mutable@<WGPURenderPass> (getelementptr type-buffer 0)
store u64 (getelementptr type-buffer 1)
store u64 (getelementptr type-buffer 2)
let wgpu_render_pass_draw_indexed_indirect = (sc_global_new 'wgpu_render_pass_draw_indexed_indirect (sc_function_type void 3 type-buffer) 6 unnamed)
store _gensc_mutable@<WGPURenderPass> (getelementptr type-buffer 0)
store u64 (getelementptr type-buffer 1)
store u64 (getelementptr type-buffer 2)
let wgpu_render_pass_draw_indirect = (sc_global_new 'wgpu_render_pass_draw_indirect (sc_function_type void 3 type-buffer) 6 unnamed)
store _gensc_mutable@<WGPURenderPass> (getelementptr type-buffer 0)
let wgpu_render_pass_end_pass = (sc_global_new 'wgpu_render_pass_end_pass (sc_function_type void 1 type-buffer) 6 unnamed)
store _gensc_mutable@<WGPURenderPass> (getelementptr type-buffer 0)
store WGPULabel (getelementptr type-buffer 1)
store u32 (getelementptr type-buffer 2)
let wgpu_render_pass_insert_debug_marker = (sc_global_new 'wgpu_render_pass_insert_debug_marker (sc_function_type void 3 type-buffer) 6 unnamed)
store _gensc_mutable@<WGPURenderPass> (getelementptr type-buffer 0)
store u64 (getelementptr type-buffer 1)
store u64 (getelementptr type-buffer 2)
store u32 (getelementptr type-buffer 3)
let wgpu_render_pass_multi_draw_indexed_indirect = (sc_global_new 'wgpu_render_pass_multi_draw_indexed_indirect (sc_function_type void 4 type-buffer) 6 unnamed)
store _gensc_mutable@<WGPURenderPass> (getelementptr type-buffer 0)
store u64 (getelementptr type-buffer 1)
store u64 (getelementptr type-buffer 2)
store u64 (getelementptr type-buffer 3)
store u64 (getelementptr type-buffer 4)
store u32 (getelementptr type-buffer 5)
let wgpu_render_pass_multi_draw_indexed_indirect_count = (sc_global_new 'wgpu_render_pass_multi_draw_indexed_indirect_count (sc_function_type void 6 type-buffer) 6 unnamed)
store _gensc_mutable@<WGPURenderPass> (getelementptr type-buffer 0)
store u64 (getelementptr type-buffer 1)
store u64 (getelementptr type-buffer 2)
store u32 (getelementptr type-buffer 3)
let wgpu_render_pass_multi_draw_indirect = (sc_global_new 'wgpu_render_pass_multi_draw_indirect (sc_function_type void 4 type-buffer) 6 unnamed)
store _gensc_mutable@<WGPURenderPass> (getelementptr type-buffer 0)
store u64 (getelementptr type-buffer 1)
store u64 (getelementptr type-buffer 2)
store u64 (getelementptr type-buffer 3)
store u64 (getelementptr type-buffer 4)
store u32 (getelementptr type-buffer 5)
let wgpu_render_pass_multi_draw_indirect_count = (sc_global_new 'wgpu_render_pass_multi_draw_indirect_count (sc_function_type void 6 type-buffer) 6 unnamed)
store _gensc_mutable@<WGPURenderPass> (getelementptr type-buffer 0)
let wgpu_render_pass_pop_debug_group = (sc_global_new 'wgpu_render_pass_pop_debug_group (sc_function_type void 1 type-buffer) 6 unnamed)
store _gensc_mutable@<WGPURenderPass> (getelementptr type-buffer 0)
store WGPULabel (getelementptr type-buffer 1)
store u32 (getelementptr type-buffer 2)
let wgpu_render_pass_push_debug_group = (sc_global_new 'wgpu_render_pass_push_debug_group (sc_function_type void 3 type-buffer) 6 unnamed)
store _gensc_mutable@<WGPURenderPass> (getelementptr type-buffer 0)
store u32 (getelementptr type-buffer 1)
store u64 (getelementptr type-buffer 2)
store _gensc_@<u32> (getelementptr type-buffer 3)
store u64 (getelementptr type-buffer 4)
let wgpu_render_pass_set_bind_group = (sc_global_new 'wgpu_render_pass_set_bind_group (sc_function_type void 5 type-buffer) 6 unnamed)
store _gensc_mutable@<WGPURenderPass> (getelementptr type-buffer 0)
store _gensc_@<WGPUColor> (getelementptr type-buffer 1)
let wgpu_render_pass_set_blend_color = (sc_global_new 'wgpu_render_pass_set_blend_color (sc_function_type void 2 type-buffer) 6 unnamed)
store _gensc_mutable@<WGPURenderPass> (getelementptr type-buffer 0)
store u64 (getelementptr type-buffer 1)
store u64 (getelementptr type-buffer 2)
store u64 (getelementptr type-buffer 3)
let wgpu_render_pass_set_index_buffer = (sc_global_new 'wgpu_render_pass_set_index_buffer (sc_function_type void 4 type-buffer) 6 unnamed)
store _gensc_mutable@<WGPURenderPass> (getelementptr type-buffer 0)
store u64 (getelementptr type-buffer 1)
let wgpu_render_pass_set_pipeline = (sc_global_new 'wgpu_render_pass_set_pipeline (sc_function_type void 2 type-buffer) 6 unnamed)
store _gensc_mutable@<WGPURenderPass> (getelementptr type-buffer 0)
store u32 (getelementptr type-buffer 1)
store u32 (getelementptr type-buffer 2)
store u32 (getelementptr type-buffer 3)
store u32 (getelementptr type-buffer 4)
let wgpu_render_pass_set_scissor_rect = (sc_global_new 'wgpu_render_pass_set_scissor_rect (sc_function_type void 5 type-buffer) 6 unnamed)
store _gensc_mutable@<WGPURenderPass> (getelementptr type-buffer 0)
store u32 (getelementptr type-buffer 1)
let wgpu_render_pass_set_stencil_reference = (sc_global_new 'wgpu_render_pass_set_stencil_reference (sc_function_type void 2 type-buffer) 6 unnamed)
store _gensc_mutable@<WGPURenderPass> (getelementptr type-buffer 0)
store u32 (getelementptr type-buffer 1)
store u64 (getelementptr type-buffer 2)
store u64 (getelementptr type-buffer 3)
store u64 (getelementptr type-buffer 4)
let wgpu_render_pass_set_vertex_buffer = (sc_global_new 'wgpu_render_pass_set_vertex_buffer (sc_function_type void 5 type-buffer) 6 unnamed)
store _gensc_mutable@<WGPURenderPass> (getelementptr type-buffer 0)
store f32 (getelementptr type-buffer 1)
store f32 (getelementptr type-buffer 2)
store f32 (getelementptr type-buffer 3)
store f32 (getelementptr type-buffer 4)
store f32 (getelementptr type-buffer 5)
store f32 (getelementptr type-buffer 6)
let wgpu_render_pass_set_viewport = (sc_global_new 'wgpu_render_pass_set_viewport (sc_function_type void 7 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
let wgpu_render_pipeline_destroy = (sc_global_new 'wgpu_render_pipeline_destroy (sc_function_type void 1 type-buffer) 6 unnamed)
store _gensc_@<WGPURequestAdapterOptions> (getelementptr type-buffer 0)
store u32 (getelementptr type-buffer 1)
store bool (getelementptr type-buffer 2)
store WGPURequestAdapterCallback (getelementptr type-buffer 3)
store _gensc_@<void> (getelementptr type-buffer 4)
let wgpu_request_adapter_async = (sc_global_new 'wgpu_request_adapter_async (sc_function_type void 5 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
let wgpu_sampler_destroy = (sc_global_new 'wgpu_sampler_destroy (sc_function_type void 1 type-buffer) 6 unnamed)
store WGPULogCallback (getelementptr type-buffer 0)
let wgpu_set_log_callback = (sc_global_new 'wgpu_set_log_callback (sc_function_type void 1 type-buffer) 6 unnamed)
store WGPULogLevel (getelementptr type-buffer 0)
let wgpu_set_log_level = (sc_global_new 'wgpu_set_log_level (sc_function_type i32 1 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
let wgpu_shader_module_destroy = (sc_global_new 'wgpu_shader_module_destroy (sc_function_type void 1 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
let wgpu_swap_chain_get_next_texture = (sc_global_new 'wgpu_swap_chain_get_next_texture (sc_function_type WGPUSwapChainOutput 1 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
let wgpu_swap_chain_present = (sc_global_new 'wgpu_swap_chain_present (sc_function_type void 1 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
store _gensc_@<WGPUTextureViewDescriptor> (getelementptr type-buffer 1)
let wgpu_texture_create_view = (sc_global_new 'wgpu_texture_create_view (sc_function_type u64 2 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
let wgpu_texture_destroy = (sc_global_new 'wgpu_texture_destroy (sc_function_type void 1 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
let wgpu_texture_view_destroy = (sc_global_new 'wgpu_texture_view_destroy (sc_function_type void 1 type-buffer) 6 unnamed)
do
    let ShaderSource = WGPUShaderSource
    let RasterizationStateDescriptor = WGPURasterizationStateDescriptor
    let BindGroupId = WGPUBindGroupId
    let DeviceId = WGPUDeviceId
    let CAdapterInfo = WGPUCAdapterInfo
    let BindGroupLayoutEntry = WGPUBindGroupLayoutEntry
    let RenderBundleEncoderDescriptor = WGPURenderBundleEncoderDescriptor
    let CommandEncoderDescriptor = WGPUCommandEncoderDescriptor
    let RenderPipelineId = WGPURenderPipelineId
    let RequestAdapterCallback = WGPURequestAdapterCallback
    let BackendBit = WGPUBackendBit
    let Id_SwapChain_Dummy = WGPUId_SwapChain_Dummy
    let NonZeroU64 = WGPUNonZeroU64
    let RenderPassDepthStencilAttachmentDescriptor = WGPURenderPassDepthStencilAttachmentDescriptor
    let ChainedStruct = WGPUChainedStruct
    let Id_BindGroup_Dummy = WGPUId_BindGroup_Dummy
    let TextureDimension = WGPUTextureDimension
    let SwapChainDescriptor = WGPUSwapChainDescriptor
    let LogCallback = WGPULogCallback
    let Id_Sampler_Dummy = WGPUId_Sampler_Dummy
    let FilterMode = WGPUFilterMode
    let SwapChainId = WGPUSwapChainId
    let CDeviceType = WGPUCDeviceType
    let TextureViewDimension = WGPUTextureViewDimension
    let Id_Device_Dummy = WGPUId_Device_Dummy
    let SwapChainOutput = WGPUSwapChainOutput
    let CommandBufferDescriptor = WGPUCommandBufferDescriptor
    let Id_Texture_Dummy = WGPUId_Texture_Dummy
    let Label = WGPULabel
    let CullMode = WGPUCullMode
    let RenderBundleEncoderId = WGPURenderBundleEncoderId
    let StencilStateFaceDescriptor = WGPUStencilStateFaceDescriptor
    let CompareFunction = WGPUCompareFunction
    let QueueId = WGPUQueueId
    let Option_AdapterId = WGPUOption_AdapterId
    let TextureCopyView = WGPUTextureCopyView
    let VertexBufferLayoutDescriptor = WGPUVertexBufferLayoutDescriptor
    let Option_SurfaceId = WGPUOption_SurfaceId
    let BindingType = WGPUBindingType
    let BindGroupDescriptor = WGPUBindGroupDescriptor
    let Option_SamplerId = WGPUOption_SamplerId
    let DynamicOffset = WGPUDynamicOffset
    let SamplerDescriptor = WGPUSamplerDescriptor
    let VertexFormat = WGPUVertexFormat
    let InputStepMode = WGPUInputStepMode
    let Option_NonZeroU64 = WGPUOption_NonZeroU64
    let TextureAspect = WGPUTextureAspect
    let RenderPassColorAttachmentDescriptor = WGPURenderPassColorAttachmentDescriptor
    let Option_NonZeroU32 = WGPUOption_NonZeroU32
    let TextureViewDescriptor = WGPUTextureViewDescriptor
    let SurfaceId = WGPUSurfaceId
    let TextureViewId = WGPUTextureViewId
    let LoadOp = WGPULoadOp
    let RenderPassDescriptor = WGPURenderPassDescriptor
    let CommandBufferId = WGPUCommandBufferId
    let RenderPass = WGPURenderPass
    let BindGroupLayoutId = WGPUBindGroupLayoutId
    let ComputePipelineDescriptor = WGPUComputePipelineDescriptor
    let BindGroupLayoutDescriptor = WGPUBindGroupLayoutDescriptor
    let PassChannel_f32 = WGPUPassChannel_f32
    let RenderPassColorAttachmentDescriptorBase_TextureViewId = WGPURenderPassColorAttachmentDescriptorBase_TextureViewId
    let PresentMode = WGPUPresentMode
    let AdapterId = WGPUAdapterId
    let BindGroupEntry = WGPUBindGroupEntry
    let Id_RenderBundle = WGPUId_RenderBundle
    let RawString = WGPURawString
    let TextureId = WGPUTextureId
    let SwapChainStatus = WGPUSwapChainStatus
    let BufferMapCallback = WGPUBufferMapCallback
    let BlendOperation = WGPUBlendOperation
    let TextureDescriptor = WGPUTextureDescriptor
    let ShaderLocation = WGPUShaderLocation
    let SType = WGPUSType
    let ComputePipelineId = WGPUComputePipelineId
    let CommandEncoderId = WGPUCommandEncoderId
    let TextureFormat = WGPUTextureFormat
    let Id_Buffer_Dummy = WGPUId_Buffer_Dummy
    let CDeviceType = WGPUCDeviceType
    let PassChannel_u32 = WGPUPassChannel_u32
    let Backend = WGPUBackend
    let Option_BufferSize = WGPUOption_BufferSize
    let StencilOperation = WGPUStencilOperation
    let TextureUsage = WGPUTextureUsage
    let ShaderStage = WGPUShaderStage
    let BufferMapAsyncStatus = WGPUBufferMapAsyncStatus
    let IndexFormat = WGPUIndexFormat
    let BufferDescriptor = WGPUBufferDescriptor
    let BufferId = WGPUBufferId
    let BufferAddress = WGPUBufferAddress
    let VertexAttributeDescriptor = WGPUVertexAttributeDescriptor
    let Id_Surface = WGPUId_Surface
    let PrimitiveTopology = WGPUPrimitiveTopology
    let ComputePassDescriptor = WGPUComputePassDescriptor
    let BlendFactor = WGPUBlendFactor
    let Id_ComputePipeline_Dummy = WGPUId_ComputePipeline_Dummy
    let LogLevel = WGPULogLevel
    let BlendDescriptor = WGPUBlendDescriptor
    let RenderPipelineDescriptor = WGPURenderPipelineDescriptor
    let AddressMode = WGPUAddressMode
    let AnisotropicSamplerDescriptorExt = WGPUAnisotropicSamplerDescriptorExt
    let StoreOp = WGPUStoreOp
    let PipelineLayoutDescriptor = WGPUPipelineLayoutDescriptor
    let RequestAdapterOptions = WGPURequestAdapterOptions
    let TextureComponentType = WGPUTextureComponentType
    let BufferUsage = WGPUBufferUsage
    let Id_RenderPipeline_Dummy = WGPUId_RenderPipeline_Dummy
    let CLimits = WGPUCLimits
    let Color = WGPUColor
    let PassChannel_Color = WGPUPassChannel_Color
    let Id_BindGroupLayout_Dummy = WGPUId_BindGroupLayout_Dummy
    let ColorWrite = WGPUColorWrite
    let Id_Adapter_Dummy = WGPUId_Adapter_Dummy
    let Option_BufferId = WGPUOption_BufferId
    let SType = WGPUSType
    let Option_TextureViewId = WGPUOption_TextureViewId
    let ComputePass = WGPUComputePass
    let VertexStateDescriptor = WGPUVertexStateDescriptor
    let PipelineLayoutId = WGPUPipelineLayoutId
    let BufferCopyView = WGPUBufferCopyView
    let Id_CommandBuffer_Dummy = WGPUId_CommandBuffer_Dummy
    let Id_TextureView_Dummy = WGPUId_TextureView_Dummy
    let TextureDataLayout = WGPUTextureDataLayout
    let RenderBundleEncoder = WGPURenderBundleEncoder
    let Id_PipelineLayout_Dummy = WGPUId_PipelineLayout_Dummy
    let Id_ShaderModule_Dummy = WGPUId_ShaderModule_Dummy
    let Backend = WGPUBackend
    let ProgrammableStageDescriptor = WGPUProgrammableStageDescriptor
    let BindingType = WGPUBindingType
    let PowerPreference = WGPUPowerPreference
    let RenderBundleDescriptor_Label = WGPURenderBundleDescriptor_Label
    let RenderBundleId = WGPURenderBundleId
    let Extent3d = WGPUExtent3d
    let ShaderModuleId = WGPUShaderModuleId
    let Origin3d = WGPUOrigin3d
    let ColorStateDescriptor = WGPUColorStateDescriptor
    let FrontFace = WGPUFrontFace
    let RenderPassDepthStencilAttachmentDescriptorBase_TextureViewId = WGPURenderPassDepthStencilAttachmentDescriptorBase_TextureViewId
    let DepthStencilStateDescriptor = WGPUDepthStencilStateDescriptor
    let SamplerId = WGPUSamplerId
    let Features = WGPUFeatures
    let BufferSize = WGPUBufferSize
    let adapter_destroy = wgpu_adapter_destroy
    let adapter_features = wgpu_adapter_features
    let adapter_get_info = wgpu_adapter_get_info
    let adapter_limits = wgpu_adapter_limits
    let adapter_request_device = wgpu_adapter_request_device
    let bind_group_destroy = wgpu_bind_group_destroy
    let bind_group_layout_destroy = wgpu_bind_group_layout_destroy
    let buffer_destroy = wgpu_buffer_destroy
    let buffer_get_mapped_range = wgpu_buffer_get_mapped_range
    let buffer_map_read_async = wgpu_buffer_map_read_async
    let buffer_map_write_async = wgpu_buffer_map_write_async
    let buffer_unmap = wgpu_buffer_unmap
    let command_buffer_destroy = wgpu_command_buffer_destroy
    let command_encoder_begin_compute_pass = wgpu_command_encoder_begin_compute_pass
    let command_encoder_begin_render_pass = wgpu_command_encoder_begin_render_pass
    let command_encoder_copy_buffer_to_buffer = wgpu_command_encoder_copy_buffer_to_buffer
    let command_encoder_copy_buffer_to_texture = wgpu_command_encoder_copy_buffer_to_texture
    let command_encoder_copy_texture_to_buffer = wgpu_command_encoder_copy_texture_to_buffer
    let command_encoder_copy_texture_to_texture = wgpu_command_encoder_copy_texture_to_texture
    let command_encoder_destroy = wgpu_command_encoder_destroy
    let command_encoder_finish = wgpu_command_encoder_finish
    let compute_pass_destroy = wgpu_compute_pass_destroy
    let compute_pass_dispatch = wgpu_compute_pass_dispatch
    let compute_pass_dispatch_indirect = wgpu_compute_pass_dispatch_indirect
    let compute_pass_end_pass = wgpu_compute_pass_end_pass
    let compute_pass_insert_debug_marker = wgpu_compute_pass_insert_debug_marker
    let compute_pass_pop_debug_group = wgpu_compute_pass_pop_debug_group
    let compute_pass_push_debug_group = wgpu_compute_pass_push_debug_group
    let compute_pass_set_bind_group = wgpu_compute_pass_set_bind_group
    let compute_pass_set_pipeline = wgpu_compute_pass_set_pipeline
    let compute_pipeline_destroy = wgpu_compute_pipeline_destroy
    let create_surface_from_android = wgpu_create_surface_from_android
    let create_surface_from_metal_layer = wgpu_create_surface_from_metal_layer
    let create_surface_from_wayland = wgpu_create_surface_from_wayland
    let create_surface_from_windows_hwnd = wgpu_create_surface_from_windows_hwnd
    let create_surface_from_xlib = wgpu_create_surface_from_xlib
    let device_create_bind_group = wgpu_device_create_bind_group
    let device_create_bind_group_layout = wgpu_device_create_bind_group_layout
    let device_create_buffer = wgpu_device_create_buffer
    let device_create_command_encoder = wgpu_device_create_command_encoder
    let device_create_compute_pipeline = wgpu_device_create_compute_pipeline
    let device_create_pipeline_layout = wgpu_device_create_pipeline_layout
    let device_create_render_bundle_encoder = wgpu_device_create_render_bundle_encoder
    let device_create_render_pipeline = wgpu_device_create_render_pipeline
    let device_create_sampler = wgpu_device_create_sampler
    let device_create_shader_module = wgpu_device_create_shader_module
    let device_create_swap_chain = wgpu_device_create_swap_chain
    let device_create_texture = wgpu_device_create_texture
    let device_destroy = wgpu_device_destroy
    let device_features = wgpu_device_features
    let device_get_default_queue = wgpu_device_get_default_queue
    let device_limits = wgpu_device_limits
    let device_poll = wgpu_device_poll
    let get_version = wgpu_get_version
    let pipeline_layout_destroy = wgpu_pipeline_layout_destroy
    let queue_submit = wgpu_queue_submit
    let queue_write_buffer = wgpu_queue_write_buffer
    let queue_write_texture = wgpu_queue_write_texture
    let render_bundle_destroy = wgpu_render_bundle_destroy
    let render_bundle_draw = wgpu_render_bundle_draw
    let render_bundle_draw_indexed = wgpu_render_bundle_draw_indexed
    let render_bundle_draw_indirect = wgpu_render_bundle_draw_indirect
    let render_bundle_encoder_finish = wgpu_render_bundle_encoder_finish
    let render_bundle_insert_debug_marker = wgpu_render_bundle_insert_debug_marker
    let render_bundle_pop_debug_group = wgpu_render_bundle_pop_debug_group
    let render_bundle_push_debug_group = wgpu_render_bundle_push_debug_group
    let render_bundle_set_bind_group = wgpu_render_bundle_set_bind_group
    let render_bundle_set_index_buffer = wgpu_render_bundle_set_index_buffer
    let render_bundle_set_pipeline = wgpu_render_bundle_set_pipeline
    let render_bundle_set_vertex_buffer = wgpu_render_bundle_set_vertex_buffer
    let render_pass_bundle_indexed_indirect = wgpu_render_pass_bundle_indexed_indirect
    let render_pass_destroy = wgpu_render_pass_destroy
    let render_pass_draw = wgpu_render_pass_draw
    let render_pass_draw_indexed = wgpu_render_pass_draw_indexed
    let render_pass_draw_indexed_indirect = wgpu_render_pass_draw_indexed_indirect
    let render_pass_draw_indirect = wgpu_render_pass_draw_indirect
    let render_pass_end_pass = wgpu_render_pass_end_pass
    let render_pass_insert_debug_marker = wgpu_render_pass_insert_debug_marker
    let render_pass_multi_draw_indexed_indirect = wgpu_render_pass_multi_draw_indexed_indirect
    let render_pass_multi_draw_indexed_indirect_count = wgpu_render_pass_multi_draw_indexed_indirect_count
    let render_pass_multi_draw_indirect = wgpu_render_pass_multi_draw_indirect
    let render_pass_multi_draw_indirect_count = wgpu_render_pass_multi_draw_indirect_count
    let render_pass_pop_debug_group = wgpu_render_pass_pop_debug_group
    let render_pass_push_debug_group = wgpu_render_pass_push_debug_group
    let render_pass_set_bind_group = wgpu_render_pass_set_bind_group
    let render_pass_set_blend_color = wgpu_render_pass_set_blend_color
    let render_pass_set_index_buffer = wgpu_render_pass_set_index_buffer
    let render_pass_set_pipeline = wgpu_render_pass_set_pipeline
    let render_pass_set_scissor_rect = wgpu_render_pass_set_scissor_rect
    let render_pass_set_stencil_reference = wgpu_render_pass_set_stencil_reference
    let render_pass_set_vertex_buffer = wgpu_render_pass_set_vertex_buffer
    let render_pass_set_viewport = wgpu_render_pass_set_viewport
    let render_pipeline_destroy = wgpu_render_pipeline_destroy
    let request_adapter_async = wgpu_request_adapter_async
    let sampler_destroy = wgpu_sampler_destroy
    let set_log_callback = wgpu_set_log_callback
    let set_log_level = wgpu_set_log_level
    let shader_module_destroy = wgpu_shader_module_destroy
    let swap_chain_get_next_texture = wgpu_swap_chain_get_next_texture
    let swap_chain_present = wgpu_swap_chain_present
    let texture_create_view = wgpu_texture_create_view
    let texture_destroy = wgpu_texture_destroy
    let texture_view_destroy = wgpu_texture_view_destroy
    let ColorWrite_ALPHA = 8
    let ColorWrite_ALL = 15
    let ColorWrite_COLOR = 7
    let TextureUsage_COPY_SRC = 1
    let DESIRED_NUM_FRAMES = 3
    let Features_ALL_UNSAFE = 18446462598732840960
    let Features_ALL_WEBGPU = 65535
    let BufferUsage_MAP_WRITE = 2
    let TextureUsage_COPY_DST = 2
    let BufferUsage_COPY_SRC = 4
    let Features_MAPPABLE_PRIMARY_BUFFERS = 65536
    let MAX_MIP_LEVELS = 16
    let ShaderStage_VERTEX = 1
    let ShaderStage_COMPUTE = 4
    let MAX_VERTEX_BUFFERS = 16
    let BufferUsage_VERTEX = 32
    let ShaderStage_FRAGMENT = 2
    let MAX_ANISOTROPY = 16
    let BufferUsage_INDEX = 16
    let Features_ALL_NATIVE = 18446744073709486080
    let ColorWrite_GREEN = 2
    let ColorWrite_BLUE = 4
    let COPY_BYTES_PER_ROW_ALIGNMENT = 256
    let MAX_COLOR_TARGETS = 4
    let BufferUsage_COPY_DST = 8
    let BufferUsage_INDIRECT = 256
    let BufferUsage_UNIFORM = 64
    let ShaderStage_NONE = 0
    let TextureUsage_STORAGE = 8
    let TextureUsage_OUTPUT_ATTACHMENT = 16
    let BufferUsage_STORAGE = 128
    let ColorWrite_RED = 1
    let TextureUsage_SAMPLED = 4
    let BufferUsage_MAP_READ = 1
    locals;
