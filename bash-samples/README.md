文件头

```bash
#!/bin/bash
```



### 常量

| 变量 | 含义 |
| ---- | ---- |
| $0   |      |
| $n   |      |
| $#   |      |
| $*   |      |
| $@   |      |
| $?   |      |
| $$   |      |

命令行参数

$* 和 $@ 的区别

```
$* 和 $@ 都表示传递给函数或脚本的所有参数，不被双引号(" ")包含时，都以"$1" "$2" … "$n" 的形式输出所有参数。

但是当它们被双引号(" ")包含时，"$*" 会将所有的参数作为一个整体，以"$1 $2 … $n"的形式输出所有参数；"$@" 会将各个参数分开，以"$1" "$2" … "$n" 的形式输出所有参数。
```

退出状态

### Shell替换：Shell变量替换，命令替换，转义字符

### 变量

#### 变量类型

运行shell时，会同时存在三种变量：

1. 局部变量：局部变量在脚本或命令中定义，仅在当前shell实例中有效，其他shell启动的程序不能访问局部变量。
2. 环境变量：所有的程序，包括shell启动的程序，都能访问环境变量，有些程序需要环境变量来保证其正常运行。必要的时候shell脚本也可以定义环境变量。
3. shell变量：shell变量是由shell程序设置的特殊变量。shell变量中有一部分是环境变量，有一部分是局部变量，这些变量保证了shell的正常运行。

#### 定义变量

Shell 变量的命名规范和大部分编程语言都一样：

- 变量名由数字、字母、下划线组成
- 必须以字母或者下划线开头
- 不能使用 Shell 里的关键字（通过 help 命令可以查看保留关键字）

Shell 支持以下定义变量的方式：

```bash
variable=value             # 赋值号的周围不能有空格
variable='value'           # 如果 value 包含了空白符，那么就必须使用引号包围起来。
variable="value"
readonly variable="value"  # 使用 readonly 命令可以将变量定义为只读变量，只读变量的值不能被改变
```

Shell 也支持将命令的执行结果赋值给变量，常见的有以下两种方式：

```bash
variable=`command`
variable=$(command)
```

#### 使用变量

使用一个定义过的变量，只要在变量名前面加美元符号$即可，如：

```bash
variable="value"
echo $variable
echo ${variable}       # 加花括号是为了帮助解释器识别变量的边界，推荐给所有变量加上花括号{}
```

#### 删除变量

使用 unset 命令可以删除变量，unset 命令不能删除只读变量，变量被删除后不能再次使用：

```bash
unset variable_name
```

### 字符串

单引号字符串的限制：

- 单引号里的任何字符都会原样输出，单引号字符串中的变量是无效的；
- 单引号字串中不能出现单独一个的单引号（对单引号使用转义符后也不行），但可成对出现，作为字符串拼接使用。

双引号的优点：

- 双引号里可以有变量
- 双引号里可以出现转义字符

### 数组

#### 定义数组

bash只支持一维数组，但参数个数没有限制。定义数组有以下方式：

1. 使用`declare -a`定义数组：

    ```bash
    declare -a array
    ```

4. 使用[]操作符：

   ```bash
   array[0]='foo'
   array[1]='bar'
   ```

5. 使用()直接赋值：

    ```bash
    array=('foo' 'bar')
    # 或
    array=([0]='bar' [1]='bar')
    ```

6. 连接两个数组：

    ```bash
    array3=(${array[@]} ${array2[@]})
    echo ${#array3[@]}
    ```

7. 从文件中读取数组：

    ```bash
    # 将每一行读取为数组的一个元素
    array=(`cat 'array.txt'`) 
    ```

#### 读取数组

使用[]操作符和基于0的下标来取值：

```bash
array=('foo' 'bar')
${array[n]}                     # 数组第n个元素
${array[*]} 或${array[@]}       # 数组所有成员，使用“@”这个特殊的下标，可以将数组扩展成列表
${!array[*]} 或${!array[@]}     # 数组所有下标
${#array[*]} 或${#array[@]}     # 数组元素个数，没有定义的数组下标不会占用数组中元素的个数
${#array[0]}                    # 数组第一个成员的长度
```

#### 遍历数组元素

```bash
# 使用for in循环读取数组：
for item in ${array[@]};do
 echo $item
done
# 使用for循环读取数组：
len=${#adobe[@]}
for ((i=0;i<$len;i++));do
 echo ${adobe[$i]}
do
```

#### 修改数组

替换数组元素：

```bash
array=('foo' 'bar')
# 使用模式操作符替换数组中的元素
echo ${array[@]/foo/foo2}
```

删除数组元素：

```bash
array=('foo1' 'bar2' 'foo3' 'bar4' 'foo5')
# 使用命令替换并重新赋值的方式删除数组元素
array=(${array[@]:0:2} ${array	[@]:3})
# 使用模式操作符删除数组元素
array=(${array[@]/foo3/})
```

### 参考

- [Bash中数组的操作教程](https://www.jb51.net/article/101241.htm)
- [bash数组定义](https://blog.csdn.net/ilovemilk/article/details/4959747)
- [30分钟玩转Shell脚本编程](http://c.biancheng.net/cpp/shell/)



数据类型
流程控制
if
switch
while
for

函数

操作符

模式操作符

read和数组