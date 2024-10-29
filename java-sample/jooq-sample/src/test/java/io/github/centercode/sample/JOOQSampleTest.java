package io.github.centercode.sample;

import org.jooq.CommonTableExpression;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Param;
import org.jooq.Query;
import org.jooq.QueryPart;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.SQLDialect;
import org.jooq.SelectJoinStep;
import org.jooq.conf.ParamType;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.StandardDateBinding;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.function;
import static org.jooq.impl.DSL.inline;
import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.select;
import static org.jooq.impl.DSL.table;
import static org.jooq.impl.DSL.val;
import static org.jooq.impl.DSL.with;
import static org.jooq.impl.SQLDataType.BINARY;

public class JOOQSampleTest {

    DSLContext MySQLContext = DSL.using(SQLDialect.MYSQL, new Settings().withRenderFormatted(false));

    static {
        System.setProperty("org.jooq.no-logo", "true");
    }

    @Test
    public void testSimpleSQLBuilder() {
        Query query = DSL.using(SQLDialect.MYSQL)
                .select(field("BOOK.TITLE"), field("AUTHOR.FIRST_NAME"), field("AUTHOR.LAST_NAME"))
                .from(table("BOOK"))
                .join(table("AUTHOR"))
                .on(field("BOOK.AUTHOR_ID").eq(field("AUTHOR.ID")))
                .where(field("BOOK.PUBLISHED_IN").eq(1948));
        String sql = query.getSQL(ParamType.INLINED);
        System.out.println(sql);
    }

    @Test
    public void testCTESQLBuilder() {
        CommonTableExpression<Record2<Integer, String>> t1 = DSL.name("t1")
                .fields("f1", "f2")
                .as(select(val(1), val("a")));
        CommonTableExpression<Record2<Integer, String>> t2 = name("t2")
                .fields("f3", "f4")
                .as(select(val(2), val("b")));

        Query from = DSL.using(SQLDialect.MYSQL)
                .with(t1)
                .with(t2)
                .select(
                        t1.field("f1").add(t2.field("f3")).as("sum"),
                        t1.field("f2").concat(t2.field("f4")).as("f5")
                )
                .from(t1, t2);

        System.out.println(from.getSQL(ParamType.INLINED));
    }

    @Test
    public void testStandardDateLiteral() {
        Param<Date> dtField = DSL.inline(LocalDate.of(2024, 1, 1), StandardDateBinding.STANDARD_DATE);
        Assertions.assertEquals("date'2024-01-01'", MySQLContext.render(dtField));
    }

    // TODO
    static Stream<Arguments> fieldsSource() {
        return Stream.of(
                Arguments.of("cast(true as unsigned)", DSL.inline(Boolean.TRUE, SQLDataType.BOOLEAN)),
                Arguments.of("cast(false as unsigned)", DSL.inline(Boolean.FALSE, SQLDataType.BOOLEAN)),
                Arguments.of("cast(127 as signed)", DSL.inline(0x7F, SQLDataType.TINYINT)),
                Arguments.of("cast(-128 as signed)", DSL.inline(0x80, SQLDataType.TINYINT)),
                Arguments.of("cast(32767 as signed)", DSL.inline(0x7FFF, SQLDataType.SMALLINT)),
                Arguments.of("cast(-32768 as signed)", DSL.inline(0x8000, SQLDataType.SMALLINT)),
                Arguments.of("cast(2147483647 as signed)", DSL.inline(0x7FFFFFFF, SQLDataType.INTEGER)),
                Arguments.of("cast(-2147483648 as signed)", DSL.inline(0x80000000, SQLDataType.INTEGER)),
                Arguments.of("cast(9223372036854775807 as signed)", DSL.inline(0x7FFFFFFFFFFFFFFFL, SQLDataType.BIGINT)),
                Arguments.of("cast(-9223372036854775808 as signed)", DSL.inline(0x8000000000000000L, SQLDataType.BIGINT)),
                Arguments.of("cast(255 as unsigned)", DSL.inline(0xFF, SQLDataType.TINYINTUNSIGNED)),
                Arguments.of("cast(65535 as unsigned)", DSL.inline(0xFFFF, SQLDataType.SMALLINTUNSIGNED)),
                Arguments.of("cast(4294967295 as unsigned)", DSL.inline(0xFFFFFFFFL, SQLDataType.INTEGERUNSIGNED)),
                Arguments.of("cast(18446744073709551615 as unsigned)", DSL.inline(0xFFFFFFFFFFFFFFFFL, SQLDataType.BIGINTUNSIGNED)),
                Arguments.of("cast(3.141592 as decimal)", DSL.inline(3.141592F, SQLDataType.REAL)), // Float
                Arguments.of("cast(3.141592653589793 as decimal)", DSL.inline(3.141592653589793D, SQLDataType.DOUBLE)),
                Arguments.of("cast(3.141592653589793238462643383279 as decimal)", DSL.inline(new BigDecimal("3.141592653589793238462643383279"), SQLDataType.DECIMAL)),
                Arguments.of("cast(3.14 as decimal(10, 2))", DSL.inline(new BigDecimal("3.14"), SQLDataType.DECIMAL(10, 2))),
                Arguments.of("cast('abc' as char)", DSL.inline("abc", SQLDataType.CHAR(0))),
                Arguments.of("cast('abc' as char)", DSL.inline("abc", SQLDataType.CHAR)),
                Arguments.of("cast('abc' as char)", DSL.inline("abc", SQLDataType.VARCHAR)),
                Arguments.of("cast('abc' as char)", DSL.inline("abc", SQLDataType.VARCHAR(0))),
                Arguments.of("cast(X'010203' as binary)", DSL.inline(new byte[]{1, 2, 3}, BINARY)),
                Arguments.of("cast(X'010203' as binary)", DSL.inline(new byte[]{1, 2, 3}, BINARY(0))),
                Arguments.of("cast({d '1970-01-01'} as date)", DSL.inline(new Date(0), SQLDataType.DATE)),
                Arguments.of("cast({d '2024-01-01'} as date)", DSL.inline(LocalDate.of(2024, 1, 1), SQLDataType.LOCALDATE)),
                Arguments.of("cast({ts '2024-01-01 00:00:00.0'} as datetime)", DSL.inline(LocalDateTime.of(2024, 1, 1, 0, 0, 0), SQLDataType.LOCALDATETIME)),
                Arguments.of("cast(timestamp with time zone '2024-01-01 00:00:00+08:00' as timestamp with time zone)", DSL.inline(LocalDateTime.of(2024, 1, 1, 0, 0, 0), SQLDataType.OFFSETDATETIME)),
                Arguments.of("cast(timestamp with time zone '2024-01-01 00:00:00+08:00' as timestamp with time zone)", DSL.inline(LocalDateTime.of(2024, 1, 1, 0, 0, 0), SQLDataType.OFFSETDATETIME(0))),
                Arguments.of("cast({ts '2024-01-01 00:00:00.0'} as datetime)", DSL.inline(LocalDateTime.of(2024, 1, 1, 0, 0, 0), SQLDataType.TIMESTAMP)),
                Arguments.of("cast(timestamp with time zone '2024-01-01 00:00:00+08:00' as timestamp with time zone)", DSL.inline(LocalDateTime.of(2024, 1, 1, 0, 0, 0), SQLDataType.TIMESTAMPWITHTIMEZONE)),
                Arguments.of("cast('[]' as json)", DSL.inline("[]", SQLDataType.JSON)),
                Arguments.of("cast(bitmap_union(bm) as binary)", function("bitmap_union", BINARY, field("bm")))
        );
    }

    @ParameterizedTest
    @MethodSource("fieldsSource")
    void testSelectFields(String sql, Field field) {
        String sqlPart = MySQLContext.render(DSL.cast(field, field.getDataType()));
        Assertions.assertEquals(sql, sqlPart);
        System.out.println("select " + sqlPart + ";");
    }

    @Test
    public void testCTE() {
        CommonTableExpression<Record1<Integer>> cte = name("t").as(DSL.selectOne());
        SelectJoinStep<? extends Record1<?>> selectStep = with(cte)
                .select(cte.field("one"))
                .from(cte);
        String expectSQL = "with `t` as (select 1 as `one` from dual) select `t`.`one` from `t`";
        Assertions.assertEquals(expectSQL, MySQLContext.render(selectStep));
        Field<?> a = cte.field("a");
    }

    static Stream<Arguments> exprsSource() {
        Condition cond1 = field("c1").eq(DSL.inline(1));
        Condition cond2 = field("c2").gt(DSL.inline(2));
        Condition cond3 = field("c3").lt(DSL.inline(3));
        LocalDate start = LocalDate.of(2024, 1, 1);
        LocalDate end = LocalDate.of(2024, 1, 31);
        return Stream.of(
                Arguments.of("`c1`", DSL.inline(1).as("c1")),
                Arguments.of("c1 = 1", cond1),
                Arguments.of("c2 > 2", cond2),
                Arguments.of("c3 < 3", cond3),
                Arguments.of("c1 is null", field("c1").isNull()),
                Arguments.of("c1 is not null", field("c1").isNotNull()),
                Arguments.of("(c1 = 1 or (c2 > 2 and c3 < 3))", cond1.or(cond2.and(cond3))),
                Arguments.of("c1 between 1 and 2", field("c1").between(inline(1), inline(2))),
                Arguments.of("c1 between {d '2024-01-01'} and {d '2024-01-31'}", field("c1").between(inline(start), inline(end)))
        );
    }

    @ParameterizedTest
    @MethodSource("exprsSource")
    void testExpr(String sql, QueryPart expr) {
        String sqlPart = MySQLContext.render(expr);
        Assertions.assertEquals(sql, MySQLContext.render(expr));
        System.out.println("select " + sqlPart + ";");
    }

    static Stream<Arguments> clauseSource() {
        return Stream.of(
                Arguments.of("select 1 as `one` from t1, t2", DSL.selectOne().from(table("t1"), table("t2"))),
                Arguments.of("select 1 as `one` from `t1`", DSL.selectOne().from(DSL.name("t1"))),
                Arguments.of("select 1 as `one` from t1", DSL.selectOne().from(DSL.sql("t1")))
        );
    }

    @ParameterizedTest
    @MethodSource("clauseSource")
    void testClause(String sql, QueryPart clause) {
        String sqlPart = MySQLContext.render(clause);
        Assertions.assertEquals(sql, MySQLContext.render(clause));
        System.out.println(sqlPart + ";");
    }
}