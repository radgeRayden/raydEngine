using import radlib.core-extensions

import .use
import HID
import timer
import filesystem
let gfx = (import gfx.webgpu.backend) # TODO: use the high level API?
let wgpu = (import gfx.webgpu.wrapper)

let UpdateCallback = (pointer (function void f64))
let DrawCallback = (pointer (function void (viewof wgpu.CommandEncoderId 1) (viewof wgpu.RenderPassId 1)))
let InitCallback = (pointer (function void))

fn... run (init : InitCallback, update : UpdateCallback, draw : DrawCallback)
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

    # after we loaded the necessary modules, it's safe to call user defined init
    init;

    while (not (HID.window.received-quit-event?))
        HID.window.poll-events;
        update 0
        # TODO: some sort of "begin frame" thing from gfx, which will request the swapchain image?
        let state = ('force-unwrap gfx.backend)

        let device = state.device
        let cmd-encoder = (wgpu.device_create_command_encoder device null)
        let swapchain-image =
            wgpu.swap_chain_get_next_texture state.swap-chain
        if (swapchain-image.view_id == 0)
            gfx.update-render-area;
            repeat;

        let render-pass =
            wgpu.command_encoder_begin_render_pass cmd-encoder
                &local wgpu.RenderPassDescriptor
                    color_attachments =
                        &local wgpu.RenderPassColorAttachmentDescriptor
                            attachment = swapchain-image.view_id
                            load_op = wgpu.LoadOp.Clear
                            store_op = wgpu.StoreOp.Store
                            clear_color = (wgpu.Color (unpack state.clear-color))
                    color_attachments_length = 1

        draw (view cmd-encoder) (view render-pass)

        wgpu.render_pass_end_pass render-pass
        local cmdbuf = (wgpu.command_encoder_finish cmd-encoder null)
        wgpu.queue_submit state.queue cmdbuf
        wgpu.swap_chain_present state.swap-chain
    ;
locals;
