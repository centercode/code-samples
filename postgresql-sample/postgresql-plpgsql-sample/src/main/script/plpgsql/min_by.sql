--
-- Implement min_by(numeric x, Date y)
--

SELECT
    routine_schema AS schema_name,
    routine_name AS function_name,
    data_type AS return_type,
    routine_definition
FROM
    information_schema.routines
WHERE
    routine_type = 'FUNCTION'
    AND specific_schema NOT IN ('pg_catalog', 'information_schema')
ORDER BY 1, 2, 3;

DROP TYPE min_by_state;
CREATE TYPE min_by_state AS (
    x numeric,
    y date
);

DROP FUNCTION min_by_transfn (state min_by_state, vx anyelement, vy date);
CREATE OR REPLACE FUNCTION min_by_transfn (state min_by_state, anyelement, date)
RETURNS min_by_state
AS $$
    BEGIN
        IF $3 IS NULL THEN
            RETURN state;
        END IF;
        IF state.y IS NULL OR $3 < state.y THEN
            state.x := $2;
            state.y := $3;
        END IF;
        RETURN state;
    END;
$$ LANGUAGE plpgsql;

DROP FUNCTION min_by_finalfn (state min_by_state);
CREATE OR REPLACE FUNCTION min_by_finalfn (state min_by_state)
RETURNS int
AS $$
    BEGIN
        RETURN state.x;
    END;
$$ LANGUAGE plpgsql;

DROP AGGREGATE min_by (anyelement, date);
CREATE AGGREGATE min_by (anyelement, date) (
    SFUNC = min_by_transfn,
    STYPE = min_by_state,
    FINALFUNC = min_by_finalfn
);