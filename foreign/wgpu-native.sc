using import radlib.foreign
using import radlib.core-extensions

switch operating-system
case 'linux
    load-library (.. module-dir "/lib/libwgpu_native.so")
case 'windows
    load-library (.. module-dir "/lib/wgpu_native.dll")
default
    error "OS not supported"

define-scope wgpu-native
    let header =
        include "include/wgpu.h"
    using header.extern filter "^wgpu_"
    using header.typedef filter "^WGPU"
    using header.define filter "^WGPU"
    locals;

sanitize-scope wgpu-native "^(wgpu_|WGPU)"
