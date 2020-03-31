ig := (import ..imgui)
import ..HID
import ..gfx
import ..2Ddraw

HID.init (HID.GLContextOptions) (HID.WindowOptions)
gfx.init;

ig.init ('force-unwrap HID.app-window)

let vert-shader frag-shader = (2Ddraw.get-default-shader-sources)
global main-shader = (gfx.Shader frag-shader vert-shader)
'bind! main-shader

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


HID.window.kickoff-event-cycle
    fn "frame" ()
        gfx.clear;
        draw;

        using ig
        with-gui
            global show-demo-window = true
            if show-demo-window
                ig.ShowDemoWindow &show-demo-window
