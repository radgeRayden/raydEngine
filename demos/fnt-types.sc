using import struct
using import Array
#
    face       This is the name of the true type font.
    size       The size of the true type font.
    bold       The font is bold.
    italic     The font is italic.
    charset    The name of the OEM charset used (when not unicode).
    unicode    Set to 1 if it is the unicode charset.
    stretchH   The font height stretch in percentage. 100% means no stretch.
    smooth     Set to 1 if smoothing was turned on.
    aa         The supersampling level used. 1 means no supersampling was used.
    padding    The padding for each character (up, right, down, left).
    spacing    The spacing for each character (horizontal, vertical).
    outline    The outline thickness for the characters.
struct FNTInfo plain
    face : string
    size : i32
    bold : bool
    italic : bool
    charset : string
    unicode : bool
    stretchH : i32
    smooth : bool
    aa : i32
    padding : (tuple i32 i32 i32 i32)
    spacing : (tuple i32 i32)
    outline : i32


#
    lineHeight    This is the distance in pixels between each line of text.
    base          The number of pixels from the absolute top of the line to the base of the characters.
    scaleW        The width of the texture, normally used to scale the x pos of the character image.
    scaleH        The height of the texture, normally used to scale the y pos of the character image.
    pages         The number of texture pages included in the font.
    packed        Set to 1 if the monochrome characters have been packed into each of the texture channels. In this case alphaChnl describes what is stored in each channel.
    alphaChnl     Set to 0 if the channel holds the glyph data, 1 if it holds the outline, 2 if it holds the glyph and the outline, 3 if its set to zero, and 4 if its set to one.
    redChnl       Set to 0 if the channel holds the glyph data, 1 if it holds the outline, 2 if it holds the glyph and the outline, 3 if its set to zero, and 4 if its set to one.
    greenChnl     Set to 0 if the channel holds the glyph data, 1 if it holds the outline, 2 if it holds the glyph and the outline, 3 if its set to zero, and 4 if its set to one.
    blueChnl      Set to 0 if the channel holds the glyph data, 1 if it holds the outline, 2 if it holds the glyph and the outline, 3 if its set to zero, and 4 if its set to one.

struct FNTCommon plain
    lineHeight : i32
    base : i32
    scaleW : i32
    scaleH : i32
    pages : i32
    packed : bool
    alphaChnl : i32
    redChnl : i32
    greenChnl : i32
    blueChnl : i32

#
    id      The page id.
    file    The texture file name.
struct FNTPage plain
    id : i32
    file : string

#
    id          The character id.
    x           The left position of the character image in the texture.
    y           The top position of the character image in the texture.
    width       The width of the character image in the texture.
    height      The height of the character image in the texture.
    xoffset     How much the current position should be offset when copying the image from the texture to the screen.
    yoffset     How much the current position should be offset when copying the image from the texture to the screen.
    xadvance    How much the current position should be advanced after drawing the character.
    page        The texture page where the character image is found.
    chnl        The texture channel where the character image is found (1 = blue, 2 = green, 4 = red, 8 = alpha, 15 = all channels).
struct FNTChar plain
    id : i32
    x : i32
    y : i32
    width : i32
    height : i32
    xoffset : i32
    yoffset : i32
    xadvance : i32
    page : i32
    chnl : i32

struct BitmapFontInfo
    info : FNTInfo
    common : FNTCommon
    # we assume it's ever only one page, and the loading logic reflects this.
    page : FNTPage
    chars : (Array FNTChar)

locals;
