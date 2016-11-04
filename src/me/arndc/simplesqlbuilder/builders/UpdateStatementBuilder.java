package me.arndc.simplesqlbuilder.builders;

import me.arndc.simplesqlbuilder.core.Column;
import me.arndc.simplesqlbuilder.core.Table;
import me.arndc.simplesqlbuilder.core.UpdateStatement;

/**
 * This builder class provides a fluent way to build a {@link UpdateStatement}.
 *
 * @see UpdateStatement
 */
public class UpdateStatementBuilder {
    private UpdateStatement updateStatement;

    public UpdateStatementBuilder(String tableName) {
        updateStatement = new UpdateStatement(tableName);
    }


    public UpdateStatementBuilder set(String columnName, Object value){
        updateStatement.addSetter(columnName, value);
        return this;
    }

    public UpdateStatementBuilder set(Column column, Object value){
        return set(column.getName(), value);
    }


    public UpdateStatementBuilder where(String whereClause) {
        updateStatement.setWhereClause(whereClause);
        return this;
    }

    public UpdateStatementBuilder where(WhereChainBuilder whereChain) {
        updateStatement.setWhereClause(whereChain.end());
        return this;
    }


    public UpdateStatement build() {
        return updateStatement;
    }

    public String buildStatement() {
        return updateStatement.statement();
    }


    public static UpdateStatementBuilder updateFrom(String tableName){
        return new UpdateStatementBuilder(tableName);
    }

    public static UpdateStatementBuilder updateFrom(Table table){
        return updateFrom(table.getName());
    }
}
