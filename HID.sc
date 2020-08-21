# HID.sc
# Human Interface Devices
# wrapper library for window and input access
# --------------------------------------------------------------------------------
using import struct
using import enum
using import Option
using import Array
using import radlib.core-extensions
glfw := (import .foreign.glfw)
let AppSettings = (import radlib.app-settings)

# --------------------------------------------------------------------------------
inline uninitialized-error ()
    hide-traceback;
    error "Tried using a window function without initializing the HID module."

# This is the pointer to the main (and only) application window. I don't intend to support
# multiple windows in the near future. Because it's assigned at the start and and its destruction
# correlates with the application exiting, checking if it's assigned is a good way to test
# if the module was initialized.
Window := (mutable (pointer glfw.window))
global app-window : (Option Window)

inline unwrap-window ()
    try
        'unwrap app-window
    else
        static-if AppSettings.AOT?
            using import radlib.libc
            stdio.puts "HID module not initialized"
            exit 1
        else
            assert app-window "HID module not initialized"
            exit 1 # force noreturn

# --------------------------------------------------------------------------------
# I keep track of every window related option in a global (since we only have one window),
# this has the advantage that it's easily accesible by callbacks (which change them) and window
# manipulation functions. For example 'toggle-fullscreen! needs to know if the window is fullscreen or
# not beforehand.
struct WindowOptions plain
    width       : i32 = 1280
    height      : i32 = 720
    x           : i32 = 100
    y           : i32 = 100
    title       : string = "Incredibly Untitled Game (or app)"
    fullscreen? : bool = false
    visible?    : bool = true
    maximized?  : bool = false
    vsync?      : bool = true

global current-window-options : WindowOptions

# --------------------------------------------------------------------------------
define-scope window
    fn toggle-visible ()
        opt := current-window-options
        let window = (unwrap-window)
        if opt.visible?
            glfw.HideWindow window
        else
            glfw.ShowWindow window
        ;

    fn swap-buffers ()
        glfw.SwapBuffers (unwrap-window)

    fn close ()
        glfw.SetWindowShouldClose (unwrap-window) true

    fn received-quit-event? ()
        let quit? = (glfw.WindowShouldClose (unwrap-window))

    fn poll-events ()
        glfw.PollEvents;

    fn size ()
        local width : i32
        local height : i32
        glfw.GetWindowSize (unwrap-window) &width &height
        _ width height

    fn framebuffer-size ()
        local width : i32
        local height : i32
        glfw.GetFramebufferSize (unwrap-window) &width &height
        _ width height

    fn set-size (width height)
        glfw.SetWindowSize (unwrap-window) width height
        ;

    fn set-position (x y)
        glfw.SetWindowPos (unwrap-window) x y

    fn set-title (title)
        glfw.SetWindowTitle (unwrap-window) title
        ;

    fn position ()
        local x : i32
        local y : i32
        glfw.GetWindowPos (unwrap-window) &x &y
        _ x y

    fn toggle-fullscreen ()
        opt := current-window-options
        let window = (unwrap-window)
        # TODO: change this so it chooses the monitor the current window resides in.
        monitor := (glfw.GetPrimaryMonitor)
        if (not opt.fullscreen?)
            opt.fullscreen? = true
            video-mode := (glfw.GetVideoMode monitor)
            glfw.SetWindowMonitor window monitor 0 0 video-mode.width video-mode.height glfw.GLFW_DONT_CARE
        else
            opt.fullscreen? = false
            glfw.SetWindowMonitor window null opt.x opt.y opt.width opt.height glfw.GLFW_DONT_CARE
        ;

    fn toggle-vsync ()
        opt := current-window-options
        if opt.vsync?
            glfw.SwapInterval 0
        else
            glfw.SwapInterval 1
        ;

    # FIXME: doesn't cover all window options, although they're all covered
    # in specific functions.
    fn configure (window-options)
        opt := window-options
        old-opt := current-window-options

        let width height = opt.width opt.height
        # if we didn't check for fullscreen status, could end up changing monitor
        # mode, which I don't want to allow.
        if (not old-opt.fullscreen?)
            set-size width height
            set-position opt.x opt.y

        if (opt.fullscreen? != old-opt.fullscreen?)
            toggle-fullscreen;

        if (opt.visible? != old-opt.visible?)
            toggle-visible;

        if (opt.vsync? != old-opt.vsync?)
            toggle-vsync;

        set-title opt.title

        current-window-options = window-options
        ;

    fn get-native-window-info (window)
        static-match operating-system
        case 'linux
            let GetX11Display =
                extern 'glfwGetX11Display (function (mutable@ voidstar))
            let GetX11Window =
                extern 'glfwGetX11Window (function u64 (mutable@ glfw.window))
            _ (GetX11Display) (GetX11Window window)
        case 'windows
            let GetModuleHandleA =
                extern 'GetModuleHandleA (function voidstar voidstar)
            let GetWin32Window =
                extern 'glfwGetWin32Window (function voidstar (mutable@ glfw.window))
            _ (GetModuleHandleA null) (GetWin32Window window)
        default
            error "OS not supported"

    fn... create-wgpu-surface (window = none,)
        let window = (window or (unwrap-window))
        let wgpu = (import .foreign.wgpu-native)

        static-match operating-system
        case 'linux
            let x11-display x11-window = (get-native-window-info window)
            wgpu.create_surface_from_xlib x11-display x11-window
        case 'windows
            let hinstance hwnd = (get-native-window-info window)
            wgpu.create_surface_from_windows_hwnd hinstance hwnd
        default
            error "OS not supported"

    fn... create-vulkan-surface (instance, window = none)
        let window = (window or (unwrap-window))
        let vk = (import .foreign.volk)

        static-match operating-system
        case 'linux
            let x11-display x11-window = (get-native-window-info window)
            local result : vk.SurfaceKHR
            vk.CreateXlibSurfaceKHR instance
                &local vk.XlibSurfaceCreateInfoKHR
                    sType = vk.VK_STRUCTURE_TYPE_XLIB_SURFACE_CREATE_INFO_KHR
                    dpy = x11-display
                    window = window
                null
                &result
            result
        case 'windows
            let hinstance hwnd = (get-native-window-info window)
            local result : vk.SurfaceKHR
            vk.CreateWin32SurfaceKHR instance
                &local vk.Win32SurfaceCreateInfoKHR
                    sType = vk.VK_STRUCTURE_TYPE_WIN32_SURFACE_CREATE_INFO_KHR
                    instance = hinstance
                    hwnd = hwnd
                null
                &result
        default
            error "OS not supported"
        ;

    unlet get-native-window-info

    fn required-vulkan-extensions ()
        local count : u32
        let ext = (glfw.GetRequiredInstanceExtensions &count)

        local ext-array : (Array rawstring)
        for i in (range count)
            'append ext-array (ext @ i)
        ext-array

    fn... monitor-size (monitor : (mutable pointer glfw.monitor) = null,)
        let monitor =
            if (monitor == null)
                (glfw.GetPrimaryMonitor)
            else
                monitor
        let mode = (glfw.GetVideoMode monitor)
        using import radlib.libc
        _ (deref mode.width) (deref mode.height)


# --------------------------------------------------------------------------------
define-scope keyboard
    enum KeyCode plain
        A               = glfw.GLFW_KEY_A
        B               = glfw.GLFW_KEY_B
        C               = glfw.GLFW_KEY_C
        D               = glfw.GLFW_KEY_D
        E               = glfw.GLFW_KEY_E
        F               = glfw.GLFW_KEY_F
        G               = glfw.GLFW_KEY_G
        H               = glfw.GLFW_KEY_H
        I               = glfw.GLFW_KEY_I
        J               = glfw.GLFW_KEY_J
        K               = glfw.GLFW_KEY_K
        L               = glfw.GLFW_KEY_L
        M               = glfw.GLFW_KEY_M
        N               = glfw.GLFW_KEY_N
        O               = glfw.GLFW_KEY_O
        P               = glfw.GLFW_KEY_P
        Q               = glfw.GLFW_KEY_Q
        R               = glfw.GLFW_KEY_R
        S               = glfw.GLFW_KEY_S
        T               = glfw.GLFW_KEY_T
        U               = glfw.GLFW_KEY_U
        V               = glfw.GLFW_KEY_V
        W               = glfw.GLFW_KEY_W
        X               = glfw.GLFW_KEY_X
        Y               = glfw.GLFW_KEY_Y
        Z               = glfw.GLFW_KEY_Z
        ROW_1           = glfw.GLFW_KEY_1
        ROW_2           = glfw.GLFW_KEY_2
        ROW_3           = glfw.GLFW_KEY_3
        ROW_4           = glfw.GLFW_KEY_4
        ROW_5           = glfw.GLFW_KEY_5
        ROW_6           = glfw.GLFW_KEY_6
        ROW_7           = glfw.GLFW_KEY_7
        ROW_8           = glfw.GLFW_KEY_8
        ROW_9           = glfw.GLFW_KEY_9
        ROW_0           = glfw.GLFW_KEY_0
        SPACE           = glfw.GLFW_KEY_SPACE
        MINUS           = glfw.GLFW_KEY_MINUS
        EQUAL           = glfw.GLFW_KEY_EQUAL
        LEFT_BRACKET    = glfw.GLFW_KEY_LEFT_BRACKET
        RIGHT_BRACKET   = glfw.GLFW_KEY_RIGHT_BRACKET
        BACKSLASH       = glfw.GLFW_KEY_BACKSLASH
        SEMICOLON       = glfw.GLFW_KEY_SEMICOLON
        APOSTROPHE      = glfw.GLFW_KEY_APOSTROPHE
        GRAVE_ACCENT    = glfw.GLFW_KEY_GRAVE_ACCENT
        COMMA           = glfw.GLFW_KEY_COMMA
        PERIOD          = glfw.GLFW_KEY_PERIOD
        SLASH           = glfw.GLFW_KEY_SLASH
        WORLD_1         = glfw.GLFW_KEY_WORLD_1
        WORLD_2         = glfw.GLFW_KEY_WORLD_2
        ESCAPE          = glfw.GLFW_KEY_ESCAPE
        F1              = glfw.GLFW_KEY_F1
        F2              = glfw.GLFW_KEY_F2
        F3              = glfw.GLFW_KEY_F3
        F4              = glfw.GLFW_KEY_F4
        F5              = glfw.GLFW_KEY_F5
        F6              = glfw.GLFW_KEY_F6
        F7              = glfw.GLFW_KEY_F7
        F8              = glfw.GLFW_KEY_F8
        F9              = glfw.GLFW_KEY_F9
        F10             = glfw.GLFW_KEY_F10
        F11             = glfw.GLFW_KEY_F11
        F12             = glfw.GLFW_KEY_F12
        F13             = glfw.GLFW_KEY_F13
        F14             = glfw.GLFW_KEY_F14
        F15             = glfw.GLFW_KEY_F15
        F16             = glfw.GLFW_KEY_F16
        F17             = glfw.GLFW_KEY_F17
        F18             = glfw.GLFW_KEY_F18
        F19             = glfw.GLFW_KEY_F19
        F20             = glfw.GLFW_KEY_F20
        F21             = glfw.GLFW_KEY_F21
        F22             = glfw.GLFW_KEY_F22
        F23             = glfw.GLFW_KEY_F23
        F24             = glfw.GLFW_KEY_F24
        F25             = glfw.GLFW_KEY_F25
        UP              = glfw.GLFW_KEY_UP
        DOWN            = glfw.GLFW_KEY_DOWN
        LEFT            = glfw.GLFW_KEY_LEFT
        RIGHT           = glfw.GLFW_KEY_RIGHT
        LEFT_SHIFT      = glfw.GLFW_KEY_LEFT_SHIFT
        RIGHT_SHIFT     = glfw.GLFW_KEY_RIGHT_SHIFT
        LEFT_CONTROL    = glfw.GLFW_KEY_LEFT_CONTROL
        RIGHT_CONTROL   = glfw.GLFW_KEY_RIGHT_CONTROL
        LEFT_ALT        = glfw.GLFW_KEY_LEFT_ALT
        RIGHT_ALT       = glfw.GLFW_KEY_RIGHT_ALT
        TAB             = glfw.GLFW_KEY_TAB
        ENTER           = glfw.GLFW_KEY_ENTER
        BACKSPACE       = glfw.GLFW_KEY_BACKSPACE
        INSERT          = glfw.GLFW_KEY_INSERT
        DELETE          = glfw.GLFW_KEY_DELETE
        PAGE_UP         = glfw.GLFW_KEY_PAGE_UP
        PAGE_DOWN       = glfw.GLFW_KEY_PAGE_DOWN
        HOME            = glfw.GLFW_KEY_HOME
        END             = glfw.GLFW_KEY_END
        KEYPAD_0        = glfw.GLFW_KEY_KP_0
        KEYPAD_1        = glfw.GLFW_KEY_KP_1
        KEYPAD_2        = glfw.GLFW_KEY_KP_2
        KEYPAD_3        = glfw.GLFW_KEY_KP_3
        KEYPAD_4        = glfw.GLFW_KEY_KP_4
        KEYPAD_5        = glfw.GLFW_KEY_KP_5
        KEYPAD_6        = glfw.GLFW_KEY_KP_6
        KEYPAD_7        = glfw.GLFW_KEY_KP_7
        KEYPAD_8        = glfw.GLFW_KEY_KP_8
        KEYPAD_9        = glfw.GLFW_KEY_KP_9
        KEYPAD_DIVIDE   = glfw.GLFW_KEY_KP_DIVIDE
        KEYPAD_MULTIPLY = glfw.GLFW_KEY_KP_MULTIPLY
        KEYPAD_SUBTRACT = glfw.GLFW_KEY_KP_SUBTRACT
        KEYPAD_ADD      = glfw.GLFW_KEY_KP_ADD
        KEYPAD_DECIMAL  = glfw.GLFW_KEY_KP_DECIMAL
        KEYPAD_EQUAL    = glfw.GLFW_KEY_KP_EQUAL
        KEYPAD_ENTER    = glfw.GLFW_KEY_KP_ENTER
        PRINT_SCREEN    = glfw.GLFW_KEY_PRINT_SCREEN
        NUM_LOCK        = glfw.GLFW_KEY_NUM_LOCK
        CAPS_LOCK       = glfw.GLFW_KEY_CAPS_LOCK
        SCROLL_LOCK     = glfw.GLFW_KEY_SCROLL_LOCK
        PAUSE           = glfw.GLFW_KEY_PAUSE
        LEFT_SUPER      = glfw.GLFW_KEY_LEFT_SUPER
        RIGHT_SUPER     = glfw.GLFW_KEY_RIGHT_SUPER
        MENU            = glfw.GLFW_KEY_MENU

    fn down? (key-code)
        (glfw.GetKey (unwrap-window) key-code) as bool

    enum KeyAction plain
        PRESS = glfw.GLFW_PRESS
        RELEASE = glfw.GLFW_RELEASE
        REPEAT = glfw.GLFW_REPEAT
    enum KeyModifier plain
        SHIFT = glfw.GLFW_MOD_SHIFT
        ALT = glfw.GLFW_MOD_ALT
        CONTROL = glfw.GLFW_MOD_CONTROL
        SUPER = glfw.GLFW_MOD_SUPER
        CAPS_LOCK = glfw.GLFW_MOD_CAPS_LOCK
        NUM_LOCK = glfw.GLFW_MOD_NUM_LOCK

    struct KeyEvent plain
        keycode : KeyCode
        scancode : i32
        action : KeyAction
        modifiers : i32

    inline mod-shift? (mods)
        (mods & KeyModifier.SHIFT) != 0
    inline mod-alt? (mods)
        (mods & KeyModifier.ALT) != 0
    inline mod-ctrl? (mods)
        (mods & KeyModifier.CONTROL) != 0
    inline mod-super? (mods)
        (mods & KeyModifier.SUPER) != 0
    inline mod-caps-lock? (mods)
        (mods & KeyModifier.CAPS_LOCK) != 0
    inline mod-num-lock? (mods)
        (mods & KeyModifier.NUM_LOCK) != 0

    inline released? (ev code)
        (ev.action == KeyAction.RELEASE) and (ev.keycode == code)

    inline pressed? (ev code)
        (ev.action == KeyAction.PRESS) and (ev.keycode == code)

    inline keybind (event bindings...)
        va-lfold true
            inline (__ current computed)
                static-if ((typeof current) == KeyModifier)
                    computed and ((event.modifiers & current) != 0)
                else
                    computed and (down? current)
            bindings...

define-scope mouse
    enum Button plain
        LEFT = 0
        RIGHT = 1
        MIDDLE = 2
        BUTTON_1 = 0
        BUTTON_2 = 1
        BUTTON_3 = 2
        BUTTON_4 = 3
        BUTTON_5 = 4
        BUTTON_6 = 5
        BUTTON_7 = 6
        BUTTON_8 = 7

    enum ButtonAction plain
        PRESS
        RELEASE

    struct ButtonEvent plain
        button : Button
        action : ButtonAction
        modifiers : i32

    fn position ()
        local x : f64
        local y : f64
        glfw.GetCursorPos (unwrap-window) &x &y
        _ x y


# so we can `using keyboard'
run-stage;

# --------------------------------------------------------------------------------
# default callbacks
inline callback-pointer (args...)
    pointer
        function void args...
global on-window-moved : (callback-pointer i32 i32)
global on-window-resized : (callback-pointer i32 i32)
global on-key-event : (callback-pointer keyboard.KeyEvent)
global on-text-input : (callback-pointer u32)
global on-mouse-button-event : (callback-pointer mouse.ButtonEvent)
global on-mouse-moved : (callback-pointer f64 f64)

# --------------------------------------------------------------------------------
# TODO: no default version; if no version specified (0.0) then we go from latest released and try
# until we find a supported version.
struct GLVersion plain
    major : i32 = 4
    minor : i32 = 5
struct GLContextOptions plain
    version : GLVersion
    debug? : bool = true

enum GfxAPI
    OpenGL : GLContextOptions
    WebGPU
    Vulkan

fn set-callbacks ()
    let window-handle = (unwrap-window)
    # ------------------------------------------------------------------------------------------
    # We set the default user callbacks, and wrap them for GLFW. The layer that
    # talks to GLFW does some massaging of the data and can't be changed in user code,
    # whereas the callback pointers are fully swapable.

    inline wrap-callback (cb args...)
        if (cb == null)
            # doesn't need to do anything
            return;
        cb args...

    glfw.SetKeyCallback window-handle
        fn "glfw-key-callback" (window keycode scancode action modifiers)
            let event =
                keyboard.KeyEvent
                    action = (action as keyboard.KeyAction)
                    keycode = (keycode as keyboard.KeyCode)
                    scancode = scancode
                    modifiers = modifiers
            wrap-callback on-key-event event

    glfw.SetWindowSizeCallback window-handle
        fn "glfw-window-size-callback" (window-handle width height)
            opt := current-window-options
            if (not opt.fullscreen?)
                opt.width = width
                opt.height = height
            wrap-callback on-window-resized width height

    glfw.SetWindowPosCallback window-handle
        fn "glfw-window-position-callback" (window-handle x y)
            opt := current-window-options
            if (not opt.fullscreen?)
                opt.x = x
                opt.y = y
            wrap-callback on-window-moved x y

    glfw.SetCharCallback window-handle
        fn "glfw-char-callback" (window codepoint)
            wrap-callback on-text-input codepoint

    glfw.SetMouseButtonCallback window-handle
        fn "glfw-mouse-button-callback" (window button action mods)
            wrap-callback on-mouse-button-event
                mouse.ButtonEvent
                    button = (button as mouse.Button)
                    action = (action as mouse.ButtonAction)
                    modifiers = mods

    glfw.SetCursorPosCallback window-handle
        fn "glfw-mouse-cursor-pos-callback" (window xpos ypos)
            wrap-callback on-mouse-moved xpos ypos

typedef HIDLifetimeCookie :: (storageof Nothing)
    inline __typecall (cls)
        bitcast none this-type
    inline __drop (self)
        print "Shutting down GLFW"
        glfw.DestroyWindow ('force-unwrap app-window)
        app-window = none
        # glfw.Terminate;

fn... init (window-config = (WindowOptions), context : GfxAPI)
    imply window-config WindowOptions

    glfw.SetErrorCallback
        fn "glfw-raise-error" (error-code error-text)
            print (string error-text)
            # handle possible errors gracefully if possible or quit
            assert false

    glfw.Init;

    dispatch context
    case OpenGL (context)
        # default opengl hints
        glfw.WindowHint glfw.GLFW_CLIENT_API glfw.GLFW_OPENGL_API
        glfw.WindowHint glfw.GLFW_DOUBLEBUFFER true
        glfw.WindowHint glfw.GLFW_OPENGL_FORWARD_COMPAT true

        version := context.version
        if ((version.major == 0) and (version.minor == 0))
        else
            glfw.WindowHint glfw.GLFW_CONTEXT_VERSION_MAJOR version.major
            glfw.WindowHint glfw.GLFW_CONTEXT_VERSION_MINOR version.minor
        glfw.WindowHint glfw.GLFW_OPENGL_DEBUG_CONTEXT context.debug?
        glfw.WindowHint glfw.GLFW_OPENGL_PROFILE glfw.GLFW_OPENGL_CORE_PROFILE

        glfw.WindowHint glfw.GLFW_SAMPLES 4
    default
        glfw.WindowHint glfw.GLFW_CLIENT_API glfw.GLFW_NO_API

    window-handle :=
        (glfw.CreateWindow window-config.width window-config.height window-config.title null null)
    if (window-handle == null)
        error "Failed to create a window with specified context settings."

    if (('literal context) == GfxAPI.OpenGL.Literal)
        glfw.MakeContextCurrent window-handle

    app-window = window-handle
    window.configure window-config

    set-callbacks;

    # ------------------------------------------------------------------------------------------
    # trying to remember to do this in every submodule to ease debugging when
    # some non initialized function segfaults the whole thing.
    print "HID module initialized."

    HIDLifetimeCookie;
# this variant is very much dedicated to custom executable / distribution,
# so we skip steps that should be taken care of.
case (window-config : WindowOptions, prev-handle : (mutable pointer glfw.window))
    app-window = prev-handle

    glfw.SetWindowAttrib prev-handle glfw.GLFW_DECORATED true
    window.configure window-config

    set-callbacks;
    HIDLifetimeCookie;
   

do
    let
        GfxAPI
        GLContextOptions
        WindowOptions
        init

        # subscopes
        window
        keyboard
        mouse

        # callbacks
        on-window-moved
        on-window-resized
        on-key-event
        on-text-input
        on-mouse-button-event
        on-mouse-moved

    let get-GLFW-window = unwrap-window
    locals;
