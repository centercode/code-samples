# bin/bash

## 数组赋值

### 1、从“标准输入”读入n次字符串，每次输入的字符串保存在数组array里
i=0
n=5
while [ "$i" -lt $n ] ; do
  echo "Please input strings ... `expr $i + 1`"
  read array[$i]
  echo "${array[$i]}"
  i=`expr $i + 1`
done

### 2、将字符串里的字母逐个放入数组，并输出到“标准输出”
chars='abcdefghijklmnopqrstuvwxyz'
for (( i=0; i<26; i++ )) ; do
    array[$i]=${chars:$i:1}
    echo ${array[$i]}
done

### 3、将文件中内容给数组赋值：(碰到第一个回车符之前的内容)
read -a SHELLS < /tmp/tmp.file

### 查看数组赋值情况：
set | grep "SHELLS"

## 参考
## [BASH数组用法小结及循环用法](https://blog.csdn.net/samxx8/article/details/8025548)
