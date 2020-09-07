using import radlib.core-extensions

physfs := (import .foreign.physfs)

using import struct
using import property
using import Array

fn last-error ()
    let code = (physfs.getLastErrorCode)
    let message = (physfs.getErrorByCode code)
    string message

struct FileData
    data : (Array u8)
    filename : string
    Text :=
        property
            inline "get" (self)
                'get-text self
    Size :=
        property
            inline "get" (self)
                (countof self.data)

    fn get-text (self)
        let data-ptr = (imply self.data (pointer u8))
        let i8ptr = (bitcast data-ptr (pointer i8))
        string i8ptr (countof self.data)

fn init ()
    let argc argv = (launch-args)
    if (not (physfs.init (argv @ 0)))
        error "Failed to initialize PHYSFS."
    physfs.mount "." "/" true
    physfs.setWriteDir "."
    # TODO: no clean up?
    print "filesystem module initialized."

fn write (data filepath)
    let handle = (physfs.openWrite filepath)
    if (handle == null)
        hide-traceback;
        error@ ('anchor `filepath) "while trying to write a file" (last-error)
    let len = (Array-sizeof data)
    let written =
        (physfs.writeBytes handle ((imply data pointer) as voidstar) len) as usize
    if (written != len)
        # FIXME: this should be better.
        error "incomplete write"
    ;

fn _load (filepath)
    let file = (physfs.openRead filepath)
    if (file == null)
        hide-traceback;
        error@ ('anchor `file) "while trying to open a file" (last-error)

    local filedata =
        FileData
            # TODO: use regex to split this into the filename as you did on the highlighter
            filename = filepath
    # maybe naive? should I seek?
    let file-size = (physfs.fileLength file)
    data := filedata.data
    'resize filedata.data file-size
    let buf = data._items
    let read = (physfs.readBytes file data (file-size as u64))

    # FIXME: maybe shouldn't be fatal.
    if (read < file-size)
        if (physfs.eof file)
            error "Unexpected end of file."
        else
            error@ ('anchor `read) "while loading file into memory" ("PHYSFS: " .. (last-error))

    filedata

fn list-directory (path)
    let files = (physfs.enumerateFiles path)
    local filelist : (Array string)
    if (files != null)
        loop (i = 0)
            if ((files @ i) == null)
                break;
            'append filelist (string (files @ i))
            i + 1
    filelist

do
    load := _load
    let
        init
        write
        list-directory
        FileData
    locals;
