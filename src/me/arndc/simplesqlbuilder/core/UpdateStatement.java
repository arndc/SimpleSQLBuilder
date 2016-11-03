package me.arndc.simplesqlbuilder.core;

import me.arndc.simplesqlbuilder.util.StatementEnhancer;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class represents the parts of an sql update statement.
 *
 * @see Statement
 */
public final class UpdateStatement implements Statement {
    private String tableName;
    private Map<String, Object> setters;
    private String whereClause;

    public UpdateStatement(String tableName) {
        this.tableName = tableName;
        setters = new LinkedHashMap<>();
    }

    public void addSetter(String columnName, Object value) {
        this.setters.put(columnName, StatementEnhancer.escapeValue(value));
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }


    public String getTableName() {
        return tableName;
    }

    public Map<String, Object> getSetters() {
        return setters;
    }

    public String getWhereClause() {
        return whereClause;
    }

    @Override
    public String statement() {

        String statement = "UPDATE " + tableName;

        statement += " SET " + setters.entrySet().stream()
                .map(entry -> entry.getKey() + " = " + entry.getValue())
                .collect(Collectors.joining(", "));

        if (whereClause == null || whereClause.length() == 0)
            statement += ";";
        else
            statement += " WHERE " + whereClause + ";";

        return StatementEnhancer.trim(statement);
    }


}
