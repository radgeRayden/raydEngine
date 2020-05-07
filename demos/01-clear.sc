using import glm
import ..HID
import ..gfx
import ..timer
import ..PRNG

global color-timer : timer.Timer
global rng = (PRNG.random.RNG (timer.clock-time))

fn elapsed-time ()
    let t = ((timer.clock-time) - color-timer.last-update)
    timer.ticks->seconds t

fn nrandom ()
    (PRNG.random.normalize (rng)) as f32

global clear-color = (vec3 0.14 0.14 0.14)
global next-color : vec3

fn choose-color ()
    next-color.r = (nrandom)
    next-color.g = (nrandom)
    next-color.b = (nrandom)

HID.init (HID.GLContextOptions) (HID.WindowOptions)
HID.window.change-title "gfx: clear (ESC to exit)"
gfx.init;

HID.on-key-event =
    fn "key" (event)
        raising Error
        using HID.keyboard
        let action keycode = event.action event.keycode
        # esc to close
        if ((action == KeyAction.PRESS) and (keycode == KeyCode.ESCAPE))
            HID.window.close;

        # alt+enter to fullscreen
        if ((action == KeyAction.PRESS) and (mod-alt? event.modifiers) and (keycode == KeyCode.ENTER))
            HID.window.toggle-fullscreen;

fn update ()
    let time-amount = (min (elapsed-time) 1.0:f64)

    let color = (mix clear-color next-color time-amount)
    gfx.clear (vec4 color.rgb 1.0)

    if (time-amount == 1.0)
        'step color-timer
        clear-color = next-color
        choose-color;
    ;

using HID.window
using import ..radlib.platform
while (not (received-quit-event?))
    poll-events;
    update;
    sleep 0.001
    swap-buffers;
