import ..HID
import ..gfx
import ..resource-loader
import ..filesystem
import ..timer
import ..PRNG
import ..2Ddraw
using import ..math
using import Rc
using import Array
using import struct
using import glm

let WINDOW_WIDTH WINDOW_HEIGHT = 1280 720
HID.init
    HID.GLContextOptions;

    HID.WindowOptions
        width = WINDOW_WIDTH
        height = WINDOW_HEIGHT
        title = "waiting..."
        vsync? = false

gfx.init;
filesystem.init;

# ----------------------------------------------------------------------------------------------
# things that are here because I didn't have them implemented but should be in their own modules

fn string-format (format args...)
    let MAX_BUF_SIZE = 1024
    local buf = ((array i8 MAX_BUF_SIZE))
    let C.stdio =
        do
            let scope = (include "stdio.h")
            using scope.extern
            locals;
    let result =
        C.stdio.snprintf &buf MAX_BUF_SIZE format args...
    if ((result > 0) and (result < MAX_BUF_SIZE))
        return (string &buf result)
    else
        error "the string too big for they gotdamn feet"

global rng = (PRNG.random.RNG (timer.clock-time))
fn nrandom ()
    PRNG.random.normalize (rng)

let vert-shader frag-shader = (2Ddraw.get-default-shader-sources)

global main-shader = (gfx.Shader frag-shader vert-shader)
'bind! main-shader

# ----------------------------------------------------------------------------------------------
# actual code for this demo starts here
let
    MAX_SPRITE_COUNT = 80000
    SPRITE_MAX_SCALE = 1.0


global game-timer : timer.Timer

local blowfish : gfx.2DTexture
    resource-loader.ImageData "assets/blowfish.png"
global sprite-batch = (2Ddraw.SpriteBatch blowfish MAX_SPRITE_COUNT)

struct Sprite plain
    x : i32
    y : i32
    rotation : f32 = 1.0
    rotation-speed : f32
    scale : f32 = 1.0
    scale-speed : i32
    speed : i32
    direction-x : f32
    direction-y : f32


global sprites : (Array Sprite)
for i in (range MAX_SPRITE_COUNT)
    'append sprites
        # TODO: have (rng range) return a value of the same type as the input
        Sprite
            x = ((rng 0 WINDOW_WIDTH) as i32)
            y = ((rng 0 WINDOW_HEIGHT) as i32)
            rotation-speed = ((rng -200 200) as f32)
            scale-speed =
                as
                    rng
                        (-2.0 * SPRITE_MAX_SCALE) as i32
                        (2 * SPRITE_MAX_SCALE) as i32
                    i32
            speed = ((rng 50 500) as i32)
            direction-x = ((-1 + (2 * (nrandom))) as f32)
            direction-y = ((-1 + (2 * (nrandom))) as f32)

global frame-count = 0:u64
global perf-measure : f64
fn draw ()
    gfx.clear;
    let world-transform =
        *
            transform.ortographic-projection 1280 720
            # set origin to top left corner
            transform.translation (vec3 (- (1280 / 2)) (- (720 / 2)) 0)
    'update-uniform main-shader "transform" world-transform
    'draw sprite-batch
    ;

global timestep-samples = 0
global time-accum = 0:f64
fn update! (dt)
    # perf counter stuff
    frame-count += 1
    #

    time-accum += dt
    timestep-samples += 1
    if (time-accum > 1)
        average-frametime := (time-accum / (timestep-samples as f64))
        average-fps := (1 / average-frametime)
        HID.window.change-title!
            string-format "Average FPS: %.2f | %.2fms" average-fps (average-frametime * 1000)
        time-accum = 0
        timestep-samples = 0

    # spritebatch management
    let begin-time = ('run-time-real game-timer)
    'clear sprite-batch
    'add sprite-batch
        x = 100
        y = 100
        orientation = 0
        scalex = 1.0
        scaley = 1.0
    # for i in (range MAX_SPRITE_COUNT)
    #     let sprite = (sprites @ i)
    #     let scale-amount = (((sprite.scale-speed as f64) * dt) as f32)
    #     let scale-amount scale-speed =
    #         if (or
    #             ((sprite.scale + scale-amount) <= -SPRITE_MAX_SCALE)
    #             ((sprite.scale + scale-amount) >= SPRITE_MAX_SCALE))
    #             _ -scale-amount (deref -sprite.scale-speed)
    #         else
    #             _ scale-amount (deref sprite.scale-speed)
    #     let scale = (sprite.scale + scale-amount)

    #     let move-amount-x = (sprite.direction-x * (sprite.speed as f32) * dt)
    #     let new-location-x = (sprite.x + (move-amount-x as i32))
    #     let new-location-x direction-x =
    #         if ((new-location-x < 0) or (new-location-x > WINDOW_WIDTH))
    #             move-amount-x := -move-amount-x
    #             _
    #                 sprite.x + (move-amount-x as i32)
    #                 -sprite.direction-x
    #         else
    #             _ new-location-x (deref sprite.direction-x)

    #     sprite =
    #         Sprite
    #             rotation = ((((sprite.rotation * 57.2958) + sprite.rotation-speed * dt) * 0.0174533:f64) as f32)
    #             scale = scale
    #             scale-speed = scale-speed
    #             x = new-location-x
    #             y = sprite.y
    #             direction-x = direction-x
    #     'add! sprite-batch
    #         x = sprite.x
    #         y = sprite.y
    #         orientation = sprite.rotation
    #         scalex = sprite.scale
    #         scaley = sprite.scale
    let end-time = ('run-time-real game-timer)
    perf-measure += (end-time - begin-time)

HID.window.kickoff-event-cycle
    fn "frame-callback" ()
        KeyCode := HID.keyboard.KeyCode
        'step game-timer
        dt := ('delta-time game-timer)
        update! dt
        draw;
        ;

print (perf-measure / (frame-count as f64))
