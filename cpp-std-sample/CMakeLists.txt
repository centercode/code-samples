cmake_minimum_required(VERSION 3.0)
project(cpp_std_sample)

set(CMAKE_CXX_STANDARD 14)

add_executable(helloworld helloworld.cpp)
add_executable(string string.cc)
add_executable(bit bit.cc)
