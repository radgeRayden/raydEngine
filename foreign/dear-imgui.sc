using import radlib.foreign
using import radlib.core-extensions

load-library (module-dir .. "/lib/raydengine.so")

let filter-pattern = "^(ig|Im)"
run-stage;
define-scope imgui
    import .headers
    using headers.extern filter filter-pattern
    using headers.typedef filter filter-pattern
    using headers.define filter filter-pattern
    using headers.const filter filter-pattern

let exports =
    sanitize-scope imgui "^ig"
