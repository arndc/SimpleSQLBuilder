package me.arndc.simplesqlbuilder.core;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toMap;
import static me.arndc.simplesqlbuilder.util.StatementEnhancer.trim;

/**
 * This class represents a table as known in a sql database.
 * This means it has a name and one or more columns.
 *
 * @see TableCommands
 * @see Column
 */
public final class Table implements TableCommands {
    private final String name;
    private Map<String, Column> columns;

    public Table(String name) {
        this.name = name;
        this.columns = new LinkedHashMap<>();
    }

    public void addColumn(Column column) {
        this.columns.put(column.getName(), column);
    }

    public void addColumn(Column... columns) {
        Map<String, Column> newColumns = Arrays.stream(columns)
                                               .collect(toMap(Column::getName,
                                                              column -> column,
                                                              (v1, v2) -> null,
                                                              LinkedHashMap::new));

        this.columns.putAll(newColumns);
    }

    public String getName() {
        return name;
    }

    public Map<String, Column> getColumns() {
        return columns;
    }

    public Column getColumn(String columnName) {
        return columns.get(columnName);
    }

    @Override
    public String createStatement() {
        String statement = "CREATE TABLE " + this.name;

        statement += "(";
        statement += columns.values()
                            .stream()
                            .map(Column::createDefinition)
                            .collect(joining(", "));
        statement += ");";

        return trim(statement);
    }

    @Override
    public String dropStatement() {
        String statement = "DROP TABLE IF EXISTS " + this.name + ";";

        return trim(statement);
    }

}
