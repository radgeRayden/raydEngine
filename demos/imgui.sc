ig := (import ..imgui)
import ..HID
import ..gfx
import ..2Ddraw

HID.init (HID.WindowOptions) (HID.GLContextOptions)
gfx.init;

ig.init (HID.get-GLFW-window)

let vert-shader frag-shader = (2Ddraw.get-default-shader-sources)
global main-shader = (gfx.Shader frag-shader vert-shader)
'bind! main-shader

HID.on-key-event =
    fn "key-event" (ev)
        raising Error
        using HID.keyboard

        let code mods = KeyCode KeyModifier

        if (released? ev KeyCode.ESCAPE)
            HID.window.close;

        if (keybind ev mods.ALT code.ENTER)
            HID.window.toggle-fullscreen;

global pbatch = (2Ddraw.PrimitiveBatch)
fn draw ()
    using import ..math
    using import glm
    let window-width window-height = (HID.window.size)
    # NOTE: this can be moved to 2Ddraw
    let world-transform =
        *
            transform.ortographic-projection 1280 720
            # set origin to top left corner
            transform.translation (vec3 (- (1280 / 2)) (- (720 / 2)) 0)
    'update-uniform main-shader "transform" world-transform

    'push-quad pbatch 100 100 125 125
    'flush pbatch


using import radlib.platform
while (not (HID.window.received-quit-event?))
    HID.window.poll-events;
    gfx.clear;
    draw;

    using ig
    with-gui
        global show-demo-window = true
        if show-demo-window
            ig.ShowDemoWindow &show-demo-window
    sleep 0.001
    HID.window.swap-buffers;
