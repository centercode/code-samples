cmake_minimum_required(VERSION 3.0)
project(cpp_std_sample)

set(CMAKE_CXX_STANDARD 17)

add_executable(helloworld helloworld.cpp)
add_executable(string string.cc)
add_executable(bit bit.cc)
add_executable(net.unix_socket.server net/unix_socket/server.c)
add_executable(net.unix_socket.client net/unix_socket/client.c)
add_executable(sizeof sizeof.cc)
add_executable(data_type data_type.cc)