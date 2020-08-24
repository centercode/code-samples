#!/bin/bash
# Bash最佳实践
#
# 什么时候不应该使用bash脚本:
#   你的脚本太长，多达几百行
#   你需要比数组更复杂的数据结构
#   出现了复杂的转义问题
#   有太多的字符串操作
#   不太需要调用其它程序和跟其它程序管道交互
#   担心性能


# 可以用bash -n对脚本进行语法检查:
#   bash -n myscript.sh
# 可以用bash -v跟踪脚本里的每个命令的执行:
#   bash -v myscript.sh
# 可以用bash -x跟踪脚本里的每个命令的执行，并附加扩充信息:
#   bash -x myscript.sh


# 可以在脚本里永久指定输出调试信息:
set -o verbose
set -o xtrace
#
#
# 当遇到不存在的变量时终止执行
set -o nounset 
# 当出错时终止执行
set -o errexit 


# 封装提高复用
log() {
    local prefix="[$(date +%Y/%m/%d\ %H:%M:%S)]: "
    echo "${prefix} $@" >&2
}
log "INFO" "a info level message"


#封装提高可读性
ExtractBashComments() {
    egrep "^#"
}
cat example.sh | ExtractBashComments |wc -l
comments=$(ExtractBashComments < rules.sh)


# 使用readonly和local修饰变量提高安全
#   DEFAULT_VAL可以被环境变量中的值覆盖
readonly DEFAULT_VAL=${DEFAULT_VAL:-234}
myfunc() {
    local var=${DEFAULT_VAL}
    echo var:$var
}


# 用$()代替反单引号"`"
#   $()能够支持内嵌
#   $()不用转义
#   $()不容易与单引号混淆
echo "A-`echo B-\`echo C-\\\`echo D\\\`\``"
echo "A-$(echo B-$(echo C-$(echo D)))"


# 用[[]]替代[]
#   避免转义问题
#   支持新功能:
#   || ：逻辑or
#   && ：逻辑and
#   < ：字符串比较（不需要转义）
#   == ：通配符(globbing)字符串比较
#   =~ ：正则表达式(regular expression, RegEx)字符串比较
name=b
[ "${name}" \> "a" -o ${name} \< "m" ]
[[ "${name}" > "a" && "${name}" < "m"  ]]


:<< !
zhushi
!

# 正则表达式/Globbing
#   注意正则表达式和globbing表达式都不能用引号包裹
t="abc123"
[[ "$t" == abc* ]]         # true (globbing比较)
[[ "$t" =~ [abc]+[123]+ ]] # true (正则表达式比较)
[[ "$t" == "abc*" ]]       # false (字面比较)
[[ "$t" =~ "abc*" ]]       # false (字面比较)
#   如果表达式里有空格，可以把它存储到一个变量里
r="a b+"
[[ "a bbb" =~ $r ]]        # true
#   按Globbing方式的字符串比较也可以用到case语句中：
case $t in
abc*)  echo "matched abc*" ;;
esac


# 使用<()避免使用临时文件:
#   下载并比较两个网页
diff <(wget -O - https://baidu.com) <(wget -O - https://sogou.com)


# 使用"here documents"在标准输入上输入多行字符串：
#   任何字词都可以当作分界符
command  << MARKER
...
${var}
$(cmd)
...
MARKER
# 如果文本里没有内嵌变量替换操作，你可以把第一个MARKER用单引号包起来：
command << 'MARKER'
...
no substitution is happening here.
$ (dollar sign) is passed through verbatim.
...
MARKER

# 内置变量
#   变量  说明
#   $0    脚本名称
#   $n    传给脚本/函数的第n个参数(第10+个参数要使用花括号：${10})
#   $$    脚本的PID
#   $!    上一个被执行的命令的PID(后台运行的进程)
#   $?    上一个命令的退出状态(管道命令使用${PIPESTATUS})
#   $#    传递给脚本/函数的参数个数
#   $@    传递给脚本/函数的所有参数(识别每个参数)
#   $*    传递给脚本/函数的所有参数(把所有参数当成一个字符串)
