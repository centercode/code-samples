create table all_datatype_text(
    col1 TINYINT,
    col2 SMALLINT,
    col3 INT,
    col4 BIGINT,
    col5 FLOAT,
    col6 DOUBLE,
    col7 DECIMAL(4,2),
    col8 TIMESTAMP,
    col9 DATE,
    col10 INTERVAL,
    col11 STRING,
    col12 VARCHAR(128),
    col13 CHAR(16),
    col14 BOOLEAN,
    col15 BINARY
) stored as textfile;

create table all_datatype_orc(
    col1 TINYINT,
    col2 SMALLINT,
    col3 INT,
    col4 BIGINT,
    col5 FLOAT,
    col6 DOUBLE,
    col7 DECIMAL(4,2),
    col8 TIMESTAMP,
    col9 DATE,
    col11 STRING,
    col12 VARCHAR(128),
    col13 CHAR(16),
    col14 BOOLEAN,
    col15 BINARY
) stored as orc;


create table all_datatype_parquet(
    col1 TINYINT,
    col2 SMALLINT,
    col3 INT,
    col4 BIGINT,
    col5 FLOAT,
    col6 DOUBLE,
    col7 DECIMAL(4,2),
    col8 TIMESTAMP,
    col9 DATE,
    col11 STRING,
    col12 VARCHAR(128),
    col13 CHAR(16),
    col14 BOOLEAN,
    col15 BINARY
) stored as parquet;

create table all_datatype_sequencefile(
    col1 TINYINT,
    col2 SMALLINT,
    col3 INT,
    col4 BIGINT,
    col5 FLOAT,
    col6 DOUBLE,
    col7 DECIMAL(4,2),
    col8 TIMESTAMP,
    col9 DATE,
    col11 STRING,
    col12 VARCHAR(128),
    col13 CHAR(16),
    col14 BOOLEAN,
    col15 BINARY
) stored as SEQUENCEFILE;