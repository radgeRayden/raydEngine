using import enum
using import Option
using import struct
using import Array
using import Map
import glm
import .math
using import .radlib.core-extensions
using import Rc
gl := (import .foreign.gl)

inline sizeof-Array (arr)
    let T = (typeof arr)
    let ET = T.ElementType
    * (sizeof ET) (countof arr)

inline core-type->gltype (T)
    match T
    case bool
        "BOOL"
    case u32
        "UNSIGNED_INT"
    case i32
        "INT"
    case f32
        "FLOAT"
    case f64
        "DOUBLE"
    default
        "UNKNOWN"

# assign gltypes to core types
va-map
    inline (T)
        let gltype = (.. "GL_" (core-type->gltype T))
        'set-symbol T
            gltype = ('@ gl (Symbol gltype))
    _ bool u32 i32 f32 f64

# build glm to gl types map
for scope in ('lineage glm)
    using glm
    for k v in scope
        if (('typeof v) == type)
            v as:= type

            # skip meta types and sampler stuff
            match v
            case (or
                    vec-type
                    mat-type
                    gvec2
                    gvec3
                    gvec4)
                continue;
            default
                ;

            let element-type =
                core-type->gltype ('@ v 'ElementType)

            let layout =
                if (v < vec-type)
                    "VEC" .. (tostring (('@ v 'Count) as i32))
                elseif (v < mat-type)
                    let columns rows = (('@ v 'Columns) as i32) (('@ v 'Rows) as i32)
                    # because SPIR-V insists on compiling matrices to arrays of vectors,
                      I'll leave this commented out for now, until it's fixed (or forever).
                    # let mat-suffix =
                    #     if (columns == rows)
                    #         tostring columns
                    #     else
                    #         .. (tostring columns) "x" (tostring rows)
                    # "MAT" .. mat-suffix
                    "VEC" .. (tostring columns)
                else ""
            let gltype = (.. "GL_" element-type "_" layout)
            try
                'set-symbol v 'gltype (('@ gl (Symbol gltype)) as i32)
            except (ex)
                # this will catch when a glm type doesn't have an equiv in opengl, eg. imat4
                ;

run-stage;

fn element-type-abbreviation (T)
    let elem-type-abbr =
        match ('@ T 'ElementType)
        case f32
            "f"
        case (i32 or bool)
            "i"
        case u32
            "ui"
        default
            error "Element type not supported by glUniform"

# TODO: handle scalars;
        handle arrays of scalars;
        handle actual arrays of vectors (that don't match number of rows of the matrix)
typedef+ glm.vec-type
    spice glUniform-dispatch! (self location array-offset)
        subtype           := ('typeof self)
        count             := (('@ subtype 'Count) as i32)
        elem-type-abbr    := (element-type-abbreviation subtype)
        fn-name           := (.. "Uniform" (tostring count) elem-type-abbr "v")
        glUniform-variant := ('@ gl (Symbol fn-name))
        spice-quote
            local v = self
            glUniform-variant location 1 (bitcast &v (pointer subtype.ElementType))

typedef+ glm.mat-type
    spice glUniform-dispatch! (self location array-offset)
        subtype           := ('typeof self)
        columns           := (('@ subtype 'Columns) as i32)
        rows              := (('@ subtype 'Rows) as i32)
        elem-type-abbr    := (element-type-abbreviation subtype)
        fn-name           := (.. "Uniform" (tostring columns) elem-type-abbr "v")
        glUniform-variant := ('@ gl (Symbol fn-name))
        spice-quote
            local v = self
            # because SPIR-V wants our matrices to be arrays
              of vectors, we use the same semantics here.
            glUniform-variant location rows (bitcast &v (pointer subtype.ElementType))
run-stage;


enum BufferError plain
    NotEnoughStorage

typedef GPUBuffer < Struct
    @@ memo
    inline __typecall (cls kind target)
        struct (.. (tostring kind) "Buffer") < this-type
            _handle : u32
            _size : i32
            inline __typecall (cls size)
                imply size i32
                local handle : u32
                # FIXME: this might error
                gl.GenBuffers 1 &handle
                # buffer needs to be bound at least once to the desired target before it
                # can be used.
                gl.BindBuffer target handle
                # TODO: check if we successfully created the storage
                # NOTE: study if more / different flags are necessary here.
                gl.NamedBufferStorage handle size null gl.GL_DYNAMIC_STORAGE_BIT
                Struct.__typecall cls
                    _handle = handle
                    _size = size

            fn... update
            case (self data size offset)
                size as:= i64
                offset as:= i64
                # trying to draw too much, needs bigger buffer
                if ((offset + size) > self._size)
                    raise BufferError.NotEnoughStorage
                gl.NamedBufferSubData self._handle offset size data
            case (self data)
                # fairly often we want to update the whole array worth of vertices.
                this-function self data (sizeof-Array data) 0

            # just for wrapping sake. Not much we can abstract away without context.
            inline bind-to-point (self binding-point offset stride)
                gl.BindVertexBuffer binding-point self._handle offset stride

            inline __drop (self)
                local handle = self._handle
                gl.DeleteBuffers 1 &handle

VertexBuffer := (GPUBuffer 'Vertex gl.GL_ARRAY_BUFFER)
IndexBuffer := (GPUBuffer 'Index gl.GL_ELEMENT_ARRAY_BUFFER)

typedef+ VertexBuffer
    fn draw-indexed (self count offset index-buffer)
        gl.BindBuffer gl.GL_ELEMENT_ARRAY_BUFFER index-buffer._handle
        gl.DrawElements gl.GL_TRIANGLES (count as i32) gl.GL_UNSIGNED_SHORT
            inttoptr offset voidstar

struct AttributeDescriptor plain
    components : i32
    gltype : i32
    location : i32
    offset : i32

typedef Mesh < Struct
    @@ memo
    inline __typecall (cls, vertex-format : CStruct)

        static-format? := (not (none? vertex-format))
        let name =
            static-if static-format?
                (.. "Mesh<" (tostring vertex-format) ">")
            else
                "Mesh<RuntimeCustom>"
        struct (tostring name)
            attached-attributes : (Array AttributeDescriptor)
            # NOTE: it's possible that in the future I might wanna have arrays of these instead,
            # but I don't know exactly how to do it, because I think I'd want to have it automatically
            # detected from the vertex attributes, perhaps if one is specified as an array.
            vertex-buffer : VertexBuffer
            index-buffer : IndexBuffer

            inline __typecall (cls vertex-count)
            # fn'

let ShaderInterfaceMap = (Map string (tuple (location = i32) (gltype = u32)))
struct Shader
    _handle : u32
    _uniform-metadata : ShaderInterfaceMap

    inline __typecall (cls frag-source vert-source)
        fn compile-shader! (source kind)
            kind as:= u32
            source as:= rawstring
            let handle = (gl.CreateShader kind)
            gl.ShaderSource handle 1 (& (local src = source)) null
            gl.CompileShader handle

            local compilation-status : i32
            gl.GetShaderiv handle gl.GL_COMPILE_STATUS &compilation-status
            if (not compilation-status)
                local log-length : i32
                local message : (array i8 1024)
                gl.GetShaderInfoLog handle (sizeof message) &log-length &message
                print (default-styler 'style-error "Shader compilation error:")
                print (string &message (log-length as usize))
            handle

        let vs fs =
            compile-shader! vert-source gl.GL_VERTEX_SHADER
            compile-shader! frag-source gl.GL_FRAGMENT_SHADER
        let program = (gl.CreateProgram)
        gl.AttachShader program vs
        gl.AttachShader program fs
        gl.LinkProgram program
        # could make this less copy pastey by abstracting away error logging
        local link-status : i32
        gl.GetProgramiv program gl.GL_LINK_STATUS &link-status
        if (not link-status)
            local log-length : i32
            local message : (array i8 1024)
            gl.GetProgramInfoLog program (sizeof message) &log-length &message
            print (default-styler 'style-error "Shader program linking error:")
            print (string &message (log-length as usize))
        # because we preemptively delete the shader stages, they are
          already marked for deletion when the program is dropped.
        gl.DeleteShader fs
        gl.DeleteShader vs

        fn query-uniform-metadata (handle)
            local uniforms = (ShaderInterfaceMap)
            local uniform-count : i32
            gl.GetProgramInterfaceiv handle \
                gl.GL_UNIFORM gl.GL_ACTIVE_RESOURCES &uniform-count

            for index in (range uniform-count)
                local properties =
                    arrayof gl.GLenum
                        gl.GL_LOCATION
                        gl.GL_ARRAY_SIZE
                        gl.GL_TYPE
                        gl.GL_NAME_LENGTH
                local values = ((array gl.GLint (countof properties)))

                gl.GetProgramResourceiv handle
                    \ gl.GL_UNIFORM
                    \ (index as u32)
                    \ (countof properties) &properties
                    \ (countof values) null &values

                uniform-location := (values @ 0)
                array-size       := (values @ 1)
                uniform-type     := (values @ 2)
                name-length      := (values @ 3)

                let uniform-name = (alloca-array gl.GLchar name-length)
                local returned-length : gl.GLsizei
                gl.GetProgramResourceName handle
                    gl.GL_UNIFORM
                    (index as u32)
                    name-length
                    &returned-length
                    uniform-name

                # returned-length doesn't include null terminator.
                assert ((returned-length + 1) == name-length)
                let uniform-name =
                    # docs.gl says it would be 0 when it's not an array,
                      but for example sampler2D returns 1 here. If this
                      turns out to be an actual problem, further pattern matching is
                      needed to check for square brackets.
                    if (array-size > 1)
                        let log10 =
                            extern 'log10
                                function f64 f64
                        # when the property is an array, it adds an index to the
                          name, which is detrimental to us.
                        let cruft-count =
                            # log for getting number of digits in the size
                            +
                                ((floor (log10 (array-size as f64))) as i32) + 1
                                countof "[]"
                        lslice (string uniform-name) (returned-length - cruft-count)
                    else
                        string uniform-name

                print uniform-name uniform-location (hex uniform-type) array-size
                'set uniforms uniform-name
                    tupleof
                        location = uniform-location
                        gltype = (uniform-type as u32)
            deref uniforms

        super-type.__typecall cls
            _handle = program
            _uniform-metadata = (query-uniform-metadata program)

    inline update-uniform (self name value !array-offset)
        fn update-uniform (self name value array-offset)
            imply name string

            # probably shouldn't be this drastic, maybe just print a warning instead
            assert ('bound? self)
            # lookup metadata for this uniform
            let metadata =
                try
                    'get self._uniform-metadata name
                except (ex)
                    hide-traceback;
                    error ("Shader doesn't contain an uniform called " .. name)
            let
                location = metadata.location
                gltype = metadata.gltype
            # assert that uniform gltype is equivalent to provided argument type
            # TODO: in the case of editing only part of an array (eg. part of a matrix),
              we must verify that we handle the type aliasing properly. This might just work already
              but I'm not 100% sure.
            assert (((typeof value) . gltype) == gltype)
            # select correct glUniform variant for the
              argument datatype and call it with value
            'glUniform-dispatch! value location array-offset
            ;
        let array-offset =
            static-if (none? !array-offset)
                0
            else
                !array-offset
        update-uniform self name value array-offset

    inline bind! (self)
        gl.UseProgram self._handle

    inline bound? (self)
        local current-program : i32
        gl.GetIntegerv gl.GL_CURRENT_PROGRAM &current-program
        self._handle == (current-program as u32)

    inline __drop (self)
        gl.DeleteProgram self._handle

fn calculate-mipmap-count (width height)
    let dimension = (max (width as f32) (height as f32))
    let levels = ((log2 dimension) + 1)
    levels as i32

let TextureHandle = (make-handle-type 'TextureHandle u32)
typedef+ TextureHandle
    inline __drop (self)
        gl.DeleteTextures 1 (& (imply self u32))
        ;
# NOTE: currently only supports RGBA format
struct 2DTexture
    width       : i32
    height      : i32
    _handle     : TextureHandle

    inline __typecall (cls image-data)
        let width height = image-data.width image-data.height
        local handle : gl.GLuint
        gl.GenTextures 1 &handle
        gl.BindTexture gl.GL_TEXTURE_2D handle

        gl.TexStorage2D gl.GL_TEXTURE_2D (calculate-mipmap-count width height) gl.GL_RGBA8 width height
        gl.TexSubImage2D gl.GL_TEXTURE_2D 0 0 0 width height gl.GL_RGBA gl.GL_UNSIGNED_BYTE image-data.data
        gl.GenerateMipmap gl.GL_TEXTURE_2D
        gl.TexParameteri gl.GL_TEXTURE_2D gl.GL_TEXTURE_WRAP_S gl.GL_REPEAT
        gl.TexParameteri gl.GL_TEXTURE_2D gl.GL_TEXTURE_WRAP_T gl.GL_REPEAT
        gl.TexParameteri gl.GL_TEXTURE_2D gl.GL_TEXTURE_MAG_FILTER gl.GL_NEAREST
        gl.TexParameteri gl.GL_TEXTURE_2D gl.GL_TEXTURE_MIN_FILTER gl.GL_LINEAR_MIPMAP_LINEAR

        super-type.__typecall cls
            width   = width
            height  = height
            _handle = (typeinit (TextureHandle handle))

    # NOTE: I hear this is bad. Why is it bad? Changes might be necessary to accomodate a switch
    # to glBindTextures, although this can remain an option.
    inline bind (self)
        gl.BindTexture gl.GL_TEXTURE_2D self._handle
# we assume textures are pretty much always a shared resource.
let 2DTexture = (Rc 2DTexture)

# --------------------------------------------------------------------------------
struct GFXCapabilities plain
    max-attribute-bindings : i32
    max-texture-image-units : i32
    max-uniform-buffer-bindings : i32

global GPU-capabilities : (Option GFXCapabilities)
fn display-capabilities ()
    let capabilities =
        dispatch GPU-capabilities
        case Some (opt)
            opt
        default
            error "gfx not initialized"
    print "Max Attribute Bindings:" capabilities.max-attribute-bindings
    print "Max Texture Image Units:" capabilities.max-texture-image-units
    print "Max Uniform Buffer Bindings:" capabilities.max-uniform-buffer-bindings

fn init ()
    gl.init!;

    # some default settings, I'll move this out / parameterize
      once it doesn't make sense anymore
    enum OpenGLDebugLevel plain
        HIGH
        MEDIUM
        LOW
        NOTIFICATION

    # log-level is the lowest severity level we care about.
    inline make-openGL-debug-callback (log-level)
        let log-level = (log-level as i32)
        fn openGL-error-callback (source _type id severity _length message user-param)
            inline gl-debug-source (source)
                match source
                case gl.GL_DEBUG_SOURCE_API                "API"
                case gl.GL_DEBUG_SOURCE_WINDOW_SYSTEM      "Window System"
                case gl.GL_DEBUG_SOURCE_SHADER_COMPILER    "Shader Compiler"
                case gl.GL_DEBUG_SOURCE_THIRD_PARTY        "Third Party"
                case gl.GL_DEBUG_SOURCE_APPLICATION        "Application"
                case gl.GL_DEBUG_SOURCE_OTHER              "Other"
                default                                    "?"

            inline gl-debug-type (type_)
                match type_
                case gl.GL_DEBUG_TYPE_ERROR                "Error"
                case gl.GL_DEBUG_TYPE_DEPRECATED_BEHAVIOR  "Deprecated"
                case gl.GL_DEBUG_TYPE_UNDEFINED_BEHAVIOR   "Undefined Behavior"
                case gl.GL_DEBUG_TYPE_PORTABILITY          "Portability"
                case gl.GL_DEBUG_TYPE_PERFORMANCE          "Performance"
                case gl.GL_DEBUG_TYPE_OTHER                "Other"
                default                                    "?"

            inline gl-debug-severity (severity)
                match severity
                case gl.GL_DEBUG_SEVERITY_HIGH             "High"
                case gl.GL_DEBUG_SEVERITY_MEDIUM           "Medium"
                case gl.GL_DEBUG_SEVERITY_LOW              "Low"
                case gl.GL_DEBUG_SEVERITY_NOTIFICATION     "Notification"
                default                                    "?"

            using OpenGLDebugLevel
            match severity

            case gl.GL_DEBUG_SEVERITY_HIGH
            case gl.GL_DEBUG_SEVERITY_MEDIUM
                static-if (log-level < MEDIUM)
                    return;
            case gl.GL_DEBUG_SEVERITY_LOW
                static-if (log-level < LOW)
                    return;
            case gl.GL_DEBUG_SEVERITY_NOTIFICATION
                static-if (log-level < NOTIFICATION)
                    return;
            default
                ;

            print
                "source:"
                gl-debug-source source
                "| type:"
                gl-debug-type _type
                "| severity:"
                gl-debug-severity severity
                "| message:"
                string message
            ;
    gl.Enable gl.GL_DEBUG_OUTPUT
    gl.Enable gl.GL_BLEND
    gl.BlendFunc gl.GL_SRC_ALPHA gl.GL_ONE_MINUS_SRC_ALPHA
    gl.Enable gl.GL_MULTISAMPLE
    # TODO: add some colors to this
    gl.DebugMessageCallback (make-openGL-debug-callback OpenGLDebugLevel.LOW) null
    local VAO : gl.GLuint
    gl.GenVertexArrays 1 &VAO
    gl.BindVertexArray VAO

    # right at the start we query everything that is relevant to runtime operation.
    inline query-gl-property (T property)
        local result : T
        let query-fn =
            static-match T
            case i32
                gl.GetIntegerv
            default
                gl.GetIntegerv
        query-fn property &result
        result

    GPU-capabilities =
        GFXCapabilities
            max-attribute-bindings = (query-gl-property i32 gl.GL_MAX_VERTEX_ATTRIB_BINDINGS)
            max-texture-image-units = (query-gl-property i32 gl.GL_MAX_TEXTURE_IMAGE_UNITS)
            max-uniform-buffer-bindings = (query-gl-property i32 gl.GL_MAX_UNIFORM_BUFFER_BINDINGS)

    typedef GfxLifetimeCookie :: (storageof Nothing)
        inline __typecall (cls)
            bitcast none this-type
    print "Gfx module initialized."
    GfxLifetimeCookie;

inline clear (clear-color)
    using glm
    let clear-color =
        static-if (none? clear-color)
            vec4 0.14 0.14 0.14 1.0
        else
            clear-color
    gl.ClearColor (unpack clear-color)
    gl.Clear (gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT)


do
    let
        init
        clear
        display-capabilities
        # types
        VertexBuffer
        IndexBuffer
        Shader
        2DTexture

        # error types
        BufferError
        # globals
        GPU-capabilities

    locals;
