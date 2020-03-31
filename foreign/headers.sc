# headers.sc imports all symbols that must coexist in the same translation unit, to avoid
# redeclarations that would make types incompatible.
let exports =
    include "include/megaheader.h"
