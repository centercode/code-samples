-- 当周的周一
select date_add(dt, 1 - dayofweek_iso(dt)) from (values(date'2024-04-07')) as t (dt);

-- 当周的周日
select date_add(dt, 7 - dayofweek_iso(dt)) from (values(date'2024-04-07')) as t (dt);
-- 前第3周的周一
select weeks_add(date_add(dt, 1 - dayofweek_iso(dt)) , -3) from (values(date'2024-04-07')) as t (dt);

-- 当月的1号
select date_add(dt, 1 - dayofmonth(dt)) from (values(date'2024-04-07')) as t (dt);
-- 前第3月的1号
select months_add(date_add(dt, 1 - dayofmonth(dt)) , -3) from (values(date'2024-04-07')) as t (dt);

/* date_format()函数的日期格式 */
--年份：%Y, %y
select '0000-01-01' as dt, date_format('0000-01-01', '%Y, %y') as '%Y, %y'
union all
select '0001-01-01' as dt, date_format('0001-01-01', '%Y, %y') as '%Y, %y'
union all
select '0009-01-01' as dt, date_format('0009-01-01', '%Y, %y') as '%Y, %y'
union all
select '0010-01-01' as dt, date_format('0010-01-01', '%Y, %y') as '%Y, %y'
union all
select '1969-01-01' as dt, date_format('1969-01-01', '%Y, %y') as '%Y, %y'
union all
select '1970-01-01' as dt, date_format('1970-01-01', '%Y, %y') as '%Y, %y'
union all
select '2000-01-01' as dt, date_format('2000-01-01', '%Y, %y') as '%Y, %y'
union all
select '9999-01-01' as dt, date_format('9999-01-01', '%Y, %y') as '%Y, %y'
order by 1
--月份: %m, %c, %b, %M
select '2024-01-01' as dt, date_format('2024-01-01', '%m, %c, %b, %M') as '%m, %c, %b, %M'
union all
select '2024-02-01' as dt, date_format('2024-02-01', '%m, %c, %b, %M') as '%m, %c, %b, %M'
union all
select '2024-03-01' as dt, date_format('2024-03-01', '%m, %c, %b, %M') as '%m, %c, %b, %M'
union all
select '2024-04-01' as dt, date_format('2024-04-01', '%m, %c, %b, %M') as '%m, %c, %b, %M'
union all
select '2024-05-01' as dt, date_format('2024-05-01', '%m, %c, %b, %M') as '%m, %c, %b, %M'
union all
select '2024-06-01' as dt, date_format('2024-06-01', '%m, %c, %b, %M') as '%m, %c, %b, %M'
union all
select '2024-07-01' as dt, date_format('2024-07-01', '%m, %c, %b, %M') as '%m, %c, %b, %M'
union all
select '2024-08-01' as dt, date_format('2024-08-01', '%m, %c, %b, %M') as '%m, %c, %b, %M'
union all
select '2024-09-01' as dt, date_format('2024-09-01', '%m, %c, %b, %M') as '%m, %c, %b, %M'
union all
select '2024-10-01' as dt, date_format('2024-10-01', '%m, %c, %b, %M') as '%m, %c, %b, %M'
union all
select '2024-11-01' as dt, date_format('2024-11-01', '%m, %c, %b, %M') as '%m, %c, %b, %M'
union all
select '2024-12-01' as dt, date_format('2024-12-01', '%m, %c, %b, %M') as '%m, %c, %b, %M'
order by 1;
-- 星期: %w, %a, %W
select '2024-01-01' as dt, date_format('2024-01-01', '%w, %a, %W') as '%w, %a, %W'
union all
select '2024-01-02' as dt, date_format('2024-01-02', '%w, %a, %W') as '%w, %a, %W'
union all
select '2024-01-03' as dt, date_format('2024-01-03', '%w, %a, %W') as '%w, %a, %W'
union all
select '2024-01-04' as dt, date_format('2024-01-04', '%w, %a, %W') as '%w, %a, %W'
union all
select '2024-01-05' as dt, date_format('2024-01-05', '%w, %a, %W') as '%w, %a, %W'
union all
select '2024-01-06' as dt, date_format('2024-01-06', '%w, %a, %W') as '%w, %a, %W'
union all
select '2024-01-07' as dt, date_format('2024-01-07', '%w, %a, %W') as '%w, %a, %W'
order by 1;
-- 年中天数: %j
select '2023-01-01' as dt, date_format('2023-01-01', '%j') as '%j'
union all
select '2023-12-31' as dt, date_format('2023-12-31', '%j') as '%j'
union all
select '2024-01-01' as dt, date_format('2024-01-01', '%j') as '%j'
union all
select '2024-12-31' as dt, date_format('2024-12-31', '%j') as '%j'
order by 1;
-- 月中天数: %D, %d, %e
select '2024-01-01' as dt, date_format('2024-01-01', '%D, %d, %e') as '%D, %d, %e'
union all
select '2024-01-02' as dt, date_format('2024-01-02', '%D, %d, %e') as '%D, %d, %e'
union all
select '2024-01-03' as dt, date_format('2024-01-03', '%D, %d, %e') as '%D, %d, %e'
union all
select '2024-01-04' as dt, date_format('2024-01-04', '%D, %d, %e') as '%D, %d, %e'
union all
select '2024-01-05' as dt, date_format('2024-01-05', '%D, %d, %e') as '%D, %d, %e'
union all
select '2024-01-06' as dt, date_format('2024-01-06', '%D, %d, %e') as '%D, %d, %e'
union all
select '2024-01-07' as dt, date_format('2024-01-07', '%D, %d, %e') as '%D, %d, %e'
union all
select '2024-01-08' as dt, date_format('2024-01-08', '%D, %d, %e') as '%D, %d, %e'
union all
select '2024-01-09' as dt, date_format('2024-01-09', '%D, %d, %e') as '%D, %d, %e'
union all
select '2024-01-10' as dt, date_format('2024-01-10', '%D, %d, %e') as '%D, %d, %e'
union all
select '2024-01-11' as dt, date_format('2024-01-11', '%D, %d, %e') as '%D, %d, %e'
union all
select '2024-01-12' as dt, date_format('2024-01-12', '%D, %d, %e') as '%D, %d, %e'
union all
select '2024-01-13' as dt, date_format('2024-01-13', '%D, %d, %e') as '%D, %d, %e'
union all
select '2024-01-14' as dt, date_format('2024-01-14', '%D, %d, %e') as '%D, %d, %e'
union all
select '2024-01-15' as dt, date_format('2024-01-15', '%D, %d, %e') as '%D, %d, %e'
union all
select '2024-01-16' as dt, date_format('2024-01-16', '%D, %d, %e') as '%D, %d, %e'
union all
select '2024-01-17' as dt, date_format('2024-01-17', '%D, %d, %e') as '%D, %d, %e'
union all
select '2024-01-18' as dt, date_format('2024-01-18', '%D, %d, %e') as '%D, %d, %e'
union all
select '2024-01-19' as dt, date_format('2024-01-19', '%D, %d, %e') as '%D, %d, %e'
union all
select '2024-01-20' as dt, date_format('2024-01-20', '%D, %d, %e') as '%D, %d, %e'
union all
select '2024-01-21' as dt, date_format('2024-01-21', '%D, %d, %e') as '%D, %d, %e'
union all
select '2024-01-22' as dt, date_format('2024-01-22', '%D, %d, %e') as '%D, %d, %e'
union all
select '2024-01-23' as dt, date_format('2024-01-23', '%D, %d, %e') as '%D, %d, %e'
union all
select '2024-01-24' as dt, date_format('2024-01-24', '%D, %d, %e') as '%D, %d, %e'
union all
select '2024-01-25' as dt, date_format('2024-01-25', '%D, %d, %e') as '%D, %d, %e'
union all
select '2024-01-26' as dt, date_format('2024-01-26', '%D, %d, %e') as '%D, %d, %e'
union all
select '2024-01-27' as dt, date_format('2024-01-27', '%D, %d, %e') as '%D, %d, %e'
union all
select '2024-01-28' as dt, date_format('2024-01-28', '%D, %d, %e') as '%D, %d, %e'
union all
select '2024-01-29' as dt, date_format('2024-01-29', '%D, %d, %e') as '%D, %d, %e'
union all
select '2024-01-30' as dt, date_format('2024-01-30', '%D, %d, %e') as '%D, %d, %e'
union all
select '2024-01-31' as dt, date_format('2024-01-31', '%D, %d, %e') as '%D, %d, %e'
order by 1;
-- 小时：%H, %h|%I, %p
select '2024-01-01 00:00:00' as dt, date_format('2024-01-01 00:00:00', '%H, %h %p, %I %p') as '%H, %h %p, %I %p'
union all
select '2024-01-01 11:59:59' as dt, date_format('2024-01-01 11:59:59', '%H, %h %p, %I %p') as '%H, %h %p, %I %p'
union all
select '2024-01-01 12:00:00' as dt, date_format('2024-01-01 12:00:00', '%H, %h %p, %I %p') as '%H, %h %p, %I %p'
union all
select '2024-01-01 23:59:59' as dt, date_format('2024-01-01 23:59:59', '%H, %h %p, %I %p') as '%H, %h %p, %I %p'
order by 1;
--分秒：%T, %r, %S|%s, %f
select '2024-01-01 00:00:00' as dt, date_format('2024-01-01 00:00:00', '%T, %H:%i:%s, %H:%i:%S.%f, %r') as '%T, %H:%i:%s, %H:%i:%S.%f, %r'
union all
select '2024-01-01 11:59:59' as dt, date_format('2024-01-01 11:59:59', '%T, %H:%i:%s, %H:%i:%S.%f, %r') as '%T, %H:%i:%s, %H:%i:%S.%f, %r'
union all
select '2024-01-01 12:00:00' as dt, date_format('2024-01-01 12:00:00', '%T, %H:%i:%s, %H:%i:%S.%f, %r') as '%T, %H:%i:%s, %H:%i:%S.%f, %r'
union all
select '2024-01-01 23:59:59' as dt, date_format('2024-01-01 23:59:59', '%T, %H:%i:%s, %H:%i:%S.%f, %r') as '%T, %H:%i:%s, %H:%i:%S.%f, %r'
order by 1;
--基于年份的周：%x, %v, %X, %V
-- 周一为周起始, 第一周至少4天，跨年
select '2009-01-01' as dt, date_format('2009-01-01', '%x-W%v')
union all
select '2009-01-04' as dt, date_format('2009-01-04', '%x-W%v')
union all
select '2009-01-05' as dt, date_format('2009-01-05', '%x-W%v')
union all
select '2010-01-01' as dt, date_format('2010-01-01', '%x-W%v')
order by 1;
-- 周日为周起始，第一周至少7天，跨年
select '2005-01-01' as dt, date_format('2005-01-01', '%X-W%V')
union all
select '2006-01-01' as dt, date_format('2006-01-01', '%X-W%V')
union all
select '2006-01-07' as dt, date_format('2006-01-07', '%X-W%V')
union all
select '2006-01-08' as dt, date_format('2006-01-08', '%X-W%V')
order by 1;
--平年最大周数
select '2001-12-30' as dt, date_format('2001-12-30', '%x-W%v')
union all
select '2002-12-29' as dt, date_format('2002-12-29', '%x-W%v')
union all
select '2003-12-28' as dt, date_format('2003-12-28', '%x-W%v')
union all
select '2009-12-31' as dt, date_format('2009-12-31', '%x-W%v')
union all
select '2010-12-31' as dt, date_format('2010-12-31', '%x-W%v')
union all
select '2011-12-31' as dt, date_format('2011-12-31', '%x-W%v')
union all
select '2017-12-31' as dt, date_format('2017-12-31', '%x-W%v')
order by 1;
-- 闰年最大周数
select '1940-12-29' as dt, date_format('1940-12-29', '%x-W%v')
union all
select '1952-12-31' as dt, date_format('1952-12-28', '%x-W%v')
union all
select '1964-12-31' as dt, date_format('1964-12-31', '%x-W%v')
union all
select '1976-12-31' as dt, date_format('1976-12-31', '%x-W%v')
union all
select '1988-12-31' as dt, date_format('1988-12-31', '%x-W%v')
union all
select '2000-12-31' as dt, date_format('2000-12-31', '%x-W%v')
union all
select '2012-12-31' as dt, date_format('2012-12-30', '%x-W%v')
order by 1;