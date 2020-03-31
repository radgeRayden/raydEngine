local filename = arg[1]

local function make_tag(tag, info)
    local result = tag .. " "
    for k,v in pairs(info) do
        result = result .. "(" .. k .. " = " .. v .. ") "
    end
    result = result .. "\n"
    return result
end

local function process_kv_list (input)
    local result = {}
    for i=1, #input, 2 do
        local key, value = input[i], input[i + 1]
        if tonumber(value) then
            result[key] = value
        elseif ((type(value) == "string") or (value == nil)) then
            result[key] = ("\"" .. (value or "") .. "\"")
        elseif (type(value) == "table") then
            local tuple = "(tupleof"
            for k,v in ipairs(value) do
                tuple = tuple .. " " .. v
            end
            tuple = tuple .. ")"
            result[key] = tuple
        end
    end
    return result
end

local lpeg = require("lpeg")
local re = require("re")

local function make_pattern (tag_name)
    local pattern = "S <- '" .. tag_name .. " ' {| ( keyvalue_pair %s+ )+ |}\n" ..
    [[
        keyvalue_pair <- {%a+} '=' (({| number_seq |} / {si32}) / string)
        number_seq    <- ({si32} ',')+ {si32}
        si32          <- '-'? %d+
        string        <- '"' {(!'"' .)*} '"'
    ]]
    return re.compile(pattern)
end

local file = io.open(filename)

local line = file:read("*line")
local fnt_info_raw = (lpeg.match(make_pattern("info"), line))
local fnt_info = process_kv_list(fnt_info_raw)

local line = file:read("*line")
local fnt_common_raw = (lpeg.match(make_pattern("common"), line))
local fnt_common = process_kv_list(fnt_common_raw)

local line = file:read("*line")
local fnt_page_raw = (lpeg.match(make_pattern("page"), line))
local fnt_page = process_kv_list(fnt_page_raw)

local fnt_chars = {}
local char_pattern = make_pattern("char")
while (line) do
    local cap = lpeg.match(char_pattern, line)
    if cap then
        fnt_chars[#fnt_chars + 1] = process_kv_list(cap)
    end
    line = file:read("*line")
end
file:close()

local indentation = string.rep(" ", 12)
local output =
[[
using import ..fnt-types
fn font ()
    local fntmetadata : BitmapFontInfo
        info =
]] .. indentation .. make_tag("FNTInfo", fnt_info) ..
[[
        common =
]] .. indentation .. make_tag("FNTCommon", fnt_common) ..
[[
        page =
]] .. indentation .. make_tag("FNTPage", fnt_page)

for k,v in ipairs(fnt_chars) do
    output = output .. "    " .. "'append fntmetadata.chars\n        " .. make_tag("FNTChar", v)
end

output = output .. "    fntmetadata\n\nlocals;"
io.write(output)
