    awk [options] [program] [file-list]
    awk [options] -f [program-file] [file-list]
    
    options:
    -F fs
    -v var=value

# 模式
~和!~ 用/把正则表达式括起来，就可以将其视为模式。“～”运算符用于测试某个字段或者变量是否匹配正则表达式。!~ 用于测试不匹配。

BEGIN和END 分别执行在awk开始处理输入信息之前和处理输入信息之后的命令。
,(逗号) 是范围运算符。如果用逗号将两种模式分开，awk就选取从匹配第一种模式的第1行开始的一系列文本行。awk选取的最后一行是随后匹配第2种模式的下一行文本。如果没有匹配第2种模式的文本行，awk就选取直到输入末尾的所有文本行。在awk找到第2种模式后，它将再次查找第1种模式以再次开启这个过程。
# 动作
除非用逗号将print命令中的各项分开，否则awk将它们连接起来。逗号使得awk用输出字段分隔符将各项分开。
# 注释
使用#（井号）
# 变量
| 变量 |含义|
|------|----|
| $0 |当前记录|
| $1~$n | 当前记录中的字段|
| FILENAME | 当前输入文件的名称(null表示标准输入)|
| NF(Number of Field) | 当前记录的字段数目|
| NR(Number of Record) | 当前记录的记录编号|
| FS(Field Seperator) | 输入字段分隔符（默认为空格或制表符）|
| RS(Record Seperator) | 输入记录分隔符（默认为换行符）|
| OFS(Out Field Seperator) | 输出字段分隔符（默认为空格）|
| ORS(Out Record Seperator) | 输出记录分隔符（默认为换行符）|
还可以在命令行上使用--assing(-v)选项初始化变量。如果某个变量的值在awk的两次运行之间发生改变，这个功能就非常有用。
# 函数
| 函数 | 含义 |
| ----- | ----- |
|length(str)| 返回str中的字符个数，不带参数则返回当前记录中的字符个数|
| int(num)|返回 num 的整数部分|
| index(str1, str2)| 返回 str2 在 str1 中的索引，如果str2不存在就返回0|
| split(str, arr, del)|用del作分隔符，将str的元素放到arr[1]...arr[n]中，返回数组中的元素个数|
| sprintf(fmt, args)|根据vmt格式化args并返回格式化后的字符串|
| substr(str,pos,len)|返回str中从pos开始、长度为len个字符的子字符串|
| tolower(str)|返回str的全部小写副本|
| toupper(str) 返回str的全部大写副本|
# 算术运算符
** 计算以运算符前面的表达式为底、以后面的表达式为指数的幂
# 关联数组
这些数组使用字符串作为索引，可给关联数组中的某个元素赋值：

    array[string] = value
可将for结构用于关联数组：

    for(elem in array) action








