with t2 as ((
    with t1 as (select 1)
    select * from t1
))
select * from t2;