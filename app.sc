using import radlib.core-extensions
using import FunctionChain

import .use
import HID
import timer
import filesystem
let gfx = (import gfx.webgpu.backend) # TODO: use the high level API?
let wgpu = (import foreign.wgpu-native)

fnchain update
fnchain draw
fnchain init

fn... run ()
    filesystem.init;
    # TODO: check config.sc
    HID.init (HID.WindowOptions (visible? = true)) (HID.GfxAPI.WebGPU)

    vvv mutate HID.on-key-event
    fn "key-callback" (ev)
        # code here...
        using HID.keyboard
        if (keybind ev KeyModifier.ALT KeyCode.ENTER)
            HID.window.toggle-fullscreen;

        if (keybind ev KeyCode.ESCAPE)
            HID.window.close;

    gfx.init;

    # call user defined init after initializing modules
    init;

    inline wrap-draw (f)
        let state = ('force-unwrap gfx.backend)
        let device = state.device
        let cmd-encoder = (wgpu.device_create_command_encoder device null)
        let swapchain-image =
            wgpu.swap_chain_get_next_texture state.swap-chain
        if (swapchain-image.view_id == 0)
            gfx.update-render-area;
            return;

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

        f (view render-pass)

        wgpu.render_pass_end_pass render-pass
        local cmdbuf = (wgpu.command_encoder_finish cmd-encoder null)
        wgpu.queue_submit state.queue &cmdbuf 1
        wgpu.swap_chain_present state.swap-chain

    HID.window.poll-events;
    # clear the screen once
    gfx.update-render-area;
    wrap-draw (inline (...) ())
    while (not (HID.window.received-quit-event?))
        HID.window.poll-events;
        update 0
        # TODO: some sort of "begin frame" thing from gfx, which will request the swapchain image?
        wrap-draw draw
    ;
locals;
