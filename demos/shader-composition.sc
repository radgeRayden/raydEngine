import ..2Ddraw
using import glsl
using import glm

Bindings := 2Ddraw.StandardAttrBindings
inline make-shader (vert-fn frag-fn)
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
                vert-fn
                    do
                        let position texcoords color
                        locals;
                    transform

            out out-color : vec4
            uniform batch-texture : sampler2D
            fn fs-main ()
                frag-fn
                    do
                        let out-color texcoords
                        locals;
                    batch-texture

            _
                compile-glsl 450 'vertex (typify vs-main)
                compile-glsl 450 'fragment (typify fs-main)

let v f =
    do
        uniform some-vec : vec4
        make-shader
            inline (attrs transform)
                gl_Position = (transform * (vec4 attrs.position 0 1.0))
                attrs.color.out = attrs.color.in
                attrs.texcoords.out = attrs.texcoords.in

            inline (attrs tex)
                attrs.out-color = ((texture tex attrs.texcoords.in) + some-vec)

print v f

inline chain-effects (input effects...)
    va-lfold input
        inline (__ effect result)
            let fun t = effect.fun effect.t
            mix (fun input) result t
        effects...

inline effect (input fun t)
    mix (fun input) input t

using import itertools
print
    do
        let input = (vec4 0.14 0.14 0.14 1.0)
        let funA =
            (x) -> (x + (vec4 0.1 0.1 0.1 0.0))
        let funB =
            (x) -> (x - (vec4 0.1 0.1 0.1 0.0))

        -->
            effect input funA 0.5
            effect __ funB 0.2

inline effect (fun t)
    do
        let fun t = fun t
        locals;

print
    chain-effects
        vec4 0.14 0.14 0.14 1.0
        effect
            fun =
                inline (x)
                    x + (vec4 0.1 0.1 0.1 0.0)
            t = 0.5
        effect
            fun =
                inline (x)
                    x - (vec4 0.1 0.1 0.1 0.0)
            t = 0.2
