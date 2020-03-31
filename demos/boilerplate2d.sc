# app.sc
# ------
# Wraps application runtime cycle with sane defaults, as to make the creation of
# prototypes fast and easy. Can be bypassed for fine control, either in parts or completely.

using import .radlib.core-extensions
import .HID
import .gfx

HID.on-key-event =
    fn "on-key-event" (event)
        raising Error
        using HID.keyboard
        let action keycode = event.action event.keycode
        # esc to close
        if ((action == KeyAction.PRESS) and (keycode == KeyCode.ESCAPE))
            HID.window.close;

        # alt+enter to fullscreen
        if ((action == KeyAction.PRESS) and (mod-alt? event.modifiers) and (keycode == KeyCode.ENTER))
            HID.window.toggle-fullscreen;

inline wrap-callback (cb args...)
    if (cb == null)
        # doesn't need to do anything
        return;
    cb args...

inline callback-pointer (args...)
    pointer
        raises
            function void args...
            Error

define-scope app
    global on-update : (callback-pointer f64) # dt
    # initializes all modules used by a default application
    inline init ()
        _
            HID.init
                (HID.GLContextOptions)
                (HID.WindowOptions)
            gfx.init;

    inline run ()
        while (not (HID.window.received-quit-event?))
            HID.window.poll-events;
            wrap-callback on-update 0
            HID.window.swap-buffers;
