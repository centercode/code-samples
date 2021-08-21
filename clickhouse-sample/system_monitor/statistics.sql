-- https://blog.csdn.net/Jarry_cm/article/details/106134994
-- https://clickhouse.tech/docs/en/operations/system-tables/parts/
-- 统计数据库数据量大小
select
    sum(rows) as row,
    formatReadableSize(sum(data_uncompressed_bytes)) as uncompressed_data,
    formatReadableSize(sum(data_compressed_bytes)) as compressed_data,
    round(sum(data_compressed_bytes) / sum(data_uncompressed_bytes), 2) compress_rate
from admin.parts;

-- 统计表级数据量大小
select database,
       table,
       sum(rows) as rows,
       sum(bytes) as size,
       sum(bytes_on_disk) as bytes_on_disk,
       sum(data_uncompressed_bytes) as data_uncompressed_bytes,
       sum(data_compressed_bytes) as data_compressed_bytes,
       (data_compressed_bytes / data_uncompressed_bytes) * 100 as compress_rate
  from system.parts
 where active
 group by database, table;
