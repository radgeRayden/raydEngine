stbi := (import .foreign.stbi)

local channel-count : i32
local width : i32
local height : i32
let desired-channels = 4 # always use rgba
filename := (module-dir .. "/raydEngine_icon.png")
data := (stbi.load filename &width &height &channel-count desired-channels)

if (data == null)
    error ("Failed loading image: " .. filename)

data
