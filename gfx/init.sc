# when we have more than one backend, it will be conditionally loaded
# here.
using
    sugar-if true
        import .webgpu.backend

# now we list every function available in the interface. This
# is to ensure backends have a consistent interface.
do
    let
        init
        request-framebuffer
        present
        set-clear-color
        draw
    locals;
