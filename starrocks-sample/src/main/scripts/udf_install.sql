CREATE FUNCTION to_upper(string)
RETURNS string
PROPERTIES (
    "symbol" = "io.github.centercode.starrocks.udf.ToUpperCaseUDF",
    "type" = "StarrocksJar",
    "file" = "http://127.0.0.1:8000/starrocks-sample-1.0.jar"
);

CREATE AGGREGATE FUNCTION my_sum(INT)
RETURNS INT
PROPERTIES
(
    "symbol" = "io.github.centercode.starrocks.udf.SumUDAF",
    "type" = "StarrocksJar",
    "file" = "http://127.0.0.1:8000/starrocks-sample-1.0.jar"
);

CREATE AGGREGATE FUNCTION my_sum(INT)
RETURNS INT
PROPERTIES
(
    "analytic" = "true",
    "symbol" = "io.github.centercode.starrocks.udf.SumUDWF",
    "type" = "StarrocksJar",
    "file" = "http://172.17.0.1:8000/starrocks-sample-1.0.jar"
);

CREATE TABLE FUNCTION split(STRING)
RETURNS STRING
PROPERTIES
(
    "symbol" = "io.github.centercode.starrocks.udf.SplitUDTF",
    "type" = "StarrocksJar",
    "file" = "http://127.0.0.1:8000/starrocks-sample-1.0.jar"
);