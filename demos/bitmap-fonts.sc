import ..HID
import ..gfx
import ..2Ddraw
import ..filesystem
import ..resource-loader
using import ..math
using import struct
using import itertools
using import Map
using import Array
using import enum
using import glm
import UTF-8
using import .fnt-types

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

fn add-code-points (str)
    local charmap = ((Array i32))
    ->> str
        UTF-8.decoder
        map
            inline (code-point)
                'append charmap code-point
        drain
    charmap

filesystem.init;
HID.init (HID.GLContextOptions) (HID.WindowOptions)
gfx.init;

#FIXME: should load the code on demand, at runtime.
BitPotionExt := (import .assets.BitPotionExt)
let font-spec = (BitPotionExt.font)
let img = (resource-loader.ImageData (.. "assets/" font-spec.page.file))
local font-texture = (gfx.2DTexture img)
local font =
    BitmapFont
        font-image = ('__clone font-texture)

for c in font-spec.chars
    'set font.char-info c.id c

global glyph-batch = (2Ddraw.SpriteBatch font.font-image 1000)
global char-offset = 0
'collect-glyphs font "pfirsich #againstbirds: öööööööööääääääääääääääüüüüüüüüüüßßßßßßßßßßß"
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

Bindings := 2Ddraw.StandardAttrBindings
let vert-shader frag-shader =
    do
        using import struct
        using import glsl

        in position : vec2
            location = (Bindings.Position as i32)
        inout texcoords : vec2
            location = (Bindings.TextureCoordinates as i32)
        inout color : vec4
            location = (Bindings.Color as i32)
        uniform transform : mat4

        fn vs-main ()
            gl_Position = (transform * (vec4 position 0 1.0))
            color.out = color.in
            texcoords.out = texcoords.in

        out out-color : vec4
        uniform batch-texture : sampler2D
        fn fs-main ()
            out-color = (texture batch-texture texcoords.in)

        _
            compile-glsl 450 'vertex (typify vs-main)
            compile-glsl 450 'fragment (typify fs-main)

global main-shader = (gfx.Shader frag-shader vert-shader)
print vert-shader
'bind! main-shader
HID.window.kickoff-event-cycle
    fn "frame" ()
        gfx.clear;
        'draw! glyph-batch
        let world-transform =
            *
                transform.ortographic-projection 1280 720
                # set origin to top left corner
                transform.translation (vec3 (- (1280 / 2)) (- (720 / 2)) 0)
        'update-uniform main-shader "transform" world-transform
        ;
none
