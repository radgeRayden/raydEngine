using import glm
using import radlib.core-extensions

typedef+ mat-type
    fn display (self)
        using import itertools
        let T = (typeof self)
        local self = self
        for j in (range T.Rows)
            switch j
            case 0
                io-write! (default-styler style-sfxfunction "⎡ ")
            case (T.Rows - 1)
                io-write! (default-styler style-sfxfunction "⎣ ")
            default
                io-write! (default-styler style-sfxfunction "⎢ ")
            for i in (range T.Columns)
                io-write! (repr ((self @ i) @ j))
                io-write! ", "
            switch j
            case 0
                io-write! (default-styler style-sfxfunction "⎤ ")
            case (T.Rows - 1)
                io-write! (default-styler style-sfxfunction "⎦ ")
            default
                io-write! (default-styler style-sfxfunction "⎥ ")
            print;

fn 2drotate (v angle)
    let rcos rsin = (cos angle) (sin angle)
    vec2
        (rcos * v.x) - (rsin * v.y)
        (rsin * v.x) + (rcos * v.y)

define-scope transform
    fn... translation
    case (t : vec2,)
        let translate-transform =
            mat3
                \   1.0         0.0       0.0
                \   0.0         1.0       0.0
                \   t.x         t.y       1.0
    case (t : vec3,)
        let translate-transform =
            mat4
                vec4   1.0         0.0       0.0     0.0
                vec4   0.0         1.0       0.0     0.0
                vec4   0.0         0.0       1.0     0.0
                vec4   t.x         t.y       t.z     1.0

    # ----------------------------------------------------------
    fn... scaling
    case (factor : vec2,)
        let scale-transform =
            mat3
                \ factor.x      0.0       0.0
                \   0.0      factor.y     0.0
                \   0.0         0.0       1.0
    case (factor : vec3,)
        let scale-transform =
            mat4
                \ factor.x      0.0       0.0         0.0
                \   0.0      factor.y     0.0         0.0
                \   0.0         0.0     factor.z      0.0
                \   0.0         0.0       0.0         1.0
    # ----------------------------------------------------------

    fn... orientation
    case (angle : f32,)
        mat3
            \ (cos angle) (- (sin angle)) 0
            \ (sin angle) (cos angle)     0
            \      0             0        1
    case (angles : vec3,)
        let t = angles
        mat4
            vec4 (cos t.y) 0 (sin t.y) 0
            vec4 0 1 0 0
            vec4 (- (sin t.y)) 0 (cos t.y) 0
            vec4 0 0 0 1


    fn ortographic-projection (width height flip?)
        # https://www.scratchapixel.com/lessons/3d-basic-rendering/perspective-and-orthographic-projection-matrix/orthographic-projection-matrix
        # right, top
        let r t = (width / 2) (height / 2)
        # left, bottom
        let l b = -r -t
        # far, near
        let f n = 100 -100
        mat4
            vec4
                (2 / (r - l))
                0.0
                0.0
                -((r + l) / (r - l))
            vec4
                0.0
                (2 / (t - b))
                0.0
                0.0
            vec4
                -((t + b) / (t - b))
                0.0
                (-2 / (f - n))
                -((f + n) / (f - n))
            vec4 0.0 0.0 0.0 1.0

locals;
