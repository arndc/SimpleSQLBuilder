package me.arndc.simplesqlbuilder.builders;

import me.arndc.simplesqlbuilder.core.DeleteStatement;
import me.arndc.simplesqlbuilder.core.Table;

/**
 * This builder class provides a fluent way to build a {@link DeleteStatement}.
 *
 * @see DeleteStatement
 */
public final class DeleteStatementBuilder {
    private DeleteStatement deleteStatement;

    public DeleteStatementBuilder(String tableName) {
        this.deleteStatement = new DeleteStatement(tableName);
    }

    public static DeleteStatementBuilder deleteFrom(String tableName) {
        return new DeleteStatementBuilder(tableName);
    }

    public static DeleteStatementBuilder deleteFrom(Table table) {
        return deleteFrom(table.getName());
    }

    public DeleteStatementBuilder where(String whereClause) {
        deleteStatement.setWhereClause(whereClause);
        return this;
    }

    public DeleteStatement build() {
        return deleteStatement;
    }

    public String buildStatement() {
        return deleteStatement.statement();
    }

}
