cmake_minimum_required(VERSION 3.21)
project(zookeeper_client_sample)

set(CMAKE_CXX_STANDARD 14)
set(EXECUTABLE_OUTPUT_PATH build)

include(ExternalProject)
ExternalProject_Add(
        zookeeper
        GIT_REPOSITORY "https://github.com/apache/zookeeper.git"
        GIT_TAG release-3.8.2
        CONFIGURE_COMMAND mvn generate-sources
        BUILD_COMMAND cmake zookeeper-client/zookeeper-client-c && make zookeeper
        INSTALL_COMMAND :
        BUILD_IN_SOURCE 1
)
add_executable(main main.cpp)

ExternalProject_Get_Property(zookeeper SOURCE_DIR)
include_directories(${SOURCE_DIR}/zookeeper-client/zookeeper-client-c/include ${SOURCE_DIR}/zookeeper-client/zookeeper-client-c/generated)

link_directories(${SOURCE_DIR})
