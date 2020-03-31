soloud := (import ..foreign.soloud)
using import ..PRNG
import ..timer
import ..audio

local rng = (random.RNG (timer.clock-time))
audio.init;

# let fx = (soloud.Sfxr_create)
# let seed = 0# ((rng) as i32)
# let result = (soloud.Sfxr_loadPreset fx soloud.SFXR_COIN seed)
# soloud.play instance fx
let fx = (audio.Sfxr)
'play fx
while ((soloud.getActiveVoiceCount (audio.unwrap-instance)) > 0)
    ;
soloud.Sfxr_destroy fx._handle
