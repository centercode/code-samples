CREATE TABLE admin.parts
(
    `rows` UInt64,
    `bytes_on_disk` UInt64,
    `data_compressed_bytes` UInt64,
    `data_uncompressed_bytes` UInt64,
    `marks_bytes` UInt64,
    `partition_id` String,
    `database` String,
    `table` String,
    `engine` String,
    `disk_name` String,
    `path` String,
    `bytes` UInt64,
    `marks_size` UInt64
)
ENGINE = Distributed('production', 'system', 'parts');