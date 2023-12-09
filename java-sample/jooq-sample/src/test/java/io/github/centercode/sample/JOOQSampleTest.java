package io.github.centercode.sample;

import org.jooq.CommonTableExpression;
import org.jooq.Query;
import org.jooq.Record2;
import org.jooq.SQLDialect;
import org.jooq.conf.ParamType;
import org.jooq.impl.DSL;
import org.junit.Test;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.select;
import static org.jooq.impl.DSL.table;
import static org.jooq.impl.DSL.val;

public class JOOQSampleTest {


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
}