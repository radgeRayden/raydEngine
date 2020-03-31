switch operating-system
case 'linux
    load-library (module-dir .. "/lib/libphysfs.so")
case 'windows
    load-library (module-dir .. "/lib/physfs.dll")
default
    ;

vvv bind physfs
do
    let c-scope =
        include (options  (.. "-I" module-dir "/include"))
            "physfs.h"
    do
        using c-scope.extern
        using c-scope.typedef
        using c-scope.define
        locals;

let physfs =
    fold (scope = physfs) for k v in physfs
        let name = ((k as Symbol) as string)
        if ('match? "^PHYSFS_" name)
            let new-name = (rslice name (countof "PHYSFS_"))
            'bind scope (Symbol new-name) v
        else
            scope
run-stage;

let physfs =
    ..
        do
            fn last-error ()
                let error-code = (physfs.getLastErrorCode) 
                let error-string = (physfs.getErrorByCode error-code)
                if (error-string == null)
                    hide-traceback;
                    error "Unknown PHYSFS error encountered."
                "PHYSFS: " .. (string error-string)
            locals;
        physfs
