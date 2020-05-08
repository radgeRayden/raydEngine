#include "glad/glad.h"

#define GLFW_INCLUDE_NONE
#include "GLFW/glfw3.h"

#ifdef RAYDENGINE_LINUX
    #define GLFW_EXPOSE_NATIVE_X11
    #include "GLFW/glfw3native.h"
    typedef Display X11Display;
#endif
#ifdef RAYDENGINE_WINDOWS
    #define GLFW_EXPOSE_NATIVE_WIN32
    #include "GLFW/glfw3native.h"
#endif


#define IMGUI_IMPL_OPENGL_LOADER_GLAD
#define IMGUI_IMPL_API extern
#define CIMGUI_DEFINE_ENUMS_AND_STRUCTS
#include "imgui/cimgui.h"
#include "imgui/imgui_impl_opengl3.h"
#include "imgui/imgui_impl_glfw.h"
