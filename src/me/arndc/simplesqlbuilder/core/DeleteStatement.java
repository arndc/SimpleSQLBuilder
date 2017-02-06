package me.arndc.simplesqlbuilder.core;

import static me.arndc.simplesqlbuilder.util.StatementEnhancer.trim;

/**
 * This class represents the parts of an sql delete statement.
 *
 * @see Statement
 */
public final class DeleteStatement implements Statement {
    private final String tableName;
    private String whereClause;

    public DeleteStatement(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public String getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }

    @Override
    public String statement() {
        String statement = "DELETE FROM " + tableName;

        if (whereClause == null || whereClause.length() == 0)
            statement += ";";
        else
            statement += " WHERE" + whereClause + ";";

        return trim(statement);
    }
}