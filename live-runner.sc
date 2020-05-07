let argc argv = (launch-args)
if (argc < 3) # scopes live-coding.sc $input
    error "expected input file."
filename := (string (argv @ 2))

let _module-name =
    do
        let match? start end = ('match? "\\.sc$" filename)
        let module =
            if match?
                lslice filename start
            else
                error "expected scopes source file, but got file with different extension."

run-stage;

using import Array
import .HID
import .gfx
import .imgui
platform := (import radlib.platform)

HID.init (HID.GLContextOptions) (HID.WindowOptions)
gfx.init;
imgui.init (HID.get-window-handle)

# event interface
using import Option
global on-update : (Option (pointer (function void f64)))

# UI overlay
global onscreen-messages : (Array string)
fn display-message (msg)
    'append onscreen-messages msg
    print msg

fn reload-source ()
    print "reloading source"
    'clear onscreen-messages
    let scope =
        try
            load-module _module-name filename
                main-module? = true
        except (ex)
            display-message ('format ex)
            return;

    if (('typeof scope) != Scope)
        display-message
            .. "expected scope exported from module, was " (tostring ('typeof scope))
        return;
    scope as:= Scope
    inline compile-callback (name arg-types...)
        let template =
            try
                '@ scope name
            else
                print "could not find" name "function. Skipped."
                raise false
        try
            let typifiedv = (typify (template as Closure) arg-types...)
            (compile typifiedv) as (pointer (function void arg-types...))
        except (ex)
            'dump ex
            display-message (tostring ('anchor template))
            display-message ('format ex)
            raise false

    # we call init once
    try
        ((compile-callback 'init))
    else
        ;
    try
        on-update = (compile-callback 'update f64)
    else
        ;

HID.on-key-event =
    fn "key" (event)
        raising Error
        using HID.keyboard
        let action keycode = event.action event.keycode
        # esc to close
        if ((action == KeyAction.PRESS) and (keycode == KeyCode.ESCAPE))
            HID.window.close;

        # alt+enter to fullscreen
        if ((action == KeyAction.PRESS) and (mod-alt? event.modifiers) and (keycode == KeyCode.ENTER))
            HID.window.toggle-fullscreen;

        # F5 to reload code
        if ((action == KeyAction.PRESS) and (keycode == KeyCode.F5))
            reload-source;

'set-symbols package
    path =
        cons (module-dir .. "/?/init.sc")
            cons (module-dir .. "/?.sc") package.path

reload-source;

fn runner-GUI ()
    using imgui
    with-gui
        Text "messages"
        for str in onscreen-messages
            Text str
do
    file-watcher := (import radlib.file-watcher)
    local fw = (file-watcher.FileWatcher)
    'watch-file fw (sc_realpath filename) (file-watcher.EventKind.MODIFIED)
        fn "source-modified" ()
            try
                reload-source;
            else
                ;
    while (not (HID.window.received-quit-event?))
        HID.window.poll-events;
        gfx.clear;
        'poll-events fw
        if on-update
            (('force-unwrap on-update) 0)
        runner-GUI;
        platform.sleep 0.001

        HID.window.swap-buffers;
