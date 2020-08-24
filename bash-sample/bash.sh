#!/bin/bash

set -o nounset
set -o errexit

# 向文件中写入多行文本
file=/tmp/tmpfile
cat >$file<<EOF
line1
line2
EOF

# 打印到标准输出
>&2 echo "foo"
echo "foo" 1>&2

# bash启动的时候会打开3个文件描述符，0，1，2也称为标准输入，标准输出，标准错误.
# bash从终端读入标准输入，通过标准输出和标准错误输出打印到终端.
# 简单重定向
# 输出重定向"n> file"
# 输入重定向"n< file"
# 管道“|”把左边命令的标准输出连接到右边命令的标准输入。
# "2>&1"是创建了文件描述符的副本而非别名，

# In Bash, you can also redirect to a subshell using process substitution:
command > >(stdlog pipe)  2> >(stderr pipe)
