#!/bin/bash
# 按时间分段统计日志中某关键词出现的次数,类似wordCount
# todo: datetime format

set -o nounset
set -o errexit

RANGE=300 #5minues

# convert datetime to timestamp in second
# and calculate time range
map() {
    while read line; do
        ts=`date -d "${line}" +%s`
        time_start=`expr $ts / $RANGE`
        echo $time_start
    done
}

reduce() {
    cat - |awk '
    BEGIN {
        range="'"$RANGE"'"
        last=0;
        n=0;
    }
    {
        if(last != $0) {
            if(last != 0){
                t=strftime("%Y-%m-%d %H:%M", last*range);
                print t,n;
            }
            last=$0;
            n=1;
        } else {
            n++;
        }
        last=$0;
    }
    END {
        t=strftime("%Y-%m-%d %H:%M", last*range);
        print t,n;
    }'
}

get_time() {
    while read line; do
        echo "${line:0:19}"
    done
}

# main method
cat - |get_time |map |sort |reduce
