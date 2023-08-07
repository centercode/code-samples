cmake_minimum_required(VERSION 3.21)
project(cpp_zookeeper_client_sample)

set(CMAKE_CXX_STANDARD 14)
set(EXECUTABLE_OUTPUT_PATH build)
#Already downloaded source code
set(ZOOKEEPER_SRC_PATH /Users/me/repo/zookeeper)
#
#add_custom_target(
#        zookeeper_prepare
#        COMMAND ant clean jar
#        COMMAND ant compile_jute
#        WORKING_DIRECTORY ${ZOOKEEPER_SRC_PATH}
#        COMMENT "Execute 'zookeeper_prepare'"
#)
#
#include(ExternalProject)
#ExternalProject_Add(
#        zookeeper
#        PREFIX ${EXECUTABLE_OUTPUT_PATH}/zookeeper
#        SOURCE_DIR ${ZOOKEEPER_SRC_PATH}/zookeeper-client/zookeeper-client-c
#        CMAKE_ARGS -DCMAKE_CXX_FLAGS="${CMAKE_CXX_FLAGS} -target arm64-apple-darwin21.1.0"
#        DEPENDS zookeeper_prepare
#        INSTALL_COMMAND :
#)
#
#ExternalProject_Get_Property(zookeeper SOURCE_DIR)
#include_directories(${SOURCE_DIR}/include ${SOURCE_DIR}/generated ${SOURCE_DIR}/src)

include_directories(include)
add_executable(main main.cpp)
#ExternalProject_Get_Property(zookeeper BINARY_DIR)
add_library(libzookeeper STATIC IMPORTED)
set_target_properties(
        libzookeeper
        PROPERTIES IMPORTED_LOCATION
        /tmp/libzookeeper.a)

target_link_libraries(main libzookeeper)
