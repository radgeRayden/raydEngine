using import glm
using import Array
using import struct
using import Rc
import ..HID
import ..gfx
import ..timer
import ..2Ddraw
import ..filesystem
using import ..PRNG
using import ..math
gl := (import ..foreign.gl)

HID.init (HID.GLContextOptions)
    HID.WindowOptions
        title = "gfx-02: primitives"

gfx.init;
filesystem.init;

let vert-shader frag-shader = (2Ddraw.get-default-shader-sources)
global main-shader = (gfx.Shader frag-shader vert-shader)
'bind! main-shader

inline sizeof-Array (arr)
    let T = (typeof arr)
    let ET = T.ElementType
    * (sizeof ET) (countof arr)

global batch = (2Ddraw.PrimitiveBatch)
fn rectangle (x y width height rotation color)
    'push-quad batch x y width height rotation color

fn circle (x y radius segments color)
    'push-circle batch x y radius segments color

fn line (points thickness color)
    'push-line batch points thickness color

struct Rectangle plain
    x : i32
    y : i32 = 300
    width : i32 = 30
    height : i32 = 30
    rotation : f32
  
global rect : Rectangle
global rng = (random.RNG 0)
fn update (dt)
    rect.x += ((90 * dt) as i32)

fn calc-segments (radius)
    radius as:= f32
    circumference := (2 * pi * radius)
    # TODO: this rule seems suboptimal.
    max (circumference // 15) 15

global radius = 100

let outer-radius = 120
global outer-line-circle : (array vec2 51)

for i in (range 50)
    let origin = (vec2 300 400)
    outer-line-circle @ i =
        origin + (2drotate (vec2 outer-radius 0) (((2 * pi) / 50) * (i as f32)))
outer-line-circle @ 50 = ((vec2 300 400) + (vec2 outer-radius 0))

import ..resource-loader
global lain-texture =
    gfx.2DTexture (resource-loader.ImageData "assets/lain.jpg")

# FONT RENDERING - REMOVE LATER
import UTF-8
using import .fnt-types
using import Map
using import itertools

struct BitmapFont
    font-image : gfx.2DTexture
    char-info : (Map i32 FNTChar)

    inline collect-glyphs (self input collectfn)
        ->> input UTF-8.decoder
            map
                inline (code-point)
                    let glyph =
                        try
                            'get self.char-info code-point
                        else
                            try ('get self.char-info (UTF-8.char "?"))
                            except (ex)
                                error "No question mark character in font, can't replace unknown character."
                    collectfn glyph
            drain
BitPotionExt := (import .assets.BitPotionExt)
let font-spec = (BitPotionExt.font)
let img = (resource-loader.ImageData (.. "assets/" font-spec.page.file))
local font-texture = (gfx.2DTexture img)
global font =
    BitmapFont
        font-image = (Rc.clone font-texture)

for c in font-spec.chars
    'set font.char-info c.id c

global glyph-batch = (2Ddraw.SpriteBatch (Rc.clone font.font-image) 1000)
global char-offset = 0
fn draw ()
    gfx.clear (vec4 0.14 0.14 0.14 1.0)
    let window-width window-height = (HID.window.size)
    # NOTE: this can be moved to 2Ddraw
    let world-transform =
        *
            transform.ortographic-projection 1280 720
            # set origin to top left corner
            transform.translation (vec3 (- (1280 / 2)) (- (720 / 2)) 0)
    'update-uniform main-shader "transform" world-transform

    rectangle 0 600 100 100 0 (vec4 1 1 1 1)
    # 'set-texture batch (Rc.clone lain-texture)
    rectangle rect.x rect.y rect.width rect.height rect.rotation (vec4 1 1 1 0.5)
    # 'set-texture batch none
    circle 300 400 radius (calc-segments radius) (vec4 1 1 0 1)
    line
        arrayof vec2
            vec2 100 400
            vec2 200 390
            vec2 300 400
            vec2 400 390
            vec2 500 400
            vec2 600 390
        thickness = 3
        color = (vec4 1 0.7 0.2 1)
    line outer-line-circle 10 (vec4 1 0.5 0 1)

    'flush batch

    char-offset = 0
    'clear glyph-batch
    global frame-counter = 0
    'collect-glyphs font (.. "frame: " (tostring (deref frame-counter)) " " 2Ddraw.debug-text)
        fn "add-glyph" (glyph)
            let font-scale = 3.0
            'add glyph-batch
                x = (char-offset + (((glyph.xoffset as f32) * font-scale) as i32))
                y = (30 + ((glyph.yoffset as f32) * font-scale))
                quad =
                    2Ddraw.Quad
                        x = (glyph.x as f32)
                        y = (glyph.y as f32)
                        w = (glyph.width as f32)
                        h = (glyph.height as f32)
                orientation = 0
                scalex = font-scale
                scaley = font-scale
            char-offset += (((glyph.xadvance as f32) * font-scale) as i32)
    frame-counter += 1
    'draw glyph-batch

global game-timer : timer.Timer

HID.window.kickoff-event-cycle
    fn "frame-callback" ()
        KeyCode := HID.keyboard.KeyCode

        'step game-timer
        update ('delta-time game-timer)
        draw;
        ;
