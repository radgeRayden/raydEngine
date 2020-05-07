switch operating-system
case 'linux
    load-library (.. module-dir "/lib/libsoloud_x64.so")
    # load-library "libasound.so" # do we need this? I think not
pass 'windows
pass 'macos
default
    error "Unsupported OS."

using import radlib.foreign
let soloud =
    include "include/soloud_c.h"

run-stage;
vvv bind soloud
do
    using soloud.extern
    using soloud.typedef
    using soloud.enum filter "^SOLOUD_"
    using soloud.define filter "^SOLOUD_"
    locals;

let filter-pattern =  "^Soloud_"
let soloud = (sanitize-scope soloud filter-pattern)
run-stage;
for k v in soloud
    print k ('typeof v)
typedef+ soloud.SOLOUD_ENUMS
    inline __imply (T otherT)
        inline (self)
            bitcast self otherT

# because of the weird enum wrap catch all
let soloud =
    fold (scope = soloud) for k v in ('symbols soloud.SOLOUD_ENUMS)
        'bind scope k v
