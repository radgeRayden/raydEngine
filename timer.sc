# most of this code was 'translated' from sokol_time.h, which was already pretty simple.
# TODO: add a "quick import" form to the foreign library
let C.time =
    do
        let time-h = (include "time.h")
        using time-h.extern
        using time-h.enum
        using time-h.struct
        using time-h.typedef
        using time-h.define
        locals;

using import struct
fn clock-time ()
    local ts : C.time.timespec
    C.time.clock_gettime C.time.CLOCK_MONOTONIC &ts
    let seconds = ((ts.tv_sec * 1000000000) as u64)
    let nanoseconds = (ts.tv_nsec as u64)
    seconds + nanoseconds

fn ticks->nanoseconds (ticks)
    (ticks as f64)

fn ticks->microseconds (ticks)
    (ticks as f64) / 1000 # 1 thousand nanoseconds

fn ticks->miliseconds (ticks)
    (ticks as f64) / 1000000 # 1 million nanoseconds

fn ticks->seconds (ticks)
    (ticks as f64) / 1000000000 # 1 billion nanoseconds

struct Timer plain
    start : u64
    last-update : u64
    last-dt : f64

    inline __typecall (cls)
        let now = (clock-time)
        Struct.__typecall cls
            start = now
            last-update = now

    inline run-time (self)
        ticks->seconds (self.last-update - self.start)

    # sub frame precision timing
    inline run-time-real (self)
        ticks->seconds ((clock-time) - self.start)

    # must be called at the start of each frame / update cycle so time measurements are correct
    fn step (self)
        let current-ticks = (clock-time)
        let dt-ticks = (current-ticks - self.last-update)
        self.last-update = current-ticks
        let dt = (ticks->seconds dt-ticks)
        self.last-dt = dt
        ;

    inline delta-time (self)
        self.last-dt

    fn fps (self)
        1.0 / self.last-dt

do
    let
        Timer
        clock-time
        ticks->nanoseconds
        ticks->microseconds
        ticks->miliseconds
        ticks->seconds
    locals;
