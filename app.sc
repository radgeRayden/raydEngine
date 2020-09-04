import .use
import HID
import timer
import filesystem
let gfx = (import gfx.webgpu.backend) # TODO: use the high level API?

let UpdateCallback = (pointer (function void f64)) # void <- (dt : f64)
let DrawCallback = (pointer (function void)) # void <- ()
let InitCallback = (pointer (function void)) # void <- ()

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
    # TODO: some sort of "begin frame" thing from gfx, which will request the swapchain image?

    # after we loaded the necessary modules, it's safe to call user defined init
    init;

    while (not (HID.window.received-quit-event?))
        HID.window.poll-events;
        draw;
    ;
locals;
