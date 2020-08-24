tr -cs 'a-zA-Z' '[\n*]' < $1 |
awk '
    { count[$1]++ }
END {
    for (item in count)
        printf "%-15s%3s\n",item, count[item] }' |
sort -k 2nr -k 1f
