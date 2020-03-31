#!/usr/bin/env bash

mkdir lib
mkdir .build
pushd .build

# PHYSFS
cmake ../src/physfs/
make -j4
cp libphysfs.so ../lib
rm -r *

# GLFW
cmake -DBUILD_SHARED_LIBS=ON ../src/glfw
make -j4
pushd src
cp libglfw.so ../../lib
popd
rm -r *

# SOLOUD
popd
# foreign
pushd ./src/soloud/build/
genie --with-miniaudio --with-nosound --platform=x64 --os=linux gmake
pushd gmake
make -j4 config=release64 SoloudDynamic
popd
rm -r gmake
pushd ../lib
cp libsoloud_x64.so ../../../lib
rm *
popd
# foreign
popd
make

rm -r .build
