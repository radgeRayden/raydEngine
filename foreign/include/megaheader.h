#include "glad/glad.h"

#define GLFW_INCLUDE_NONE
#include "GLFW/glfw3.h"

#define IMGUI_IMPL_OPENGL_LOADER_GLAD
#define IMGUI_IMPL_API extern
#define CIMGUI_DEFINE_ENUMS_AND_STRUCTS
#include "imgui/cimgui.h"
#include "imgui/imgui_impl_opengl3.h"
#include "imgui/imgui_impl_glfw.h"
