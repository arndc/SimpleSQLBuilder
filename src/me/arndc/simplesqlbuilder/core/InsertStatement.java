package me.arndc.simplesqlbuilder.core;

import me.arndc.simplesqlbuilder.util.StatementEnhancer;

import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.stream.Collectors.joining;
import static me.arndc.simplesqlbuilder.util.StatementEnhancer.trim;

/**
 * This class represents the parts of an sql insert statement.
 *
 * @see Statement
 */
public final class InsertStatement implements Statement {
    private String tableName;
    private Map<String, Object> values;

    public InsertStatement(String tableName) {
        this.tableName = tableName;
        values = new LinkedHashMap<>();
    }

    public void addValue(String columnName, Object value) {
        values.put(columnName, value);
    }

    public String getTableName() {
        return tableName;
    }

    public Map<String, Object> getValues() {
        return values;
    }

    @Override
    public String statement() {
        String columns = this.values.keySet().stream()
                                    .collect(joining(", ", "(", ")"));
        String values = this.values.values().stream()
                                   .map(StatementEnhancer::escapeValue)
                                   .collect(joining(", ", "(", ")"));

        String statement = "INSERT INTO " + tableName + " " + columns + " VALUES " + values + ";";
        return trim(statement);
    }
}