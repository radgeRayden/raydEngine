using import radlib.core-extensions

using import struct
using import Option
using import glm
using import String
using import enum

let wgpu = (import ...foreign.wgpu-native)
import ...HID

struct GfxState
    # webgpu state
    surface : wgpu.SurfaceId
    adapter : wgpu.AdapterId
    device : wgpu.DeviceId
    swap-chain : wgpu.SwapChainId
    queue : wgpu.QueueId
    command-encoder : (Option wgpu.CommandEncoderId)

    # application state
    clear-color = (vec4 1 1 1 1)

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
    # TODO: store this where I can read it?
    local limits = (wgpu.adapter_limits adapter)
    let device =
        wgpu.adapter_request_device adapter
            0 # extensions
            &limits
            false # no shader validation - scopes already does it
            null

    istate =
        GfxState
            surface = surface
            adapter = adapter
            device = device
            queue = (wgpu.device_get_default_queue device)
            swap-chain = (create-swap-chain device surface)

typedef+ wgpu.RenderPass
    fn finish (self)
        wgpu.render_pass_end_pass self

enum FrameError plain
    SwapChainOutdated
    LeftOverFrame

fn request-framebuffer ()
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
        wgpu.command_encoder_begin_render_pass cmd-encoder
            &local wgpu.RenderPassDescriptor
                color_attachments =
                    &local wgpu.RenderPassColorAttachmentDescriptor
                        attachment = swapchain-image.view_id
                        channel =
                            typeinit
                                load_op = wgpu.LoadOp.Clear
                                store_op = wgpu.StoreOp.Store
                                clear_value = (wgpu.Color (unpack state.clear-color))
                color_attachments_length = 1

    state.command-encoder = cmd-encoder

    render-pass

fn present ()
    let state = ('force-unwrap istate)
   
    if state.command-encoder
        let cmd-encoder = ('swap state.command-encoder none)
        local cmdbuf = (wgpu.command_encoder_finish ('force-unwrap cmd-encoder) null)
        lose cmd-encoder
        wgpu.queue_submit state.queue cmdbuf
        wgpu.swap_chain_present state.swap-chain
    else
        #???
        ;

do
    let
        init
        request-framebuffer
        present
    locals;
