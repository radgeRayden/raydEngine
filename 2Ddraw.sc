using import struct
using import enum
using import Array
using import glm
using import Rc
using import Option

import .gfx
import .resource-loader
let gl = (import .foreign.gl)
import .math

global debug-text : string = ""

inline sizeof-Array (arr)
    let T = (typeof arr)
    let ET = T.ElementType
    * (sizeof ET) (countof arr)

struct Quad plain
    x : f32
    y : f32
    w : f32
    h : f32

enum StandardAttrBindings plain
    Position
    TextureCoordinates
    Color

    inline __imply (T otherT)
        static-if (otherT < integer)
            static-if ((sizeof otherT) == (sizeof T))
                inline (self)
                    bitcast self otherT
            else
                inline (self)
                    self as otherT
        else
            static-error "Can't convert to non integer type."

struct 2DStdVertexAttributes plain
    position : vec2
    texcoords : vec2
    color : vec4

# This function returns a white 1x1 texture that we use as a placeholder
# while drawing untextured primitives. This allows us to only use one shader for both
# textured and untextured, since white can be multiplied by any color without
# change. Texture is created the first time this function is called then the same one is returned
# every time.
fn white-1px-texture ()
    using import Option
    using import Array
    global white-texture : (Option gfx.2DTexture)
    if (not white-texture)
        local data : (Array u8)
        # set 4 bytes to 255, opaque white
        'resize data 4
        for b in data
            b = 0xFF
        let image-data = (resource-loader.ImageData data 1 1 4)
        white-texture = (gfx.2DTexture image-data)
    'force-unwrap white-texture

struct PrimitiveBatch
    vertexbuffer : gfx.VertexBuffer
    indexbuffer : gfx.IndexBuffer
    vertices : (Array 2DStdVertexAttributes)
    indices : (Array u16)
    current-texture : gfx.2DTexture
    _vertexbuffer-offset : i32
    _indexbuffer-offset : i32

    inline __typecall (cls)
        # let's start with 10k vertices
        super-type.__typecall cls
            vertexbuffer = (gfx.VertexBuffer (10000 * (sizeof 2DStdVertexAttributes)))
            indexbuffer = (gfx.IndexBuffer 20000) # 2x should be good?
            current-texture = (Rc.clone (white-1px-texture))

    # We conclude a batch implicitly when certain state changes, like the currently used texture.
    # By setting the current texture to `none`, we communicate the
    # intent of drawing untextured primitives.
    fn set-texture (self _texture)
        if (not (self.current-texture == _texture))
            'flush self
            self.current-texture = _texture
        ;

    fn flush (self)
        # ignore empty flush
        if (((countof self.vertices) == 0) or ((countof self.indices) == 0))
            return;
        VertexAttributes := 2DStdVertexAttributes
        Bindings := StandardAttrBindings
        let offset =
            (member) -> ((offsetof VertexAttributes member) as i64)
        stride := (sizeof VertexAttributes)

        # when flushing we update our buffers such that we append data to the end of the previous (now
        # soon to be invalid) draw data until we run out of space, at which point we reset the whole
        # buffer. The reason to do this is that this way we use the whole buffer optimally and mostly
        # avoid stalling issues because we updated the same region of the buffer too frequently. Later
        # this approach is to be substituted by persistently mapped buffers.
        let voffset ioffset = self._vertexbuffer-offset self._indexbuffer-offset
        let vsize isize = (sizeof-Array self.vertices) (sizeof-Array self.indices)
        if ((voffset + vsize) > self.vertexbuffer._size)
            voffset = 0
        if ((ioffset + isize) > self.indexbuffer._size)
            ioffset = 0

        'bind-to-point self.vertexbuffer
            \ Bindings.Position (voffset + (offset 'position)) stride
        'bind-to-point self.vertexbuffer
            \ Bindings.TextureCoordinates (voffset + (offset 'texcoords)) stride
        'bind-to-point self.vertexbuffer
            \ Bindings.Color (voffset + (offset 'color)) stride

        gl.VertexAttribFormat Bindings.Position 2 gl.GL_FLOAT false 0
        gl.VertexAttribFormat Bindings.TextureCoordinates 2 gl.GL_FLOAT false 0
        gl.VertexAttribFormat Bindings.Color 4 gl.GL_FLOAT false 0

        gl.EnableVertexAttribArray Bindings.Position
        gl.EnableVertexAttribArray Bindings.TextureCoordinates
        gl.EnableVertexAttribArray Bindings.Color

        'bind self.current-texture

        debug-text =
            ..
                "vsize: "
                tostring vsize
                " isize: "
                tostring isize
                " voffset: "
                tostring voffset
                " ioffset: "
                tostring ioffset
        try
            'update self.vertexbuffer self.vertices vsize voffset
            'update self.indexbuffer self.indices isize ioffset
            'draw-indexed self.vertexbuffer (countof self.indices) ioffset self.indexbuffer

            # after uploading the buffers we can advance our offsets
            voffset += (vsize as i32)
            ioffset += (isize as i32)

            'clear self.vertices
            'clear self.indices
        except (ex)
            # TODO: destroy current buffer and recreate with necessary size
            assert false
            ;
    fn... push-quad
    case (self x y width height rotation color)
        # we need to change texture _before_ appending any data otherwise the current
        # update will fall under old settings.
        # untextured mode
        'set-texture self (Rc.clone (white-1px-texture))

        VertexAttributes := 2DStdVertexAttributes
        origin := (vec2 x y)
        rotation as:= f32
        let rcos rsin = (cos rotation) (sin rotation)
        inline 2drotate (v)
            vec2
                (rcos * v.x) - (rsin * v.y)
                (rsin * v.x) + (rcos * v.y)

        vertex-offset := (countof self.vertices)
        # top left
        'append self.vertices
            VertexAttributes
                position = origin
                texcoords = (vec2 0 0)
                color = color
        # top right
        'append self.vertices
            VertexAttributes
                position = (origin + (2drotate (vec2 width 0)))
                texcoords = (vec2 1 0)
                color = color
        # bottom left
        'append self.vertices
            VertexAttributes
                position = (origin + (2drotate (vec2 0 height)))
                texcoords = (vec2 0 1)
                color = color
        # bottom right
        'append self.vertices
            VertexAttributes
                position = (origin + (2drotate (vec2 width height)))
                texcoords = (vec2 1 1)
                color = color

        top-left := vertex-offset
        top-right := (vertex-offset + 1)
        bottom-left := (vertex-offset + 2)
        bottom-right := (vertex-offset + 3)
        'append self.indices (top-left as u16)
        'append self.indices (bottom-left as u16)
        'append self.indices (bottom-right as u16)
        'append self.indices (bottom-right as u16)
        'append self.indices (top-right as u16)
        'append self.indices (top-left as u16)
        ;
    case (self x y width height)
        this-function self x y width height 0 (vec4 1 1 1 1)

    fn push-circle (self x y radius segments color)
        # untextured mode
        'set-texture self (Rc.clone (white-1px-texture))

        VertexAttributes := 2DStdVertexAttributes
        center-index := (countof self.vertices)
        origin := (vec2 x y)
        add-index := (idx) -> ('append self.indices (idx as u16))
        # center of the circle
        'append self.vertices
            VertexAttributes
                position = (vec2 x y) # origin
                color = color

        angle-slice := ((2 * pi) / (segments as f32))
        for i in (range segments)
            using math
            'append self.vertices
                VertexAttributes
                    position = (origin + (2drotate (vec2 radius 0) (angle-slice * (i as f32)))) # origin
                    color = color

        for i in (range segments)
            # always starts from center
            first-segment-start := (center-index + 1)
            add-index center-index
            add-index (first-segment-start + i)
            add-index (first-segment-start + ((i + 1) % segments))
        ;
    fn push-line (self points thickness color)
        # untextured mode
        'set-texture self (Rc.clone (white-1px-texture))

        VertexAttributes := 2DStdVertexAttributes
        using math
        # make it a reference
        local points = points
        let vertex-offset = (countof self.vertices)
        for i in (range ((countof points) - 1))
            using math
            this-point := (points @ i)
            next-point := (points @ (i + 1))

            dir := (normalize (next-point - this-point))
            perp := (2drotate dir (pi / 2))
            'append self.vertices
                VertexAttributes
                    position = (this-point + (perp * (thickness / 2)))
                    color = color
            'append self.vertices
                VertexAttributes
                    position = (this-point - (perp * (thickness / 2)))
                    color = color

        this-point := (points @ ((countof points) - 1))
        prev-point := (points @ ((countof points) - 2))

        dir := (normalize (this-point - prev-point))
        perp := (2drotate dir (pi / 2))
        'append self.vertices
            VertexAttributes
                position = (this-point + (perp * (thickness / 2)))
                color = color
        'append self.vertices
            VertexAttributes
                position = (this-point - (perp * (thickness / 2)))
                color = color
        for i in (range ((countof points) - 1))
            let segment-start = (vertex-offset + (i * 2))
            # +perp, -perp, idem for next point
            let left right nleft nright =
                segment-start
                segment-start + 1
                segment-start + 2
                segment-start + 3
            'append self.indices (left as u16)
            'append self.indices (right as u16)
            'append self.indices (nright as u16)
            'append self.indices (nright as u16)
            'append self.indices (nleft as u16)
            'append self.indices (left as u16)
        ;

struct SpriteBatch
    _texture : gfx.2DTexture
    _sprites : (Array (array 2DStdVertexAttributes 4))
    _vertexbuffer : gfx.VertexBuffer
    _indexbuffer : gfx.IndexBuffer
    _dirty? : bool
    sprite-limit : i32

    inline __typecall (cls _texture sprite-limit)
        # index buffer never changes (while we don't have batch resizing),
        # so we can just generate it at the start and leave it be.
        # TODO: I don't like that I have to pass these sizes around!
        let index-count = (* 6 sprite-limit)
        local indices = ((Array u16))
        'resize indices index-count
        # fill in index data
        for i in (range sprite-limit)
            let vertex-offset = (i * 4)
            top-left := vertex-offset
            top-right := (vertex-offset + 1)
            bottom-left := (vertex-offset + 2)
            bottom-right := (vertex-offset + 3)

            let index-offset = (i * 6)
            indices @ index-offset       = (top-left as u16)
            indices @ (index-offset + 1) = (bottom-left as u16)
            indices @ (index-offset + 2) = (bottom-right as u16)
            indices @ (index-offset + 3) = (bottom-right as u16)
            indices @ (index-offset + 4) = (top-right as u16)
            indices @ (index-offset + 5) = (top-left as u16)

        let index-buffer = (gfx.IndexBuffer ((sizeof u16) * index-count))
        try
            'update index-buffer indices (sizeof-Array indices) 0
        except (ex)
            print ex
            # FIXME
            assert false
        using import Rc
        super-type.__typecall cls
            _texture = _texture
            _vertexbuffer = (gfx.VertexBuffer ((sizeof 2DStdVertexAttributes) * 4 * sprite-limit))
            _indexbuffer = index-buffer
            sprite-limit = sprite-limit

    # add a quad to the spritebatch
    # TODO: add variants for transforming origin, shearing, or using different UVs
    # NOTE: quad dimensions are in pixels
    fn... add (self x y quad orientation scalex scaley)
        using math

        scalex as:= f32
        scaley as:= f32
        orientation as:= f32

        _texture := self._texture
        let id = (countof self._sprites)

        # make a quad and transform it on CPU
        let width height = (_texture.width as f32) (_texture.height as f32)
        let uv-quad =
            # convert to normalized
            Quad
                x = (quad.x / width)
                y = (quad.y / height)
                w = (quad.w / width)
                h = (quad.h / height)

        let origin = (vec2 x y)
        VertexAttributes := 2DStdVertexAttributes
        color := (vec4 1 1 1 1)
        let vertices =
            arrayof 2DStdVertexAttributes
                # top left
                VertexAttributes
                    position = origin
                    texcoords = (vec2 uv-quad.x uv-quad.y)
                    color = color
                # top right
                VertexAttributes
                    position = (origin + (math.2drotate (vec2 (quad.w * scalex) 0) orientation))
                    texcoords = (vec2 (uv-quad.x + uv-quad.w) uv-quad.y)
                    color = color
                # bottom left
                VertexAttributes
                    position = (origin + (math.2drotate (vec2 0 (quad.h * scaley)) orientation))
                    texcoords = (vec2 uv-quad.x (uv-quad.y + uv-quad.h))
                    color = color
                # bottom right
                VertexAttributes
                    position = (origin + (math.2drotate (vec2 (quad.w * scalex) (quad.h * scaley)) orientation))
                    texcoords = (vec2 (uv-quad.x + uv-quad.w) (uv-quad.y + uv-quad.h))
                    color = color
        'append self._sprites vertices
        # signal that we need to update buffers next time we draw
        self._dirty? = true
        id
    case (self x y orientation scalex scaley)
        this-function
            self
            x
            y
            Quad
                x = 0
                y = 0
                w = (self._texture.width as f32)
                h = (self._texture.height as f32)
            orientation
            scalex
            scaley


    fn clear (self)
        'clear self._sprites
        self._dirty? = true
        ;

    fn draw (self)
        # ignore empty flush
        if (empty? self._sprites)
            return;
        Bindings := StandardAttrBindings
        vertexT := 2DStdVertexAttributes
        stride := (sizeof vertexT)
        let offset =
            (member) -> ((offsetof vertexT member) as i64)
        # TODO: this should be generated from a struct or something
        # NOTE: I want to implement this in the Mesh super type, where it could parse an attribute
        # format struct or just receive a runtime format descriptor if you got it dynamically.
        'bind-to-point self._vertexbuffer Bindings.Position (offset 'position) stride
        'bind-to-point self._vertexbuffer Bindings.TextureCoordinates (offset 'texcoords) stride
        'bind-to-point self._vertexbuffer Bindings.Color (offset 'color) stride

        gl.VertexAttribFormat Bindings.Position 2 gl.GL_FLOAT false 0
        gl.VertexAttribFormat Bindings.TextureCoordinates 2 gl.GL_FLOAT false 0
        gl.VertexAttribFormat Bindings.Color 4 gl.GL_FLOAT false 0

        gl.EnableVertexAttribArray Bindings.Position
        gl.EnableVertexAttribArray Bindings.TextureCoordinates
        gl.EnableVertexAttribArray Bindings.Color

        if self._dirty?
            # update GPU side buffers
            try
                'update self._vertexbuffer self._sprites (sizeof-Array self._sprites) 0
            except (ex)
                print ex
                # FIXME
                # NOTE: should prompt buffer recreation at a bigger size
                assert false

            self._dirty? = false
        # TODO: verify if this is the best way to do this.
        'bind self._texture
        'draw-indexed self._vertexbuffer (6 * (countof self._sprites)) 0 self._indexbuffer

fn get-default-shader-sources ()
    Bindings := StandardAttrBindings
    let vert-shader frag-shader =
        do
            using import struct
            using import glsl

            in position : vec2
                # FIXME: attribute sugar should imply the values if possible
                location = (Bindings.Position as i32)
            inout texcoords : vec2
                location = (Bindings.TextureCoordinates as i32)
            inout color : vec4
                location = (Bindings.Color as i32)
            uniform transform : mat4

            fn vs-main ()
                gl_Position = (transform * (vec4 position 0 1.0))
                texcoords.out = texcoords.in
                color.out = color.in

            uniform image : sampler2D
            # can't use inout color here if I want it to actually show up on the shader
            out out-color : vec4
            fn fs-main ()
                # FIXME: needs premultiplied alpha or some shit like that
                out-color = (color.in * (texture image texcoords.in))

            _
                compile-glsl 450 'vertex (typify vs-main)
                compile-glsl 450 'fragment (typify fs-main)




do
    let
        # types
        SpriteBatch
        PrimitiveBatch
        Quad
        StandardAttrBindings
        2DStdVertexAttributes
        # functions
        get-default-shader-sources

        # globals
        debug-text
    locals;
