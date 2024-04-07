-- 当周的周一
select date_add(dt, 1 - dayofweek_iso(dt)) from (values(date'2024-04-07')) as t (dt)

-- 当周的周日
select date_add(dt, 7 - dayofweek_iso(dt)) from (values(date'2024-04-07')) as t (dt)
-- 前第3周的周一
select weeks_add(date_add(dt, 1 - dayofweek_iso(dt)) , -3) from (values(date'2024-04-07')) as t (dt)

-- 当月的1号
select date_add(dt, 1 - dayofmonth(dt)) from (values(date'2024-04-07')) as t (dt)
-- 前第3月的1号
select months_add(date_add(dt, 1 - dayofmonth(dt)) , -3) from (values(date'2024-04-07')) as t (dt)