if [ $# != 2 ]
    then 
        echo "Usage: manuf.shfield file"
        exit 1
fi 
awk -v "field=$1" < $2 '
    { count[$field]++ }
END {
    for (item in count)
        printf "%-20s %-20s\n",\
        item, count[item] }' |
sort

