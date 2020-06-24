using import radlib.foreign
using import radlib.core-extensions

define-scope soloud
    let soloud =
        include "include/soloud_c.h"

    using soloud.extern
    using soloud.typedef
    using soloud.enum filter "^SOLOUD_"
    using soloud.define filter "^SOLOUD_"

let filter-pattern =  "^Soloud_"
let soloud = (sanitize-scope soloud filter-pattern)
run-stage;

typedef+ soloud.SOLOUD_ENUMS
    inline __imply (T otherT)
        inline (self)
            bitcast self otherT

# because of the weird enum wrap catch all
let soloud =
    fold (scope = soloud) for k v in ('symbols soloud.SOLOUD_ENUMS)
        'bind scope k v
