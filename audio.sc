soloud := (import .foreign.soloud)
using import struct
using import enum
using import Option

global instance : (Option (mutable pointer soloud.Soloud))

inline uninitialized-error ()
    hide-traceback;
    error "Tried using an audio function without initializing the backend successfully."

inline unwrap-instance ()
    try
        'unwrap instance
    else
        uninitialized-error;

typedef Source < Struct
    # generate source types
    # inline __typecall (cls kind)

    # inline so it doesn't generate an actual function for every typedef unnecessarily
    inline play (self)
        soloud.play (unwrap-instance) self._handle

struct Sfxr < Source
    _handle : (mutable pointer soloud.Sfxr)
    inline __typecall (cls)
        let handle = (soloud.Sfxr_create)
        soloud.Sfxr_loadPreset handle soloud.SFXR_COIN 0
        super-type.__typecall cls
            _handle = handle

fn init ()
    # default settings, could be customizable later. Do plan on using miniaudio backend
    # in every OS.
    let _instance = (soloud.create)
    soloud.initEx _instance soloud.SOLOUD_CLIP_ROUNDOFF soloud.SOLOUD_MINIAUDIO 0 0 2
    typedef AudioLifetimeCookie :: (storageof Nothing)
        inline __drop (self)
            try
                let instance = ('unwrap instance)
                soloud.deinit instance
                soloud.destroy instance
            else;

        inline __typecall (cls)
            bitcast none this-type

    instance = _instance
    print "Audio module initialized."
    (AudioLifetimeCookie)

locals;
