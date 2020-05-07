using import ..core-extensions
using import ..foreign

let extern-pattern = "(scopes_wrapper|inotify_)"

define-scope inotify
    let header =
        foreign "sys/inotify.h"
            with-constants
                IN_CLOSE
                IN_MOVE
                IN_ALL_EVENTS

    using header.extern filter extern-pattern
    using header.const filter extern-pattern
    using header.typedef filter extern-pattern
    using header.struct filter extern-pattern
    using header.enum filter extern-pattern
    using header.define filter "^IN_"
    using header.union filter extern-pattern

    unlet header

define-scope inotify-macros
    let IN_CLOSE = (inotify.scopes_wrapper__IN_CLOSE)
    let IN_MOVE = (inotify.scopes_wrapper__IN_MOVE)
    let IN_ALL_EVENTS = (inotify.scopes_wrapper__IN_ALL_EVENTS)

sanitize-scope (inotify .. inotify-macros) extern-pattern
