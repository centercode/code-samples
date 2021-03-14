create table all_data_type(
    col1 TINYINT,
    col2 SMALLINT,
    col3 INT,
    col4 BIGINT,
    col5 FLOAT,
    col6 DOUBLE,
    col7 DECIMAL(4,2),
    col8 TIMESTAMP,
    col9 STRING,
    col10 BOOLEAN,
    primary key (col1)
) stored as kudu;