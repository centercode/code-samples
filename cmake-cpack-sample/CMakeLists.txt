cmake_minimum_required(VERSION 3.21)
project(cmake_cpack_sample)
set(CMAKE_CXX_STANDARD 11)

include_directories(greet PUBLIC src/include)
add_library(greet SHARED "src/lib/greet.c")
add_executable(main "src/cli/main.c")
target_link_libraries(main greet)

install(
        TARGETS greet
        COMPONENT libraries
        DESTINATION "lib"
)
install(
        TARGETS main
        COMPONENT applications
        DESTINATION "bin"
)
install(
        FILES "src/include/greet.h"
        COMPONENT headers
        DESTINATION "include"
)

# make uninstall
add_custom_target("uninstall" COMMENT "Uninstall installed files")
add_custom_command(
        TARGET "uninstall"
        POST_BUILD
        COMMENT "Uninstall files with install_manifest.txt"
        COMMAND xargs rm -vf < install_manifest.txt
)

#include(deb)
#include(CPack)