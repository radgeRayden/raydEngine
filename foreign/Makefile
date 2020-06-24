##
# Project Title
#
# @file
# @version 0.1

ifndef config
	config = debug
endif

OBJS = ./src/stb.o ./src/soloud/src/c_api/soloud_c.o ./src/glad/src/glad.o
INCLUDE_OPTS = -I./src/glad/include

PHYSFS_STATIC = ./src/physfs/build/libphysfs.a
GLFW3_DYNAMIC = ./src/glfw/build/src/libglfw.so

ifeq ($(config),debug)
	WGPU_STATIC = ./src/wgpu-native/target/debug/libwgpu_native.a
	SOLOUD_DYNAMIC = ./src/soloud/lib/libsoloud_x64_d.so
else
	WGPU_STATIC = ./src/wgpu-native/target/release/wgpu_native.dll
	SOLOUD_DYNAMIC = ./src/soloud/lib/libsoloud_x64..so
endif

STATIC_LIBS = $(PHYSFS_STATIC) $(WGPU_STATIC)
DYNAMIC_LIBS = $(GLFW3_DYNAMIC) $(SOLOUD_DYNAMIC)
HEADERS = physfs.h GLFW/ soloud/ wgpu.h stb_image.h

CFLAGS = -Wall -O2 -DRAYDENGINE_OFFLINE_BUILD -shared -fPIC -I./src/soloud/include $(INCLUDE_OPTS)
ifeq ($(config),debug)
	CFLAGS += -g
endif
CXXFLAGS = $(CFLAGS) -fno-exceptions -fno-rtti

UNAME_S := $(shell uname -s)
ifeq ($(UNAME_S), Linux)
	ECHO_MESSAGE = "Linux"
	MAKEFILE_FLAVOR = Unix
	MAKE = make
ifeq ($(config),debug)
	OUTPUTNAME = ./lib/libraydengine_d.so
else
	OUTPUTNAME = ./lib/libraydengine.so
endif
endif

ifeq ($(OS), Windows_NT)
	ECHO_MESSAGE = "Windows"
	MAKEFILE_FLAVOR = MinGW
	CC = x86_64-w64-mingw32-gcc
	CXX = x86_64-w64-mingw32-g++
	MAKE = mingw32-make

ifeq ($(config),debug)
	OUTPUTNAME = ./lib/raydengine_d.dll
else
	OUTPUTNAME = ./lib/raydengine.dll
endif
endif

all:$(OUTPUTNAME)
	@echo Build complete for $(ECHO_MESSAGE)

.cpp.o:
	$(CXX) $(CXXFLAGS) -c -o $@ $<

$(PHYSFS_STATIC):src/physfs
	mkdir -p ./src/physfs/build
	cmake -G "$(MAKEFILE_FLAVOR) Makefiles" -DCMAKE_C_COMPILER=$(CC) -DCMAKE_C_FLAGS=-fPIC -S ./src/physfs -B ./src/physfs/build
	${MAKE} -C ./src/physfs/build

$(GLFW3_DYNAMIC):src/glfw
	mkdir -p ./src/glfw/build
	cmake -G "$(MAKEFILE_FLAVOR) Makefiles" -S ./src/glfw -B ./src/glfw/build -DBUILD_SHARED_LIBS=on
	${MAKE} -C ./src/glfw/build

$(SOLOUD_DYNAMIC):src/soloud
	mkdir -p ./src/soloud/build
	genie --file=./src/soloud/build/genie.lua --with-miniaudio --with-nosound --platform=x64 gmake
ifeq ($(config),release)
	${MAKE} -C ./src/soloud/build/gmake config=release64 SoloudDynamic
else
	${MAKE} -C ./src/soloud/build/gmake config=debug64 SoloudDynamic
endif

$(WGPU_STATIC):src/wgpu-native
ifeq ($(config),release)
	cargo build --manifest-path=./src/wgpu-native/Cargo.toml --release
else
	cargo build --manifest-path=./src/wgpu-native/Cargo.toml
endif

$(OUTPUTNAME):$(DYNAMIC_LIBS) $(STATIC_LIBS) $(OBJS)
	mkdir -p ./lib
	$(CXX) -o $(OUTPUTNAME) $(OBJS) -shared $(LFLAGS) -Wl,--whole-archive $(STATIC_LIBS) -Wl,--no-whole-archive

install:
	mkdir -p include
	cp ./src/physfs/src/physfs.h ./include/
	cp -r ./src/glfw/include/GLFW ./include/
	cp -r ./src/soloud/include ./include/soloud
	cp ./src/wgpu-native/ffi/wgpu.h ./include/
	cp ./src/stb/stb_image.h ./include/
	cp $(DYNAMIC_LIBS) ./lib/

clean:
	${MAKE} -C ./src/physfs/build clean
	${MAKE} -C ./src/glfw/build clean
	${MAKE} -C ./src/soloud/build/gmake clean
	rm -rf ./src/wgpu-native/target
	rm -f $(OBJS)
	rm -rf ./lib

.PHONY: all install clean fclean re
# end
