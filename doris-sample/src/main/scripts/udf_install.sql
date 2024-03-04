CREATE FUNCTION to_upper(string)
RETURNS string
PROPERTIES (
    "file" = "http://127.0.0.1:8000/doris-sample-1.0.jar",
    "symbol" = "io.github.centercode.doris.udf.ToUpperCaseUDF",
    "always_nullable"="true",
    "type" = "JAVA_UDF"
);

CREATE AGGREGATE FUNCTION my_sum(INT)
RETURNS INT
PROPERTIES
(
    "file" = "http://127.0.0.1:8000/doris-sample-1.0.jar",
    "symbol" = "io.github.centercode.doris.udf.SumUDAF",
    "always_nullable"="true",
    "type" = "JAVA_UDF"
);