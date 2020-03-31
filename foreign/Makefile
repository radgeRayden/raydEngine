#
# Runtime library for dependencies that require an in-tree build
# Cross Platform Makefile
# Compatible with Ubuntu 14.04.1 and macOS

# all cimgui compilation happens here
OBJS = ./src/cimgui/cimgui.o
OBJS += ./src/cimgui/imgui/imgui.o
OBJS += ./src/cimgui/imgui/imgui_draw.o
OBJS += ./src/cimgui/imgui/imgui_demo.o
OBJS += ./src/cimgui/imgui/imgui_widgets.o
# and the imgui backends. Headers are modified to work with C.
OBJS += ./src/imgui_impl_glfw_opengl3/imgui_impl_glfw.o
OBJS += ./src/imgui_impl_glfw_opengl3/imgui_impl_opengl3.o
# glad
OBJS += ./src/glad/src/glad.o
# stb stuff
OBJS += ./src/stb/stb.o

CXXFLAGS=-O2 -fno-exceptions -fno-rtti -DIMGUI_IMPL_OPENGL_LOADER_GLAD -DRAYDENGINE_OFFLINE_BUILD

UNAME_S := $(shell uname -s)

ifeq ($(UNAME_S), Linux) #LINUX
	ECHO_MESSAGE = "Linux"

	OUTPUTNAME = ./lib/raydengine.so
	CXXFLAGS += -I./include -I./include/imgui
	CXXFLAGS += -Wall
	CXXFLAGS += -shared -fPIC
	CFLAGS = $(CXXFLAGS)
endif

ifeq ($(OS), Windows_NT)
	ECHO_MESSAGE = "Windows"

	OUTPUTNAME = ./lib/raydengine.dll
	CXXFLAGS += -I./include -I./cimgui/imgui/ -I.
	CXXFLAGS += -Wall
	CXXFLAGS += -shared
	LINKFLAGS = -limm32
	CFLAGS = $(CXXFLAGS)
endif

.cpp.o:
	$(CXX) $(CXXFLAGS) -c -o $@ $<

all:$(OUTPUTNAME)
	@echo Build complete for $(ECHO_MESSAGE)

$(OUTPUTNAME):$(OBJS)
	$(CXX) -o $(OUTPUTNAME) $(OBJS) $(CXXFLAGS) $(LINKFLAGS)

clean:
	rm -f $(OBJS)

fclean: clean
	rm -f $(OUTPUTNAME)

re: fclean all

.PHONY: all clean fclean re
