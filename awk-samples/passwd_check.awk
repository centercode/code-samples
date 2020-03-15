awk < ./passwd ' BEGIN {
    uid[void] = "" #tell awk that uid is an array
}
{
    dup = 0 #initialize duplicate flag
    split($0, field, ":") #split into fields delimited by ":"
    if (field[2] == "") 
    {
        if (field[5] == "")
        {
            print  field[1] "has no password."
        }
        else 
        {
            print field[1] " (" field[5]") has no password"
        }
    }
    for (name in uid){ #loop through uid array
        if (uid[name] == field[3]){ #check for second use of UID
            print field[1] "has the same UID as" name ":UID = " uid[name]
            dup = 1 #set duplicate flag
        }
    }
    if (!dup) #assign UID and login name to uid array
    {
        uid[field[1]] = field[3]
    }
}'
