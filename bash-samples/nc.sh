#!/bin/sh
# 辅助使用nc命令传输文件
# Usage: nc.sh path_to_file

set -o nounset
set -o errexit

if [ ! -e $1 -o -d $1 ]; then
	echo '文件不存在';
    exit 1
fi

# 网卡名称
interface=wlp58s0
ip=$(ifconfig $interface| grep inet | grep broadcast |awk '{print $2}')
port=9998
file_path=$1

ls -lh $file_path &&\
echo "md5:" `md5sum $file_path|cut  -d" " -f1` &&\
echo &&\
echo "nc $ip $port > ${file_path##*/}" &&\
nc -l $port < $file_path
