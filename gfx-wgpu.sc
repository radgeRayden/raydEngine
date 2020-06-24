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

struct Shader

struct Pipeline

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

    # inline __typecall (cls cmd-encoder)

    inline finish (self)
        wgpu.render_pass_end_pass ('force-unwrap self.handle)
        self.handle = none
        drop self

    inline __drop (self)
        try
            wgpu.render_pass_destroy ('unwrap self.handle)
        else
            ;


inline create-shader-module (stage fun)
    let src = (compile-spirv stage (static-typify fun))
    let len = ((countof src) // 4)

    let device = istate.device
    wgpu.device_create_shader_module device
        &local wgpu.ShaderModuleDescriptor
            code =
                typeinit
                    bytes = (src as rawstring as (pointer u32))
                    length = len

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
        create-shader-module
        screen-pass
        present

        GfxState
        ResourceRequestError
        CommandEncoder
        RenderPass

    backend := istate

    locals;
