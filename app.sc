using import radlib.core-extensions
using import FunctionChain
using import glm

import .HID
import .timer
import .filesystem
import .gfx
let wgpu = (import .gfx.webgpu.wrapper)

global app-timer : timer.Timer
global bg-color : vec4 1 1 1 1

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

    # reset timer to account for initialization time
    app-timer = (timer.Timer)

    while (not (HID.window.received-quit-event?))
        HID.window.poll-events;
        'step app-timer
        update ('delta-time app-timer)

        let framebuffer =
            try (gfx.request-framebuffer bg-color)
            else (continue)

        draw framebuffer
        'finish framebuffer
        gfx.present;
    ;

fn set-background-color (c)
    bg-color = c

do
    let
        update
        draw
        init
        run
        set-background-color
    locals;
