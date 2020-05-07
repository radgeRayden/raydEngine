""""sleep takes a time in seconds (real number) and sleeps for the closest amount of time
    supported by the OS clock granularity.
fn sleep (time)
    assert (time > 0) "can't sleep for a negative amount of time"
    microseconds := (time * (10 ** 6))
    static-match operating-system
    case 'linux
        let usleep =
            extern 'usleep (function i32 u32)
        usleep (microseconds as u32)
    case 'windows
        error "not implemented on windows"
    default
        error "operating system not supported"
locals;
