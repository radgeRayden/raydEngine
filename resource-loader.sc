stbi := (import .foreign.stbi)
import .filesystem
using import struct
using import enum
using import Array

enum ImageFormat plain
    RGBA

# NOTE:
    just so I don't forget I was thinking about this. At the moment it doesn't make a lot of sense
    that this is called "resource loader", because it's just a collection of (one) types that have
    constructors that deal perfectly fine with what you throw at them, no need for a module to do the
    work. But where a module like this can really shine is in caching resources. A simple hashmap with
    filenames can make sure we don't load things twice, and also frees us from having to cache the
    resource where it is used.
    One related idea I had was to add "asset loading barriers" that delimit for example
    a whole level worth of resources; I'm not 100% sure how I'm gonna implement that, but when we unload
    a level then we can use these temporal barriers to indicate that the resource loader
    should release just this chunk of data and nothing else.
struct ImageData
    data : (Array u8)
    width : i32
    height : i32
    channel-count : i32
    format : ImageFormat

    # this variant is mostly here so we can provide RGBA data without going through stbi
    inline... __typecall (cls, data : (Array u8), width, height, channel-count, format = ImageFormat.RGBA,)
        super-type.__typecall cls
            data = data
            width = width
            height = height
            channel-count = channel-count
            format = format

    case (cls, filedata : filesystem.FileData,)
        data := filedata.data
        local channel-count : i32
        local width : i32
        local height : i32
        let desired-channels = 4 # always use rgba

        let raw-imgdata =
            stbi.load_from_memory
                \ data ((countof data) as i32) &width &height &channel-count desired-channels
        if (raw-imgdata == null)
            error@ ('anchor `data)
                "while loading image " .. filedata.filename
                "image data is invalid"

        # TODO: use something less hacky
        let byte-array = (@ (alloca (Array u8)))
        byte-array._items = raw-imgdata
        # because each channel is 8 bits, in our use case anyway.
        byte-array._count = (width * height * channel-count)
        byte-array._capacity = byte-array._count

        this-function cls byte-array width height channel-count ImageFormat.RGBA
    case (cls, path : string,)
        filedata := (filesystem.load path)
        this-function cls filedata

do
    let
        ImageData
        ImageFormat
    locals;
