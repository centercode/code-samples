#!/bin/bash
#
# this script can move current work directory files'
# all duplicated files to ./duplication directory.
# 
# USAGE:
#   cd to target directory
#   run this script
#   you'll find duplication in ./duplication

md5sum *|sort >md5info; #calculate all files' md5 value and sort them
awk 'BEGIN{ preMd5 = ""; preName = ""; }   { curMd5 = $1; curName = $2; if (curMd5 == preMd5) print  curName; preMd5 = curMd5; preName = curName ; }' md5info >md5info.result; #if a record in md5info already exists, then record it to md5info.result 
rm md5info;
mkdir duplication; #duplication files move to here, thus no duplication files in current work directory

for i in `cat md5info.result`
do 
    mv $i ./duplication/;
done
rm md5info.result;
