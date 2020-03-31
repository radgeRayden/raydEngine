# typedef ArrayTypeInitializer
#     inline __static-imply (cls T)
#         dump cls T
#         inline (value)
#             (bitcast value Closure) T

# inline typeinit-array (...)
#     bitcast
#         inline (T)
#             T ...
#         typedef "typeinit" < ArrayTypeInitializer : (storageof Closure)

inline make-handle-type (name storageT)
    typedef (tostring name) :: storageT
        inline __typecall (cls init)
            bitcast (deref init) this-type
        inline __imply (cls other-cls)
            inline (self)
                bitcast (view self) other-cls
        inline __rimply (cls other-cls)
            this-type.__typecall
        inline __as (cls other-cls)
            inline (self)
                let value = (storagecast (view self))
                value as other-cls

sugar define-scope (name body...)
    qq
        [let] [name] =
            [do]
                unquote-splice body...
                (locals)

locals;
