package org.jooq.impl;

import org.jooq.BindingGetResultSetContext;
import org.jooq.BindingGetSQLInputContext;
import org.jooq.BindingGetStatementContext;
import org.jooq.BindingRegisterContext;
import org.jooq.BindingSQLContext;
import org.jooq.BindingSetSQLOutputContext;
import org.jooq.BindingSetStatementContext;
import org.jooq.Configuration;
import org.jooq.Converter;
import org.jooq.Converters;
import org.jooq.DataType;
import org.jooq.Nullability;
import org.jooq.RenderContext;
import org.jooq.SQLDialect;
import org.jooq.tools.Longs;
import org.jooq.tools.StringUtils;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;

import static org.jooq.SQLDialect.SQLITE;
import static org.jooq.impl.Keywords.K_DATE;

/**
 * To support Standard SQL Date Literals for MySQL,
 * see : https://dev.mysql.com/doc/refman/8.4/en/date-and-time-literals.html
 */
public class StandardDateBinding<U> extends DefaultBinding.AbstractBinding<Date, U> {

    private static final StandardDateBinding<Date> INSTANCE = new StandardDateBinding<>(Converters.identity(Date.class), false);

    public static final DataType<Date> STANDARD_DATE = new DefaultDataType<>(null, Date.class, INSTANCE, "date", "date", 0, 0, 0, Nullability.DEFAULT, null);

    StandardDateBinding(Converter<Date, U> converter, boolean isLob) {
        super(converter, isLob);
    }

    @Override
    final void sqlInline0(BindingSQLContext<U> ctx, Date value) {
        ctx.render().visit(K_DATE).sql("'").sql(escape(value, ctx.render())).sql("'");
    }

    private final String format(Date value, RenderContext render) {
        return escape(value, render);
    }

    @Override
    final void sqlBind0(BindingSQLContext<U> ctx, Date value) throws SQLException {
        super.sqlBind0(ctx, value);
    }

    @Override
    final void register0(BindingRegisterContext<U> ctx) throws SQLException {
        super.register0(ctx);
    }

    @Override
    final void set0(BindingSetStatementContext<U> ctx, Date value) throws SQLException {
        if (ctx.family() == SQLITE)
            ctx.statement().setString(ctx.index(), value.toString());
        else
            ctx.statement().setDate(ctx.index(), value);
    }

    @Override
    final void set0(BindingSetSQLOutputContext<U> ctx, Date value) throws SQLException {
        ctx.output().writeDate(value);
    }

    @Override
    final Date get0(BindingGetResultSetContext<U> ctx) throws SQLException {
        SQLDialect family = ctx.family();
        // SQLite's type affinity needs special care...
        if (family == SQLITE) {
            String date = ctx.resultSet().getString(ctx.index());
            return date == null ? null : new Date(parse(Date.class, date));
        } else {
            return ctx.resultSet().getDate(ctx.index());
        }
    }

    @Override
    final Date get0(BindingGetStatementContext<U> ctx) throws SQLException {
        return ctx.statement().getDate(ctx.index());
    }

    @Override
    final Date get0(BindingGetSQLInputContext<U> ctx) throws SQLException {
        return ctx.input().readDate();
    }

    @Override
    final int sqltype(Statement statement, Configuration configuration) {
        return Types.DATE;
    }

    private static final long parse(Class<? extends java.util.Date> type, String date) throws SQLException {
        // Try reading a plain number first
        Long number = Longs.tryParse(date);
        if (number != null)
            return number;

        // If that fails, try reading a formatted date

        // [#7325] In SQLite dates could be stored in both ISO standard formats:
        //         With T (default standard), or without T (optional standard, JDBC standard)
        date = StringUtils.replace(date, "T", " ");

        if (type == Timestamp.class)
            return Timestamp.valueOf(date).getTime();

            // Dates may come with " 00:00:00". This is safely trimming time information
        else if (type == Date.class)
            return Date.valueOf(date.split(" ")[0]).getTime();

        else if (type == Time.class)
            return Time.valueOf(date).getTime();

        throw new SQLException("Could not parse date " + date);
    }
}