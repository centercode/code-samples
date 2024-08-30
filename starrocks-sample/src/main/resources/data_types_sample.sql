SELECT
    CAST(0 AS BOOLEAN)
    -- Numeric types
    ,CAST(1 AS TINYINT)
    ,CAST(2 AS SMALLINT)
    ,CAST(3 AS INT)
    ,CAST(4 AS BIGINT)
    ,CAST(5 AS LARGEINT)
    ,CAST(6.66 AS FLOAT)
    ,CAST(7.77 AS DOUBLE)
    ,CAST(8.88 AS DECIMAL(10,2))
    -- Character types
    ,CAST('A' AS CHAR(1))
    ,CAST('AB' AS STRING)
    ,CAST('ABC'AS VARCHAR(10))
    ,CAST('ABCD'AS BINARY(1))
    -- Date types
    ,CAST('2013-01-01' AS DATE)
    ,CAST('2014-01-01' AS DATETIME)
    -- Semi-structured types
    ,CAST([1, 2, 3] AS ARRAY<INT>)
    ,CAST('{"A": 1, "B": TRUE}' AS JSON)
    ,CAST(MAP{1:1, 2:2, 3:3} AS MAP<INT,INT>) 
    ,CAST(NAMED_STRUCT('A', 1, 'B', 2, 'C', 3, 'D', 4) AS STRUCT<A INT, B INT, C INT, D INT>)
    -- Other types
    ,CAST(BITMAP_EMPTY() AS BITMAP)
    ,CAST(HLL_EMPTY() AS HLL)