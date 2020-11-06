using import radlib.core-extensions

using import struct
using import Option
using import glm
using import String
using import enum
using import Map
using import Array

let wgpu = (import .wrapper)
import ...HID

inline struct-hash-fields (self)
    let fhashes... =
        va-map
            inline (f)
                let k = (keyof f.Type)
                let v = (getattr self k)
                hash v
            (typeof self) . __fields__
    let head tail = (va-split 1 fhashes...)
    va-lfold (head)
        inline (__ current computed)
            hash computed current
        (tail)

global wgpu-limits : wgpu.CLimits

# NOTE:
# Pipelines and their dependencies are cached, and checked by hash of their descriptors. We
# use our own version of the descriptors to have correct lifetime handling.
# Caches use the hash type to index the map to avoid lifetime problems with duplicate
# copies.
# Similarly, flushing the descriptor has to return a non lifetime managed handle, as lifetime
# of the objects is managed by the caches themselves. This could be avoided by using an Rc, but
# since it's for internal usage only I decided to avoid the overhead. Could be revised if
# safety becomes an issue here.
# At the moment, these caches are not thread safe. Thread safety could be achieved by putting
# a mutex over the access of the global caches.
struct BindGroupLayoutBlueprint
    bind-group-entries : (Map usize wgpu.BindGroupLayoutEntry)

    global bind-group-layout-cache : (Map hash wgpu.BindGroupLayoutId)

    let __== = struct-equality-by-field
    inline __hash (self)
        fold (h = (hash 0)) for k v in self.bind-group-entries
            hash h
                hash k v

    fn flush (self device)
        try
            storagecast
                view
                    'get bind-group-layout-cache (hash self)
        else
            local bgroup-entries : (Array wgpu.BindGroupLayoutEntry)
            # NOTE: complete guess on my part that more than 16 entries aren't needed. There's
            # no query for this limit at the moment.
            'resize bgroup-entries 16
            for idx bgroup-entry in self.bind-group-entries
                bgroup-entries @ idx = bgroup-entry
            let new-bgroup-layout =
                wgpu.device_create_bind_group_layout (view device)
                    &local wgpu.BindGroupLayoutDescriptor
                        entries = bgroup-entries._items
                        entries_length = (countof bgroup-entries)
            let result = (copy (storagecast (view new-bgroup-layout)))
            'set bind-group-layout-cache (hash self) new-bgroup-layout
            result

    fn clear-cache ()

struct PipelineLayoutBlueprint
    bind-group-layouts : (Map usize BindGroupLayoutBlueprint) # slot -> bind group

    global pipeline-layout-cache : (Map hash wgpu.PipelineLayoutId)

    let __== = struct-equality-by-field
    inline __hash (self)
        fold (h = (hash 0)) for k v in self.bind-group-layouts
            hash h
                hash k v

    fn flush (self device)
        try
            storagecast
                view
                    'get pipeline-layout-cache (hash self)
        else
            local bgroup-layouts : (Array (storageof wgpu.BindGroupLayoutId))
            'resize bgroup-layouts wgpu-limits.max_bind_groups
            for idx bind-group-layout in self.bind-group-layouts
                bgroup-layouts @ idx = ('flush bind-group-layout device)
            let new-piplayout =
                wgpu.device_create_pipeline_layout (view device)
                    &local wgpu.PipelineLayoutDescriptor
                        bind_group_layouts = (bgroup-layouts._items as (pointer u64))
                        bind_group_layouts_length = (countof bgroup-layouts)

            let result = (copy (storagecast (view new-piplayout)))
            'set pipeline-layout-cache (hash self) new-piplayout
            result

    fn clear-cache ()
        'clear pipeline-layout-cache

struct RenderPipelineBlueprint
    layout : PipelineLayoutBlueprint
    vertex-stage : wgpu.ShaderModuleId
    fragment-stage : (Option wgpu.ShaderModuleId)
    primitive-topology : wgpu.PrimitiveTopology
    rasterization-state : wgpu.RasterizationStateDescriptor
    color-states : (Array wgpu.ColorStateDescriptor)
    depth-stencil-state : (Option wgpu.DepthStencilStateDescriptor)
    vertex-state : wgpu.VertexStateDescriptor
    sample-count : u32 = 1
    sample-mask : u32 = 0xffffffff
    alpha-to-coverage-enabled : bool

    global pipeline-cache : (Map hash wgpu.RenderPipelineId)

    typedef+ (Array wgpu.ColorStateDescriptor)
        inline __hash (self)
            fold (result = (hash none)) for el in self
                hash result el

    let __== = struct-equality-by-field
    let __hash = struct-hash-fields

    fn flush (self device)
        try
            storagecast
                view
                    'get pipeline-cache (hash self)
        else
            let fragment-module =
                try
                    deref
                        storagecast
                            view ('unwrap self.fragment-stage)
                else
                    0:u64

            let depth-stencil-state =
                try
                    let desc = ('unwrap self.depth-stencil-state)
                    &desc as (pointer wgpu.DepthStencilStateDescriptor)
                else
                    nullof (pointer wgpu.DepthStencilStateDescriptor)

            let desc =
                wgpu.RenderPipelineDescriptor
                    layout = ('flush self.layout device)
                    vertex_stage =
                        typeinit
                            module = self.vertex-stage
                            entry_point = "main"
                    fragment_stage =
                        &local wgpu.ProgrammableStageDescriptor
                            module = fragment-module
                            entry_point = "main"
                    primitive_topology = self.primitive-topology
                    rasterization_state = &self.rasterization-state
                    color_states = self.color-states._items
                    color_states_length = (countof self.color-states)
                    depth_stencil_state = depth-stencil-state
                    vertex_state = self.vertex-state
                    sample_count = self.sample-count
                    sample_mask = self.sample-mask
                    alpha_to_coverage_enabled = self.alpha-to-coverage-enabled

            let new-pip =
                wgpu.device_create_render_pipeline (view device)
                    &local desc
            let result = (copy (storagecast (view new-pip)))
            'set pipeline-cache (hash self) new-pip
            result

    fn clear-cache ()
        'clear pipeline-cache

struct GfxState
    # webgpu state
    surface : wgpu.SurfaceId
    adapter : wgpu.AdapterId
    device : wgpu.DeviceId
    swap-chain : wgpu.SwapChainId
    queue : wgpu.QueueId
    command-encoder : (Option wgpu.CommandEncoderId)

    # pipeline JIT compilation
    current-pipeline : RenderPipelineBlueprint

global istate : (Option GfxState)

fn create-swap-chain (device surface)
    let width height = (HID.window.size)
    wgpu.device_create_swap_chain device surface
        &local wgpu.SwapChainDescriptor
            usage = wgpu.TextureUsage_OUTPUT_ATTACHMENT
            format = wgpu.TextureFormat.Bgra8UnormSrgb
            width = (width as u32)
            height = (height as u32)
            present_mode = wgpu.PresentMode.Fifo

fn default-pipeline (device)
    fn default-vertex-shader ()
        using import glsl
        using import glm
        buffer vertex-data :
            struct VertexData plain
                position : (array vec4)
            set = 0
            binding = 0
        gl_Position = vertex-data.position @ gl_VertexIndex

    fn default-fragment-shader ()
        using import glsl
        using import glm
        out fcolor : vec4
            location = 0
        fcolor = (vec4 1)

    let vshader = (static-compile-spirv 0x10000 'vertex (static-typify default-vertex-shader))
    vlen := (countof vshader) // 4
    let fshader = (static-compile-spirv 0x10000 'fragment (static-typify default-fragment-shader))
    flen := (countof fshader) // 4
    let vertex-module =
        wgpu.device_create_shader_module (storagecast (view device))
            wgpu.ShaderSource
                bytes = (vshader as rawstring as (pointer u32))
                length = vlen
    let fragment-module =
        wgpu.device_create_shader_module (storagecast (view device))
            wgpu.ShaderSource
                bytes = (fshader as rawstring as (pointer u32))
                length = flen

    local layoutb : PipelineLayoutBlueprint
    local vertex-bgroup : BindGroupLayoutBlueprint
    'set vertex-bgroup.bind-group-entries 0:usize
        wgpu.BindGroupLayoutEntry
            binding = 0
            visibility = wgpu.ShaderStage_VERTEX
            ty = wgpu.BindingType.StorageBuffer

    'set layoutb.bind-group-layouts 0:usize
        deref vertex-bgroup

    local color-states : (Array wgpu.ColorStateDescriptor)
    'emplace-append color-states
        format = wgpu.TextureFormat.Bgra8UnormSrgb
        alpha_blend =
            typeinit
                src_factor = wgpu.BlendFactor.SrcAlpha
                dst_factor = wgpu.BlendFactor.OneMinusSrcAlpha
                operation = wgpu.BlendOperation.Add
        color_blend =
            typeinit
                src_factor = wgpu.BlendFactor.SrcAlpha
                dst_factor = wgpu.BlendFactor.OneMinusSrcAlpha
                operation = wgpu.BlendOperation.Add
        write_mask = wgpu.ColorWrite_ALL

    RenderPipelineBlueprint
        layout = layoutb
        vertex-stage = vertex-module
        fragment-stage = fragment-module
        primitive-topology = wgpu.PrimitiveTopology.TriangleList
        rasterization-state =
            typeinit
                front_face = wgpu.FrontFace.Ccw
                cull_mode = wgpu.CullMode.None
                depth_bias = 0
                depth_bias_slope_scale = 0.0
                depth_bias_clamp = 0.0
        color-states = color-states
        # depth-stencil-state =
        vertex-state =
            typeinit
                index_format = wgpu.IndexFormat.Uint16
                vertex_buffers = null
                vertex_buffers_length = 0

fn init ()
    # TODO: use settings to configure
    wgpu.set_log_level wgpu.LogLevel.Error
    wgpu.set_log_callback
        fn "gfx-log" (level msg)
            # TODO: change to String once we have a constructor that doesn't take size.
            print "level:" level "-" (string msg)

    let surface = (HID.window.create-wgpu-surface)

    # adapter configuration
    # =====================
    local adapter : u64
    wgpu.request_adapter_async
        &local wgpu.RequestAdapterOptions
            power_preference = wgpu.PowerPreference.LowPower
            compatible_surface = surface
        | 2 4 8
        false
        # callback
        fn "on-adapter-available" (result adapterptr)
            adapterptr as:= (mutable pointer u64)
            @adapterptr = result
            ;

        &adapter

    # device configuration
    # =====================
    wgpu-limits = (wgpu.adapter_limits adapter)
    let device =
        wgpu.adapter_request_device adapter
            0 # extensions
            &wgpu-limits
            false # no shader validation - scopes already does it
            null

    # default pipeline
    # ================
    # TODO: fill this out with even more defaults
    let defpip =
        wgpu.RenderPipelineDescriptor
            primitive_topology = wgpu.PrimitiveTopology.TriangleList
            vertex_state =
                typeinit
                    index_format = wgpu.IndexFormat.Uint16

    istate =
        GfxState
            surface = surface
            adapter = adapter
            device = device
            queue = (wgpu.device_get_default_queue (storagecast (view device)))
            swap-chain = (create-swap-chain (storagecast (view device)) surface)
            current-pipeline = (default-pipeline (view device))

typedef+ wgpu.RenderPass
    fn finish (self)
        wgpu.render_pass_end_pass self

enum FrameError plain
    SwapChainOutdated
    LeftOverFrame

fn request-framebuffer (clear-color)
    let state = ('force-unwrap istate)
    let device = state.device
    let surface = state.surface

    if state.command-encoder
        raise FrameError.LeftOverFrame

    let cmd-encoder = (wgpu.device_create_command_encoder device null)

    let swapchain-image =
        wgpu.swap_chain_get_next_texture state.swap-chain
    if (swapchain-image.view_id == 0)
        state.swap-chain = (create-swap-chain device surface)
        raise FrameError.SwapChainOutdated

    let render-pass =
        wgpu.command_encoder_begin_render_pass (storagecast (view cmd-encoder))
            &local wgpu.RenderPassDescriptor
                color_attachments =
                    &local wgpu.RenderPassColorAttachmentDescriptor
                        attachment = swapchain-image.view_id
                        channel =
                            typeinit
                                load_op = wgpu.LoadOp.Clear
                                store_op = wgpu.StoreOp.Store
                                clear_value = (wgpu.Color (unpack clear-color))
                color_attachments_length = 1

    state.command-encoder = cmd-encoder

    render-pass

fn present ()
    let state = ('force-unwrap istate)
   
    if state.command-encoder
        let cmd-encoder = ('swap state.command-encoder none)
        local cmdbuf = (wgpu.command_encoder_finish ('force-unwrap cmd-encoder) null)
        # because finish "kills" the command encoder, but it still exists in the option we
        # swapped, we need to skip its descructor.
        lose cmd-encoder
        wgpu.queue_submit state.queue cmdbuf
        wgpu.swap_chain_present state.swap-chain
    else
        #???
        ;

fn reset-pipeline ()
    """"Clears all cached pipelines and resets it to a default state. Can be used to
        free VRAM when changing a level, for example.
    let state = ('force-unwrap istate)
    RenderPipelineBlueprint.clear-cache;
    PipelineLayoutBlueprint.clear-cache;
    BindGroupLayoutBlueprint.clear-cache;
    state.current-pipeline = (default-pipeline (view state.device))

fn... draw (render-pass, topology, vertex-count, instance-count = 0,
            first-vertex = 0, first-instance = 0)
    let state = ('force-unwrap istate)
    let pip = ('flush state.current-pipeline state.device)
    wgpu.render_pass_set_pipeline render-pass pip
    ;

fn set-clear-color (color)
    try
        let state = ('unwrap istate)
        state.clear-color = color
    else
        error "gfx module not initialized"

do
    let
        init
        request-framebuffer
        set-clear-color
        present
        draw
    locals;
