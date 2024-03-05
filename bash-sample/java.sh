#!/bin/bash
#
### Jinfo command ###
# Reference:
# https://docs.oracle.com/javase/8/docs/technotes/tools/unix/jinfo.html
# https://docs.oracle.com/javase/8/docs/technotes/guides/troubleshoot/clopts001.html
# https://www.oracle.com/java/technologies/javase/vmoptions-jsp.html
# https://docs.oracle.com/javase/8/docs/technotes/tools/unix/java.html
# Usage:
# jinfo [option] <pid>
# <option>:
#    -flag <name>         to print the value of the named VM flag
#    -flag [+|-]<name>    to enable or disable the named VM flag
#    -flag <name>=<value> to set the named VM flag to the given value
#    -flags               to print VM flags
#    -sysprops            to print Java system properties
#    <no option>          to print both VM flags and system properties
#
# 注明了被标记为manageable的参数可以被动态修改:
#  java -XX:+PrintFlagsInitial | grep manageable
#
# examples:
jinfo -flag HeapDumpOnOutOfMemoryError <pid>
jinfo -flag +HeapDumpOnOutOfMemoryError <pid>
jinfo -flag -HeapDumpOnOutOfMemoryError <pid>
jinfo -flag HeapDumpPath=/tmp <pid>
jinfo -flags <pid>
jinfo -sysprops <pid>
jinfo <pid>