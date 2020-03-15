BEGIN{
    srand();
    count = 0;
    sum = 0;
}
{
    for(i = 0; i < rand()*99 + 1 / 5 ;i++){
        printf "."
    }
    print ""

    sum += $1;
    count ++;
}
END{
    print sum / count
}
