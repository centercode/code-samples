if (test $# = 0) then
    echo "you must supply a filename."
    exit 1
fi 

(date; cat $1) |
awk '
NR == 1 {
    print "Report for ", $1, $2, $3 "," $6
}
NR > 1 {
    print $5 "\t" $1
}
'
