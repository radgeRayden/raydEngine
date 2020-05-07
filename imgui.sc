ig := (import .foreign.dear-imgui)

fn begin-frame ()
    ig.ImGui_ImplOpenGL3_NewFrame;
    ig.ImGui_ImplGlfw_NewFrame;
    ig.NewFrame;
fn end-frame ()
    ig.Render;
    ig.ImGui_ImplOpenGL3_RenderDrawData (ig.GetDrawData)

sugar with-gui (body...)
    qq
        [do]
            [begin-frame];
            unquote-splice body...
            [end-frame];

global imgui-IO : (mutable pointer ig.ImGuiIO)

fn init (native-window-handle)
    ig.CreateContext null
    imgui-IO = (ig.GetIO)
    ig.StyleColorsDark null;

    # openGL / GLFW specific initialization
    # NOTE: second argument instructs the wrapper to install GLFW callbacks for input handling;
    ig.ImGui_ImplGlfw_InitForOpenGL native-window-handle true
    # I'll probably always require 4.x, but to decouple the library from that decision I'll
    # set it up so it uses the very earliest version we're guaranteed to support.
    ig.ImGui_ImplOpenGL3_Init "#version 330"

    print "Imgui module initialized"

    typedef ImguiLifetimeCookie :: (storageof Nothing)
        inline __typecall (cls)
            bitcast none this-type
        inline __drop (self)
            print "Shutting down Imgui"
            ig.ImGui_ImplOpenGL3_Shutdown;
            ig.ImGui_ImplGlfw_Shutdown;
            # we're using a single context atm, not sure if we'll need more;
            ig.DestroyContext null

    ImguiLifetimeCookie;

do
    using ig
    let ig = ig

    let
        with-gui
        init
    locals;
