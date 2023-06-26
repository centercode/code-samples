cmake_minimum_required(VERSION 3.21)
project(cpp_zookeeper_client_sample)

set(CMAKE_CXX_STANDARD 14)
set(EXECUTABLE_OUTPUT_PATH build)
#Already downloaded source code
set(ZOOKEEPER_SRC_PATH /tmp/zookeeper)

add_custom_target(
        zookeeper_prepare
        COMMAND ant clean jar
        COMMAND ant compile_jute
        WORKING_DIRECTORY ${ZOOKEEPER_SRC_PATH}
        COMMENT "Execute 'zookeeper_prepare'"
)

include(ExternalProject)
ExternalProject_Add(
        zookeeper
        PREFIX ${EXECUTABLE_OUTPUT_PATH}/zookeeper
        SOURCE_DIR ${ZOOKEEPER_SRC_PATH}/zookeeper-client/zookeeper-client-c
        DEPENDS zookeeper_prepare
        INSTALL_COMMAND :
)

ExternalProject_Get_Property(zookeeper SOURCE_DIR)
include_directories(${SOURCE_DIR}/include ${SOURCE_DIR}/generated)

add_executable(cpp_zookeeper_client_sample main.cpp)
add_dependencies(cpp_zookeeper_client_sample zookeeper)
