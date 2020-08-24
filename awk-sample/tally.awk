awk 'BEGIN{
    ORS=""
}
NR == 1 { #first record only set fields to number of fields in the record(NF)
    nfields = NF 
}
{
    if ($0 ~ /[^0-9. \t]/) #check each record to see if it contains any characters that are not numbers
    {
        print "\nRecord " NR "skipped:\n\t" #periods,spaces,or TABS
        print $0 "\n"
        next #skip bad records
    }
    else {
        for (count = 1; count <= nfields; count++) #for good records loop through fields
        {
            printf "%10.2f", $count > "tally.out"
            sum[count] += $count
            gtotal += $count
        }
    print "\n" > "tally.out"
    }
}

END {
    for (count = 1; count <= nfields; count++){
        print "   -----" > "tally.out"
    }
    print "\n" > "tally.out"
    for (count = 1; count <= nfields; count++){
        printf "%10.2f", sum[count] > "tally.out"
    }
print "\n\n    Grand Total " gtotal "\n" > "tally.out"
}
' < numbers
