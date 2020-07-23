using import struct
using import Option
using import Array
using import enum
using import glm

using import radlib.core-extensions
let wgpu = (import .foreign.wgpu-native)
let AppSettings = (import radlib.app-settings)


enum ResourceRequestError
    TryAgainLater

struct GfxState plain
    surface : wgpu.SurfaceId
    adapter : wgpu.AdapterId
    device : wgpu.DeviceId
    swap-chain : wgpu.SwapChainId
    queue : wgpu.QueueId

global istate : GfxState

struct 2DTexture
    width : i32
    height : i32
    handle : (Option wgpu.TextureId)

    inline __typecall (cls imagedata debug-name)
        debug-name := (none? debug-name) and "texture" or debug-name
        let handle =
            wgpu.device_create_texture istate.device
                &local wgpu.TextureDescriptor
                    label = debug-name
                    size =
                        wgpu.Extent3d
                            width = (imagedata.width as u32)
                            height = (imagedata.height as u32)
                            depth = 1 #?
                    mip_level_count = 1 # TODO: investigate how to handle this
                    sample_count = 1 # TODO: same
                    dimension = wgpu.TextureDimension.D2
                    format = wgpu.TextureFormat.Rgba8Unorm # TODO: different formats
                    usage = wgpu.TextureUsage_COPY_DST

        wgpu.queue_write_texture (wgpu.device_get_default_queue istate.device)
            # texture
            &local wgpu.TextureCopyView
                texture = handle
                mip_level = 0
                origin =
                    wgpu.Origin3d 0 0 0

            # data
            imagedata.data
            # data_length
            countof imagedata.data
            # data_layout
            &local wgpu.TextureDataLayout
                offset = 0
                bytes_per_row = ((4 * imagedata.width) as u32)
                rows_per_image = (imagedata.height as u32)
            # size
            &local wgpu.Extent3d
                width = (imagedata.width as u32)
                height = (imagedata.height as u32)
                depth = 1 #?

        super-type.__typecall cls
            width = imagedata.width
            height = imagedata.height
            handle = handle

struct Shader
    handle : (Option wgpu.ShaderModuleId)
    inline __typecall (cls stage fun)
        let src = (static-compile-spirv stage (static-typify fun))
        let len = ((countof src) // 4)

        let device = istate.device
        let handle =
            wgpu.device_create_shader_module device
                &local wgpu.ShaderModuleDescriptor
                    code =
                        typeinit
                            bytes = (src as rawstring as (pointer u32))
                            length = len

        super-type.__typecall cls
            handle = handle

    inline __drop (self)
        try
            wgpu.shader_module_destroy ('unwrap self.handle)
        else
            ;

# TODO: many options are hardcoded and have to be refactored out. Will do when different
# pipelines are required.
struct RenderPipeline
    handle : (Option wgpu.RenderPipelineId)
    inline... __typecall (cls, vertex-shader : Shader, fragment-shader : Shader)
        let rpip-layout =
            wgpu.device_create_pipeline_layout istate.device
                &local wgpu.PipelineLayoutDescriptor
                    null
                    0
        let handle =
            wgpu.device_create_render_pipeline istate.device
                &local wgpu.RenderPipelineDescriptor
                    layout = rpip-layout
                    vertex_stage =
                        wgpu.ProgrammableStageDescriptor
                            module = ('force-unwrap vertex-shader.handle)
                            entry_point = "main"
                    fragment_stage =
                        &local wgpu.ProgrammableStageDescriptor
                            module = ('force-unwrap fragment-shader.handle)
                            entry_point = "main"
                    primitive_topology = wgpu.PrimitiveTopology.TriangleList
                    rasterization_state =
                        &local wgpu.RasterizationStateDescriptor
                    sample_count = 1
                    color_states =
                        &local wgpu.ColorStateDescriptor
                            format = wgpu.TextureFormat.Bgra8UnormSrgb
                            color_blend =
                                typeinit
                                    src_factor = wgpu.BlendFactor.One
                                    dst_factor = wgpu.BlendFactor.Zero
                                    operation = wgpu.BlendOperation.Add
                            write_mask = wgpu.ColorWrite_ALL
                    color_states_length = 1
        super-type.__typecall cls
            handle = handle
    case (cls handle)
        super-type.__typecall cls
            handle = handle


    inline __drop (self)
        try
            wgpu.render_pipeline_destroy ('unwrap self.handle)
        else
            ;

struct CommandBuffer
    handle : (Option wgpu.CommandBufferId)

    inline __typecall (cls handle)
        super-type.__typecall cls
            handle = handle

    fn submit (self)
        local handle = ('force-unwrap self.handle)
        wgpu.queue_submit istate.queue &handle 1
        self.handle = none
        drop self

    inline __drop (self)
        try
            wgpu.command_buffer_destroy ('unwrap self.handle)
        else
            ;


struct CommandEncoder
    handle : (Option wgpu.CommandEncoderId)

    inline __typecall (cls)
        let handle =
            wgpu.device_create_command_encoder istate.device
                &local wgpu.CommandEncoderDescriptor
                    label = "command encoder"
        super-type.__typecall cls
            handle = handle

    inline finish (self)
        let cmdbuf = (wgpu.command_encoder_finish ('force-unwrap self.handle) null)
        self.handle = none
        drop self
        CommandBuffer cmdbuf

    inline __drop (self)
        try
            wgpu.command_encoder_destroy ('unwrap self.handle)
        else
            ;

struct RenderPass
    handle : (Option wgpu.RenderPassId)
    color-attachments : (Array wgpu.RenderPassColorAttachmentDescriptor)
    depth-attachment  : (Option wgpu.RenderPassDepthStencilAttachmentDescriptor)

    fn set-pipeline (self pip)
        static-match (typeof pip)
        case RenderPipeline
            wgpu.render_pass_set_pipeline ('force-unwrap self.handle) ('force-unwrap pip.handle)
        default
            static-error "second argument must be a pipeline object"

    fn... draw (self, vertex-count : u32, instance-count : u32 = 1,
                first-vertex : u32 = 0, first-instance : u32 = 0)
        wgpu.render_pass_draw ('force-unwrap self.handle)
            vertex-count
            instance-count
            first-vertex
            first-instance

    fn... draw-indexed (self, index-count : u32, instance-count : u32 = 1,
                first-index : u32 = 0, first-instance : u32 = 0, base-vertex : u32 = 0)
        wgpu.render_pass_draw_indexed ('force-unwrap self.handle)
            index-count
            instance-count
            first-index
            base-vertex
            first-instance
           
    inline finish (self)
        wgpu.render_pass_end_pass ('force-unwrap self.handle)
        self.handle = none
        drop self

    inline __drop (self)
        try
            wgpu.render_pass_destroy ('unwrap self.handle)
        else
            ;

fn update-backbuffer-size (width height)
    let device surface = istate.device istate.surface

    istate.swap-chain =
        wgpu.device_create_swap_chain device surface
            &local wgpu.SwapChainDescriptor
                usage = wgpu.WGPUTextureUsage_OUTPUT_ATTACHMENT
                format = wgpu.TextureFormat.Bgra8UnormSrgb
                width = (width as u32)
                height = (height as u32)
                present_mode = wgpu.PresentMode.Fifo
    ;

fn... init (surface, width : i32, height : i32, trace-path = null)
    wgpu.set_log_level wgpu.LogLevel.Error
    wgpu.set_log_callback
        fn "wgpu-log" (level msg)
            static-if AppSettings.AOT?
                using import radlib.libc
                stdio.printf "level: %d - %s\n" level msg
                ;
            else
                print "level:" level "-" (string msg)

    # adapter configuration
    # =====================
    local adapter : wgpu.AdapterId
    wgpu.request_adapter_async
        &local wgpu.RequestAdapterOptions
            power_preference = wgpu.PowerPreference.LowPower
            compatible_surface = surface
        | 2 4 8
        false
        # callback
        fn "on-adapter-available" (result adapterptr)
            # adapter = result
            # let statusptr = (bitcast statusptr (mutable pointer bool))
            adapterptr as:= (mutable pointer wgpu.AdapterId)
            @adapterptr = result
            ;
        &adapter

    # device configuration
    # =====================
    let device =
        wgpu.adapter_request_device adapter
            0 # extensions
            &local wgpu.CLimits
                max_bind_groups = wgpu.WGPUDEFAULT_BIND_GROUPS
            trace-path

    istate.surface = surface
    istate.adapter = adapter
    istate.device = device
    # creates and sets the swap chain
    update-backbuffer-size width height
    istate.queue = (wgpu.device_get_default_queue device)
    ;

fn acquire-backbuffer ()
    let swap-chain = istate.swap-chain
    let next-image = (wgpu.swap_chain_get_next_texture swap-chain)
    if (next-image.view_id == 0)
        raise (ResourceRequestError.TryAgainLater)
    next-image

fn... screen-pass (cmd-encoder : CommandEncoder, clear-color : vec4)
    local color-attachments = ((Array wgpu.RenderPassColorAttachmentDescriptor))
    'append color-attachments
        wgpu.RenderPassColorAttachmentDescriptor
            attachment = ((acquire-backbuffer) . view_id)
            load_op = wgpu.LoadOp.Clear
            store_op = wgpu.StoreOp.Store
            clear_color = (wgpu.Color (unpack clear-color))

    let handle =
        wgpu.command_encoder_begin_render_pass ('force-unwrap cmd-encoder.handle)
            &local wgpu.RenderPassDescriptor
                color_attachments = color-attachments
                color_attachments_length = (countof color-attachments)
                depth_stencil_attachment = null

    RenderPass
        handle = handle
        color-attachments = color-attachments
        depth-attachment = none

fn present ()
    wgpu.swap_chain_present istate.swap-chain

do
    let
        init
        update-backbuffer-size
        acquire-backbuffer
        screen-pass
        present

        GfxState
        ResourceRequestError
        CommandEncoder
        RenderPass
        Shader
        RenderPipeline
        2DTexture

    backend := istate

    locals;
