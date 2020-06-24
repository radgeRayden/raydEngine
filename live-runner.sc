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
using import radlib.core-extensions
import .HID
import .gfx
import .imgui
platform := (import radlib.platform)

HID.init (HID.WindowOptions) (HID.GLContextOptions)
gfx.init;
imgui.init (HID.get-GLFW-window)

# event interface
using import Option
global on-update : (Option (pointer (function void f64)))

# UI overlay
global __runner-messages : (Array string)
fn display-message (msg)
    'append __runner-messages msg
    print msg

fn reload-source ()
    print "reloading source"
    'clear __runner-messages
    let upvalues =
        .. (globals)
            do
                let __runner-messages
                let reload-source = this-function

                using import Map
                global __persistent-data : (Map Symbol (tuple (ptr = voidstar) (T = type)))
                sugar live (values...)
                    sugar-match values...
                    case (name ': T '= value)
                        name as:= Symbol
                        T := ((sc_expand T '() sugar-scope) as type)
                        let result =
                            label verify
                                try
                                    let entry = ('get __persistent-data name)
                                    let ptr eT = entry.ptr entry.T
                                    if (eT != T)
                                        merge verify
                                            qq
                                                drop (ptrtoref (bitcast [ptr] (mutable pointer [T])))
                                    return
                                        qq
                                            let [name] =
                                                ptrtoref (view (bitcast [ptr] (mutable pointer [T])))
                                else
                                    '()

                        let result =
                            qq
                                embed
                                    [result]
                                    global [name] : [T] = [value]
                                    'set [__persistent-data] (sugar-quote [name])
                                        tupleof
                                            ptr = (bitcast (reftoptr (view [name])) voidstar)
                                            T = [T]
                        deref result

                    default
                        error "syntax: live <name> : <type> = <value>"
                locals;
    print (typeof upvalues)

    let scope =
        try
            load-module _module-name filename
                main-module? = true
                scope = upvalues

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
        platform.sleep 0.001

        HID.window.swap-buffers;
