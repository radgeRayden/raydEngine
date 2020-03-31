using import glm
using import .radlib.core-extensions

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
                \   1.0         0.0       0.0     0.0
                \   0.0         1.0       0.0     0.0
                \   0.0         0.0       1.0     0.0
                \   t.x         t.y       t.z     1.0

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
        (mat4)


    fn ortographic-projection (width height)
        # https://www.scratchapixel.com/lessons/3d-basic-rendering/perspective-and-orthographic-projection-matrix/orthographic-projection-matrix
        let r-edge b-edge = (width / 2) (height / 2)
        let l-edge t-edge = -r-edge -b-edge
        let far near = -100 100
        mat4
            (2 / (r-edge - l-edge))
            0.0
            0.0
            -((r-edge + l-edge) / (r-edge - l-edge))
            0.0
            (2 / (t-edge - b-edge))
            0.0
            0.0
            -((t-edge + b-edge) / (t-edge - b-edge))
            0.0
            (-2 / (far - near))
            -((far + near) / (far - near))
            0.0
            0.0
            0.0
            1.0

locals;
