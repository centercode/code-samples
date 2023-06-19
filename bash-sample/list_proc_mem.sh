#!/bin/bash
#
# List processes memory.
#


# show java process rss memory.
# output format: <pid> <rss_in_KB> <cmd>
show_java_mem0() {
  ps -eo pid,rss,cmd --sort=-rss| awk '$3 ~ /java/'
}

# show java process rss memory.
# output format: <pid> <rss_in_KB> <main_class>
show_java_mem1() {
  for id in `jps -q|grep -v sun.tools.jps.Jps`; do
    proc=$(jps -l |grep $id |awk '{print $2}')
    [ -f /proc/$id/status ] && echo "${id} $(cat /proc/$id/status| grep VmRSS| awk '{ print $2 }')KB ${proc}"
  done | sort -rnk2
}

# TODO
# show java process max heap size
show_java_xmx() {
  jps -l \
    |grep -v sun.tools.jps.Jps \
    | awk '{ printf "%s %s %s",$1, $2, system("jinfo -flag MaxHeapSize "$1 " 2> /dev/null || echo 01");}'| sort -rnk1
}