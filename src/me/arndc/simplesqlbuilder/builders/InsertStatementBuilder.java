package me.arndc.simplesqlbuilder.builders;

import me.arndc.simplesqlbuilder.core.Column;
import me.arndc.simplesqlbuilder.core.InsertStatement;
import me.arndc.simplesqlbuilder.core.Table;

/**
 * This builder class provides a fluent way to build an {@link InsertStatement}.
 *
 * @see InsertStatement
 */
public final class InsertStatementBuilder {
    private InsertStatement insertStatement;

    private InsertStatementBuilder(String tableName) {
        insertStatement = new InsertStatement(tableName);
    }


    public InsertStatementBuilder withValue(String columnName, Object value) {
        insertStatement.addValue(columnName, value);
        return this;
    }

    public InsertStatementBuilder withValue(Column column, Object value) {
        return withValue(column.getName(), value);
    }


    public InsertStatement build() {
        return insertStatement;
    }

    public String buildStatement() {
        return insertStatement.statement();
    }


    public static InsertStatementBuilder insertInto(String tableName) {
        return new InsertStatementBuilder(tableName);
    }

    public static InsertStatementBuilder insertInto(Table table) {
        return insertInto(table.getName());
    }

}