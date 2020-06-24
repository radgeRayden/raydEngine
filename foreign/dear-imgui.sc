using import radlib.foreign
using import radlib.core-extensions

let filter-pattern = "^(ig|Im)"
run-stage;
define-scope imgui
    import .platform
    using platform.extern filter filter-pattern
    using platform.typedef filter filter-pattern
    using platform.define filter filter-pattern
    using platform.const filter filter-pattern

let exports =
    sanitize-scope imgui "^ig"
