typedef SCGenPointer < pointer
    inline __imply (A B)
        inline (self)
            imply (storagecast self) B
    inline __rimply (A B)
        inline (self other)
            imply self (storageof B)

let type-buffer = (alloca-array type 128)
let PHYSFS_compile_time_assert_uint16IsTwoBytes = (sc_typename_type "PHYSFS_compile_time_assert_uint16IsTwoBytes" array)
let PHYSFS_sint32 = (sc_typename_type "PHYSFS_sint32" integer)
let PHYSFS_compile_time_assert_sint32IsFourBytes = (sc_typename_type "PHYSFS_compile_time_assert_sint32IsFourBytes" array)
let PHYSFS_sint64 = (sc_typename_type "PHYSFS_sint64" integer)
let PHYSFS_uint8 = (sc_typename_type "PHYSFS_uint8" integer)
let PHYSFS_uint32 = (sc_typename_type "PHYSFS_uint32" integer)
let PHYSFS_compile_time_assert_uint64IsEightBytes = (sc_typename_type "PHYSFS_compile_time_assert_uint64IsEightBytes" array)
let PHYSFS_sint16 = (sc_typename_type "PHYSFS_sint16" integer)
let PHYSFS_sint8 = (sc_typename_type "PHYSFS_sint8" integer)
let PHYSFS_compile_time_assert_uint32IsFourBytes = (sc_typename_type "PHYSFS_compile_time_assert_uint32IsFourBytes" array)
let PHYSFS_uint16 = (sc_typename_type "PHYSFS_uint16" integer)
let PHYSFS_uint64 = (sc_typename_type "PHYSFS_uint64" integer)
let PHYSFS_compile_time_assert_sint16IsTwoBytes = (sc_typename_type "PHYSFS_compile_time_assert_sint16IsTwoBytes" array)
let PHYSFS_compile_time_assert_sint64IsEightBytes = (sc_typename_type "PHYSFS_compile_time_assert_sint64IsEightBytes" array)
let PHYSFS_compile_time_assert_uint8IsOneByte = (sc_typename_type "PHYSFS_compile_time_assert_uint8IsOneByte" array)
let PHYSFS_compile_time_assert_sint8IsOneByte = (sc_typename_type "PHYSFS_compile_time_assert_sint8IsOneByte" array)
sc_typename_type_set_storage PHYSFS_uint8 u8 typename-flag-plain
sc_typename_type_set_storage PHYSFS_sint8 i8 typename-flag-plain
sc_typename_type_set_storage PHYSFS_uint16 u16 typename-flag-plain
sc_typename_type_set_storage PHYSFS_sint16 i16 typename-flag-plain
sc_typename_type_set_storage PHYSFS_uint32 u32 typename-flag-plain
sc_typename_type_set_storage PHYSFS_sint32 i32 typename-flag-plain
sc_typename_type_set_storage PHYSFS_uint64 u64 typename-flag-plain
sc_typename_type_set_storage PHYSFS_sint64 i64 typename-flag-plain
sc_typename_type_set_storage PHYSFS_compile_time_assert_uint8IsOneByte (sc_array_type i32 1) typename-flag-plain
sc_typename_type_set_storage PHYSFS_compile_time_assert_sint8IsOneByte (sc_array_type i32 1) typename-flag-plain
sc_typename_type_set_storage PHYSFS_compile_time_assert_uint16IsTwoBytes (sc_array_type i32 1) typename-flag-plain
sc_typename_type_set_storage PHYSFS_compile_time_assert_sint16IsTwoBytes (sc_array_type i32 1) typename-flag-plain
sc_typename_type_set_storage PHYSFS_compile_time_assert_uint32IsFourBytes (sc_array_type i32 1) typename-flag-plain
sc_typename_type_set_storage PHYSFS_compile_time_assert_sint32IsFourBytes (sc_array_type i32 1) typename-flag-plain
sc_typename_type_set_storage PHYSFS_compile_time_assert_uint64IsEightBytes (sc_array_type i32 1) typename-flag-plain
sc_typename_type_set_storage PHYSFS_compile_time_assert_sint64IsEightBytes (sc_array_type i32 1) typename-flag-plain
store (sc_key_type 'major u8) (getelementptr type-buffer 0)
store (sc_key_type 'minor u8) (getelementptr type-buffer 1)
store (sc_key_type 'patch u8) (getelementptr type-buffer 2)
let Unknown = (sc_tuple_type 3 type-buffer)
let _gensc_mutable@<Unknown> = (sc_pointer_type Unknown 0:u64 unnamed)
let _gensc_@<i8> = (sc_pointer_type i8 2:u64 unnamed)
let _gensc_@<Unknown> = (sc_pointer_type Unknown 2:u64 unnamed)
let _gensc_mutable@<_gensc_@<Unknown>> = (sc_pointer_type _gensc_@<Unknown> 0:u64 unnamed)
let _gensc_@<void> = (sc_pointer_type void 2:u64 unnamed)
let _gensc_mutable@<i8> = (sc_pointer_type i8 0:u64 unnamed)
let _gensc_mutable@<_gensc_mutable@<i8>> = (sc_pointer_type _gensc_mutable@<i8> 0:u64 unnamed)
let _gensc_mutable@<i16> = (sc_pointer_type i16 0:u64 unnamed)
let _gensc_mutable@<u16> = (sc_pointer_type u16 0:u64 unnamed)
let _gensc_mutable@<i32> = (sc_pointer_type i32 0:u64 unnamed)
let _gensc_mutable@<u32> = (sc_pointer_type u32 0:u64 unnamed)
let _gensc_mutable@<i64> = (sc_pointer_type i64 0:u64 unnamed)
let _gensc_mutable@<u64> = (sc_pointer_type u64 0:u64 unnamed)
let _gensc_@<u32> = (sc_pointer_type u32 2:u64 unnamed)
let _gensc_@<u16> = (sc_pointer_type u16 2:u64 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
let PHYSFS_getLinkedVersion = (sc_global_new 'PHYSFS_getLinkedVersion (sc_function_type void 1 type-buffer) 6 unnamed)
store _gensc_@<i8> (getelementptr type-buffer 0)
let PHYSFS_init = (sc_global_new 'PHYSFS_init (sc_function_type i32 1 type-buffer) 6 unnamed)
let PHYSFS_deinit = (sc_global_new 'PHYSFS_deinit (sc_function_type i32 0 type-buffer) 6 unnamed)
let PHYSFS_supportedArchiveTypes = (sc_global_new 'PHYSFS_supportedArchiveTypes (sc_function_type _gensc_mutable@<_gensc_@<Unknown>> 0 type-buffer) 6 unnamed)
store _gensc_@<void> (getelementptr type-buffer 0)
let PHYSFS_freeList = (sc_global_new 'PHYSFS_freeList (sc_function_type void 1 type-buffer) 6 unnamed)
let PHYSFS_getLastError = (sc_global_new 'PHYSFS_getLastError (sc_function_type _gensc_@<i8> 0 type-buffer) 6 unnamed)
let PHYSFS_getDirSeparator = (sc_global_new 'PHYSFS_getDirSeparator (sc_function_type _gensc_@<i8> 0 type-buffer) 6 unnamed)
store i32 (getelementptr type-buffer 0)
let PHYSFS_permitSymbolicLinks = (sc_global_new 'PHYSFS_permitSymbolicLinks (sc_function_type void 1 type-buffer) 6 unnamed)
let PHYSFS_getCdRomDirs = (sc_global_new 'PHYSFS_getCdRomDirs (sc_function_type _gensc_mutable@<_gensc_mutable@<i8>> 0 type-buffer) 6 unnamed)
let PHYSFS_getBaseDir = (sc_global_new 'PHYSFS_getBaseDir (sc_function_type _gensc_@<i8> 0 type-buffer) 6 unnamed)
let PHYSFS_getUserDir = (sc_global_new 'PHYSFS_getUserDir (sc_function_type _gensc_@<i8> 0 type-buffer) 6 unnamed)
let PHYSFS_getWriteDir = (sc_global_new 'PHYSFS_getWriteDir (sc_function_type _gensc_@<i8> 0 type-buffer) 6 unnamed)
store _gensc_@<i8> (getelementptr type-buffer 0)
let PHYSFS_setWriteDir = (sc_global_new 'PHYSFS_setWriteDir (sc_function_type i32 1 type-buffer) 6 unnamed)
store _gensc_@<i8> (getelementptr type-buffer 0)
store i32 (getelementptr type-buffer 1)
let PHYSFS_addToSearchPath = (sc_global_new 'PHYSFS_addToSearchPath (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_@<i8> (getelementptr type-buffer 0)
let PHYSFS_removeFromSearchPath = (sc_global_new 'PHYSFS_removeFromSearchPath (sc_function_type i32 1 type-buffer) 6 unnamed)
let PHYSFS_getSearchPath = (sc_global_new 'PHYSFS_getSearchPath (sc_function_type _gensc_mutable@<_gensc_mutable@<i8>> 0 type-buffer) 6 unnamed)
store _gensc_@<i8> (getelementptr type-buffer 0)
store _gensc_@<i8> (getelementptr type-buffer 1)
store _gensc_@<i8> (getelementptr type-buffer 2)
store i32 (getelementptr type-buffer 3)
store i32 (getelementptr type-buffer 4)
let PHYSFS_setSaneConfig = (sc_global_new 'PHYSFS_setSaneConfig (sc_function_type i32 5 type-buffer) 6 unnamed)
store _gensc_@<i8> (getelementptr type-buffer 0)
let PHYSFS_mkdir = (sc_global_new 'PHYSFS_mkdir (sc_function_type i32 1 type-buffer) 6 unnamed)
store _gensc_@<i8> (getelementptr type-buffer 0)
let PHYSFS_delete = (sc_global_new 'PHYSFS_delete (sc_function_type i32 1 type-buffer) 6 unnamed)
store _gensc_@<i8> (getelementptr type-buffer 0)
let PHYSFS_getRealDir = (sc_global_new 'PHYSFS_getRealDir (sc_function_type _gensc_@<i8> 1 type-buffer) 6 unnamed)
store _gensc_@<i8> (getelementptr type-buffer 0)
let PHYSFS_enumerateFiles = (sc_global_new 'PHYSFS_enumerateFiles (sc_function_type _gensc_mutable@<_gensc_mutable@<i8>> 1 type-buffer) 6 unnamed)
store _gensc_@<i8> (getelementptr type-buffer 0)
let PHYSFS_exists = (sc_global_new 'PHYSFS_exists (sc_function_type i32 1 type-buffer) 6 unnamed)
store _gensc_@<i8> (getelementptr type-buffer 0)
let PHYSFS_isDirectory = (sc_global_new 'PHYSFS_isDirectory (sc_function_type i32 1 type-buffer) 6 unnamed)
store _gensc_@<i8> (getelementptr type-buffer 0)
let PHYSFS_isSymbolicLink = (sc_global_new 'PHYSFS_isSymbolicLink (sc_function_type i32 1 type-buffer) 6 unnamed)
store _gensc_@<i8> (getelementptr type-buffer 0)
let PHYSFS_getLastModTime = (sc_global_new 'PHYSFS_getLastModTime (sc_function_type i64 1 type-buffer) 6 unnamed)
store _gensc_@<i8> (getelementptr type-buffer 0)
let PHYSFS_openWrite = (sc_global_new 'PHYSFS_openWrite (sc_function_type _gensc_mutable@<Unknown> 1 type-buffer) 6 unnamed)
store _gensc_@<i8> (getelementptr type-buffer 0)
let PHYSFS_openAppend = (sc_global_new 'PHYSFS_openAppend (sc_function_type _gensc_mutable@<Unknown> 1 type-buffer) 6 unnamed)
store _gensc_@<i8> (getelementptr type-buffer 0)
let PHYSFS_openRead = (sc_global_new 'PHYSFS_openRead (sc_function_type _gensc_mutable@<Unknown> 1 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
let PHYSFS_close = (sc_global_new 'PHYSFS_close (sc_function_type i32 1 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
store _gensc_@<void> (getelementptr type-buffer 1)
store u32 (getelementptr type-buffer 2)
store u32 (getelementptr type-buffer 3)
let PHYSFS_read = (sc_global_new 'PHYSFS_read (sc_function_type i64 4 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
store _gensc_@<void> (getelementptr type-buffer 1)
store u32 (getelementptr type-buffer 2)
store u32 (getelementptr type-buffer 3)
let PHYSFS_write = (sc_global_new 'PHYSFS_write (sc_function_type i64 4 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
let PHYSFS_eof = (sc_global_new 'PHYSFS_eof (sc_function_type i32 1 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
let PHYSFS_tell = (sc_global_new 'PHYSFS_tell (sc_function_type i64 1 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
store u64 (getelementptr type-buffer 1)
let PHYSFS_seek = (sc_global_new 'PHYSFS_seek (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
let PHYSFS_fileLength = (sc_global_new 'PHYSFS_fileLength (sc_function_type i64 1 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
store u64 (getelementptr type-buffer 1)
let PHYSFS_setBuffer = (sc_global_new 'PHYSFS_setBuffer (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
let PHYSFS_flush = (sc_global_new 'PHYSFS_flush (sc_function_type i32 1 type-buffer) 6 unnamed)
store i16 (getelementptr type-buffer 0)
let PHYSFS_swapSLE16 = (sc_global_new 'PHYSFS_swapSLE16 (sc_function_type i16 1 type-buffer) 6 unnamed)
store u16 (getelementptr type-buffer 0)
let PHYSFS_swapULE16 = (sc_global_new 'PHYSFS_swapULE16 (sc_function_type u16 1 type-buffer) 6 unnamed)
store i32 (getelementptr type-buffer 0)
let PHYSFS_swapSLE32 = (sc_global_new 'PHYSFS_swapSLE32 (sc_function_type i32 1 type-buffer) 6 unnamed)
store u32 (getelementptr type-buffer 0)
let PHYSFS_swapULE32 = (sc_global_new 'PHYSFS_swapULE32 (sc_function_type u32 1 type-buffer) 6 unnamed)
store i64 (getelementptr type-buffer 0)
let PHYSFS_swapSLE64 = (sc_global_new 'PHYSFS_swapSLE64 (sc_function_type i64 1 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
let PHYSFS_swapULE64 = (sc_global_new 'PHYSFS_swapULE64 (sc_function_type u64 1 type-buffer) 6 unnamed)
store i16 (getelementptr type-buffer 0)
let PHYSFS_swapSBE16 = (sc_global_new 'PHYSFS_swapSBE16 (sc_function_type i16 1 type-buffer) 6 unnamed)
store u16 (getelementptr type-buffer 0)
let PHYSFS_swapUBE16 = (sc_global_new 'PHYSFS_swapUBE16 (sc_function_type u16 1 type-buffer) 6 unnamed)
store i32 (getelementptr type-buffer 0)
let PHYSFS_swapSBE32 = (sc_global_new 'PHYSFS_swapSBE32 (sc_function_type i32 1 type-buffer) 6 unnamed)
store u32 (getelementptr type-buffer 0)
let PHYSFS_swapUBE32 = (sc_global_new 'PHYSFS_swapUBE32 (sc_function_type u32 1 type-buffer) 6 unnamed)
store i64 (getelementptr type-buffer 0)
let PHYSFS_swapSBE64 = (sc_global_new 'PHYSFS_swapSBE64 (sc_function_type i64 1 type-buffer) 6 unnamed)
store u64 (getelementptr type-buffer 0)
let PHYSFS_swapUBE64 = (sc_global_new 'PHYSFS_swapUBE64 (sc_function_type u64 1 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
store _gensc_mutable@<i16> (getelementptr type-buffer 1)
let PHYSFS_readSLE16 = (sc_global_new 'PHYSFS_readSLE16 (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
store _gensc_mutable@<u16> (getelementptr type-buffer 1)
let PHYSFS_readULE16 = (sc_global_new 'PHYSFS_readULE16 (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
store _gensc_mutable@<i16> (getelementptr type-buffer 1)
let PHYSFS_readSBE16 = (sc_global_new 'PHYSFS_readSBE16 (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
store _gensc_mutable@<u16> (getelementptr type-buffer 1)
let PHYSFS_readUBE16 = (sc_global_new 'PHYSFS_readUBE16 (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
store _gensc_mutable@<i32> (getelementptr type-buffer 1)
let PHYSFS_readSLE32 = (sc_global_new 'PHYSFS_readSLE32 (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
store _gensc_mutable@<u32> (getelementptr type-buffer 1)
let PHYSFS_readULE32 = (sc_global_new 'PHYSFS_readULE32 (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
store _gensc_mutable@<i32> (getelementptr type-buffer 1)
let PHYSFS_readSBE32 = (sc_global_new 'PHYSFS_readSBE32 (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
store _gensc_mutable@<u32> (getelementptr type-buffer 1)
let PHYSFS_readUBE32 = (sc_global_new 'PHYSFS_readUBE32 (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
store _gensc_mutable@<i64> (getelementptr type-buffer 1)
let PHYSFS_readSLE64 = (sc_global_new 'PHYSFS_readSLE64 (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
store _gensc_mutable@<u64> (getelementptr type-buffer 1)
let PHYSFS_readULE64 = (sc_global_new 'PHYSFS_readULE64 (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
store _gensc_mutable@<i64> (getelementptr type-buffer 1)
let PHYSFS_readSBE64 = (sc_global_new 'PHYSFS_readSBE64 (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
store _gensc_mutable@<u64> (getelementptr type-buffer 1)
let PHYSFS_readUBE64 = (sc_global_new 'PHYSFS_readUBE64 (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
store i16 (getelementptr type-buffer 1)
let PHYSFS_writeSLE16 = (sc_global_new 'PHYSFS_writeSLE16 (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
store u16 (getelementptr type-buffer 1)
let PHYSFS_writeULE16 = (sc_global_new 'PHYSFS_writeULE16 (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
store i16 (getelementptr type-buffer 1)
let PHYSFS_writeSBE16 = (sc_global_new 'PHYSFS_writeSBE16 (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
store u16 (getelementptr type-buffer 1)
let PHYSFS_writeUBE16 = (sc_global_new 'PHYSFS_writeUBE16 (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
store i32 (getelementptr type-buffer 1)
let PHYSFS_writeSLE32 = (sc_global_new 'PHYSFS_writeSLE32 (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
store u32 (getelementptr type-buffer 1)
let PHYSFS_writeULE32 = (sc_global_new 'PHYSFS_writeULE32 (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
store i32 (getelementptr type-buffer 1)
let PHYSFS_writeSBE32 = (sc_global_new 'PHYSFS_writeSBE32 (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
store u32 (getelementptr type-buffer 1)
let PHYSFS_writeUBE32 = (sc_global_new 'PHYSFS_writeUBE32 (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
store i64 (getelementptr type-buffer 1)
let PHYSFS_writeSLE64 = (sc_global_new 'PHYSFS_writeSLE64 (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
store u64 (getelementptr type-buffer 1)
let PHYSFS_writeULE64 = (sc_global_new 'PHYSFS_writeULE64 (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
store i64 (getelementptr type-buffer 1)
let PHYSFS_writeSBE64 = (sc_global_new 'PHYSFS_writeSBE64 (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
store u64 (getelementptr type-buffer 1)
let PHYSFS_writeUBE64 = (sc_global_new 'PHYSFS_writeUBE64 (sc_function_type i32 2 type-buffer) 6 unnamed)
let PHYSFS_isInit = (sc_global_new 'PHYSFS_isInit (sc_function_type i32 0 type-buffer) 6 unnamed)
let PHYSFS_symbolicLinksPermitted = (sc_global_new 'PHYSFS_symbolicLinksPermitted (sc_function_type i32 0 type-buffer) 6 unnamed)
store _gensc_@<Unknown> (getelementptr type-buffer 0)
let PHYSFS_setAllocator = (sc_global_new 'PHYSFS_setAllocator (sc_function_type i32 1 type-buffer) 6 unnamed)
store _gensc_@<i8> (getelementptr type-buffer 0)
store _gensc_@<i8> (getelementptr type-buffer 1)
store i32 (getelementptr type-buffer 2)
let PHYSFS_mount = (sc_global_new 'PHYSFS_mount (sc_function_type i32 3 type-buffer) 6 unnamed)
store _gensc_@<i8> (getelementptr type-buffer 0)
let PHYSFS_getMountPoint = (sc_global_new 'PHYSFS_getMountPoint (sc_function_type _gensc_@<i8> 1 type-buffer) 6 unnamed)
store _gensc_@<Unknown> (getelementptr type-buffer 0)
store _gensc_@<void> (getelementptr type-buffer 1)
let PHYSFS_getCdRomDirsCallback = (sc_global_new 'PHYSFS_getCdRomDirsCallback (sc_function_type void 2 type-buffer) 6 unnamed)
store _gensc_@<Unknown> (getelementptr type-buffer 0)
store _gensc_@<void> (getelementptr type-buffer 1)
let PHYSFS_getSearchPathCallback = (sc_global_new 'PHYSFS_getSearchPathCallback (sc_function_type void 2 type-buffer) 6 unnamed)
store _gensc_@<i8> (getelementptr type-buffer 0)
store _gensc_@<Unknown> (getelementptr type-buffer 1)
store _gensc_@<void> (getelementptr type-buffer 2)
let PHYSFS_enumerateFilesCallback = (sc_global_new 'PHYSFS_enumerateFilesCallback (sc_function_type void 3 type-buffer) 6 unnamed)
store _gensc_@<u32> (getelementptr type-buffer 0)
store _gensc_mutable@<i8> (getelementptr type-buffer 1)
store u64 (getelementptr type-buffer 2)
let PHYSFS_utf8FromUcs4 = (sc_global_new 'PHYSFS_utf8FromUcs4 (sc_function_type void 3 type-buffer) 6 unnamed)
store _gensc_@<i8> (getelementptr type-buffer 0)
store _gensc_mutable@<u32> (getelementptr type-buffer 1)
store u64 (getelementptr type-buffer 2)
let PHYSFS_utf8ToUcs4 = (sc_global_new 'PHYSFS_utf8ToUcs4 (sc_function_type void 3 type-buffer) 6 unnamed)
store _gensc_@<u16> (getelementptr type-buffer 0)
store _gensc_mutable@<i8> (getelementptr type-buffer 1)
store u64 (getelementptr type-buffer 2)
let PHYSFS_utf8FromUcs2 = (sc_global_new 'PHYSFS_utf8FromUcs2 (sc_function_type void 3 type-buffer) 6 unnamed)
store _gensc_@<i8> (getelementptr type-buffer 0)
store _gensc_mutable@<u16> (getelementptr type-buffer 1)
store u64 (getelementptr type-buffer 2)
let PHYSFS_utf8ToUcs2 = (sc_global_new 'PHYSFS_utf8ToUcs2 (sc_function_type void 3 type-buffer) 6 unnamed)
store _gensc_@<i8> (getelementptr type-buffer 0)
store _gensc_mutable@<i8> (getelementptr type-buffer 1)
store u64 (getelementptr type-buffer 2)
let PHYSFS_utf8FromLatin1 = (sc_global_new 'PHYSFS_utf8FromLatin1 (sc_function_type void 3 type-buffer) 6 unnamed)
store u32 (getelementptr type-buffer 0)
store _gensc_mutable@<u32> (getelementptr type-buffer 1)
let PHYSFS_caseFold = (sc_global_new 'PHYSFS_caseFold (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_@<i8> (getelementptr type-buffer 0)
store _gensc_@<i8> (getelementptr type-buffer 1)
let PHYSFS_utf8stricmp = (sc_global_new 'PHYSFS_utf8stricmp (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_@<u16> (getelementptr type-buffer 0)
store _gensc_@<u16> (getelementptr type-buffer 1)
let PHYSFS_utf16stricmp = (sc_global_new 'PHYSFS_utf16stricmp (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_@<u32> (getelementptr type-buffer 0)
store _gensc_@<u32> (getelementptr type-buffer 1)
let PHYSFS_ucs4stricmp = (sc_global_new 'PHYSFS_ucs4stricmp (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_@<i8> (getelementptr type-buffer 0)
store _gensc_@<Unknown> (getelementptr type-buffer 1)
store _gensc_@<void> (getelementptr type-buffer 2)
let PHYSFS_enumerate = (sc_global_new 'PHYSFS_enumerate (sc_function_type i32 3 type-buffer) 6 unnamed)
store _gensc_@<i8> (getelementptr type-buffer 0)
let PHYSFS_unmount = (sc_global_new 'PHYSFS_unmount (sc_function_type i32 1 type-buffer) 6 unnamed)
let PHYSFS_getAllocator = (sc_global_new 'PHYSFS_getAllocator (sc_function_type _gensc_@<Unknown> 0 type-buffer) 6 unnamed)
store _gensc_@<i8> (getelementptr type-buffer 0)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 1)
let PHYSFS_stat = (sc_global_new 'PHYSFS_stat (sc_function_type i32 2 type-buffer) 6 unnamed)
store _gensc_@<u16> (getelementptr type-buffer 0)
store _gensc_mutable@<i8> (getelementptr type-buffer 1)
store u64 (getelementptr type-buffer 2)
let PHYSFS_utf8FromUtf16 = (sc_global_new 'PHYSFS_utf8FromUtf16 (sc_function_type void 3 type-buffer) 6 unnamed)
store _gensc_@<i8> (getelementptr type-buffer 0)
store _gensc_mutable@<u16> (getelementptr type-buffer 1)
store u64 (getelementptr type-buffer 2)
let PHYSFS_utf8ToUtf16 = (sc_global_new 'PHYSFS_utf8ToUtf16 (sc_function_type void 3 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
store _gensc_@<void> (getelementptr type-buffer 1)
store u64 (getelementptr type-buffer 2)
let PHYSFS_readBytes = (sc_global_new 'PHYSFS_readBytes (sc_function_type i64 3 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
store _gensc_@<void> (getelementptr type-buffer 1)
store u64 (getelementptr type-buffer 2)
let PHYSFS_writeBytes = (sc_global_new 'PHYSFS_writeBytes (sc_function_type i64 3 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
store _gensc_@<i8> (getelementptr type-buffer 1)
store _gensc_@<i8> (getelementptr type-buffer 2)
store i32 (getelementptr type-buffer 3)
let PHYSFS_mountIo = (sc_global_new 'PHYSFS_mountIo (sc_function_type i32 4 type-buffer) 6 unnamed)
store _gensc_@<void> (getelementptr type-buffer 0)
store u64 (getelementptr type-buffer 1)
store _gensc_@<Unknown> (getelementptr type-buffer 2)
store _gensc_@<i8> (getelementptr type-buffer 3)
store _gensc_@<i8> (getelementptr type-buffer 4)
store i32 (getelementptr type-buffer 5)
let PHYSFS_mountMemory = (sc_global_new 'PHYSFS_mountMemory (sc_function_type i32 6 type-buffer) 6 unnamed)
store _gensc_mutable@<Unknown> (getelementptr type-buffer 0)
store _gensc_@<i8> (getelementptr type-buffer 1)
store _gensc_@<i8> (getelementptr type-buffer 2)
store i32 (getelementptr type-buffer 3)
let PHYSFS_mountHandle = (sc_global_new 'PHYSFS_mountHandle (sc_function_type i32 4 type-buffer) 6 unnamed)
let PHYSFS_getLastErrorCode = (sc_global_new 'PHYSFS_getLastErrorCode (sc_function_type Unknown 0 type-buffer) 6 unnamed)
store Unknown (getelementptr type-buffer 0)
let PHYSFS_getErrorByCode = (sc_global_new 'PHYSFS_getErrorByCode (sc_function_type _gensc_@<i8> 1 type-buffer) 6 unnamed)
store Unknown (getelementptr type-buffer 0)
let PHYSFS_setErrorCode = (sc_global_new 'PHYSFS_setErrorCode (sc_function_type void 1 type-buffer) 6 unnamed)
store _gensc_@<i8> (getelementptr type-buffer 0)
store _gensc_@<i8> (getelementptr type-buffer 1)
let PHYSFS_getPrefDir = (sc_global_new 'PHYSFS_getPrefDir (sc_function_type _gensc_@<i8> 2 type-buffer) 6 unnamed)
store _gensc_@<Unknown> (getelementptr type-buffer 0)
let PHYSFS_registerArchiver = (sc_global_new 'PHYSFS_registerArchiver (sc_function_type i32 1 type-buffer) 6 unnamed)
store _gensc_@<i8> (getelementptr type-buffer 0)
let PHYSFS_deregisterArchiver = (sc_global_new 'PHYSFS_deregisterArchiver (sc_function_type i32 1 type-buffer) 6 unnamed)
do
    let compile_time_assert_uint16IsTwoBytes = PHYSFS_compile_time_assert_uint16IsTwoBytes
    let sint32 = PHYSFS_sint32
    let compile_time_assert_sint32IsFourBytes = PHYSFS_compile_time_assert_sint32IsFourBytes
    let sint64 = PHYSFS_sint64
    let uint8 = PHYSFS_uint8
    let uint32 = PHYSFS_uint32
    let compile_time_assert_uint64IsEightBytes = PHYSFS_compile_time_assert_uint64IsEightBytes
    let sint16 = PHYSFS_sint16
    let sint8 = PHYSFS_sint8
    let compile_time_assert_uint32IsFourBytes = PHYSFS_compile_time_assert_uint32IsFourBytes
    let uint16 = PHYSFS_uint16
    let uint64 = PHYSFS_uint64
    let compile_time_assert_sint16IsTwoBytes = PHYSFS_compile_time_assert_sint16IsTwoBytes
    let compile_time_assert_sint64IsEightBytes = PHYSFS_compile_time_assert_sint64IsEightBytes
    let compile_time_assert_uint8IsOneByte = PHYSFS_compile_time_assert_uint8IsOneByte
    let compile_time_assert_sint8IsOneByte = PHYSFS_compile_time_assert_sint8IsOneByte
    let getLinkedVersion = PHYSFS_getLinkedVersion
    let init = PHYSFS_init
    let deinit = PHYSFS_deinit
    let supportedArchiveTypes = PHYSFS_supportedArchiveTypes
    let freeList = PHYSFS_freeList
    let getLastError = PHYSFS_getLastError
    let getDirSeparator = PHYSFS_getDirSeparator
    let permitSymbolicLinks = PHYSFS_permitSymbolicLinks
    let getCdRomDirs = PHYSFS_getCdRomDirs
    let getBaseDir = PHYSFS_getBaseDir
    let getUserDir = PHYSFS_getUserDir
    let getWriteDir = PHYSFS_getWriteDir
    let setWriteDir = PHYSFS_setWriteDir
    let addToSearchPath = PHYSFS_addToSearchPath
    let removeFromSearchPath = PHYSFS_removeFromSearchPath
    let getSearchPath = PHYSFS_getSearchPath
    let setSaneConfig = PHYSFS_setSaneConfig
    let mkdir = PHYSFS_mkdir
    let delete = PHYSFS_delete
    let getRealDir = PHYSFS_getRealDir
    let enumerateFiles = PHYSFS_enumerateFiles
    let exists = PHYSFS_exists
    let isDirectory = PHYSFS_isDirectory
    let isSymbolicLink = PHYSFS_isSymbolicLink
    let getLastModTime = PHYSFS_getLastModTime
    let openWrite = PHYSFS_openWrite
    let openAppend = PHYSFS_openAppend
    let openRead = PHYSFS_openRead
    let close = PHYSFS_close
    let read = PHYSFS_read
    let write = PHYSFS_write
    let eof = PHYSFS_eof
    let tell = PHYSFS_tell
    let seek = PHYSFS_seek
    let fileLength = PHYSFS_fileLength
    let setBuffer = PHYSFS_setBuffer
    let flush = PHYSFS_flush
    let swapSLE16 = PHYSFS_swapSLE16
    let swapULE16 = PHYSFS_swapULE16
    let swapSLE32 = PHYSFS_swapSLE32
    let swapULE32 = PHYSFS_swapULE32
    let swapSLE64 = PHYSFS_swapSLE64
    let swapULE64 = PHYSFS_swapULE64
    let swapSBE16 = PHYSFS_swapSBE16
    let swapUBE16 = PHYSFS_swapUBE16
    let swapSBE32 = PHYSFS_swapSBE32
    let swapUBE32 = PHYSFS_swapUBE32
    let swapSBE64 = PHYSFS_swapSBE64
    let swapUBE64 = PHYSFS_swapUBE64
    let readSLE16 = PHYSFS_readSLE16
    let readULE16 = PHYSFS_readULE16
    let readSBE16 = PHYSFS_readSBE16
    let readUBE16 = PHYSFS_readUBE16
    let readSLE32 = PHYSFS_readSLE32
    let readULE32 = PHYSFS_readULE32
    let readSBE32 = PHYSFS_readSBE32
    let readUBE32 = PHYSFS_readUBE32
    let readSLE64 = PHYSFS_readSLE64
    let readULE64 = PHYSFS_readULE64
    let readSBE64 = PHYSFS_readSBE64
    let readUBE64 = PHYSFS_readUBE64
    let writeSLE16 = PHYSFS_writeSLE16
    let writeULE16 = PHYSFS_writeULE16
    let writeSBE16 = PHYSFS_writeSBE16
    let writeUBE16 = PHYSFS_writeUBE16
    let writeSLE32 = PHYSFS_writeSLE32
    let writeULE32 = PHYSFS_writeULE32
    let writeSBE32 = PHYSFS_writeSBE32
    let writeUBE32 = PHYSFS_writeUBE32
    let writeSLE64 = PHYSFS_writeSLE64
    let writeULE64 = PHYSFS_writeULE64
    let writeSBE64 = PHYSFS_writeSBE64
    let writeUBE64 = PHYSFS_writeUBE64
    let isInit = PHYSFS_isInit
    let symbolicLinksPermitted = PHYSFS_symbolicLinksPermitted
    let setAllocator = PHYSFS_setAllocator
    let mount = PHYSFS_mount
    let getMountPoint = PHYSFS_getMountPoint
    let getCdRomDirsCallback = PHYSFS_getCdRomDirsCallback
    let getSearchPathCallback = PHYSFS_getSearchPathCallback
    let enumerateFilesCallback = PHYSFS_enumerateFilesCallback
    let utf8FromUcs4 = PHYSFS_utf8FromUcs4
    let utf8ToUcs4 = PHYSFS_utf8ToUcs4
    let utf8FromUcs2 = PHYSFS_utf8FromUcs2
    let utf8ToUcs2 = PHYSFS_utf8ToUcs2
    let utf8FromLatin1 = PHYSFS_utf8FromLatin1
    let caseFold = PHYSFS_caseFold
    let utf8stricmp = PHYSFS_utf8stricmp
    let utf16stricmp = PHYSFS_utf16stricmp
    let ucs4stricmp = PHYSFS_ucs4stricmp
    let enumerate = PHYSFS_enumerate
    let unmount = PHYSFS_unmount
    let getAllocator = PHYSFS_getAllocator
    let stat = PHYSFS_stat
    let utf8FromUtf16 = PHYSFS_utf8FromUtf16
    let utf8ToUtf16 = PHYSFS_utf8ToUtf16
    let readBytes = PHYSFS_readBytes
    let writeBytes = PHYSFS_writeBytes
    let mountIo = PHYSFS_mountIo
    let mountMemory = PHYSFS_mountMemory
    let mountHandle = PHYSFS_mountHandle
    let getLastErrorCode = PHYSFS_getLastErrorCode
    let getErrorByCode = PHYSFS_getErrorByCode
    let setErrorCode = PHYSFS_setErrorCode
    let getPrefDir = PHYSFS_getPrefDir
    let registerArchiver = PHYSFS_registerArchiver
    let deregisterArchiver = PHYSFS_deregisterArchiver
    let PHYSFS_VER_MAJOR = 3
    let PHYSFS_VER_PATCH = 2
    let PHYSFS_VER_MINOR = 0
    locals;
