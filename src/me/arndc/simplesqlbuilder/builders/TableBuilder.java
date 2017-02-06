package me.arndc.simplesqlbuilder.builders;

import me.arndc.simplesqlbuilder.core.Column;
import me.arndc.simplesqlbuilder.core.Table;

/**
 * This builder class provides a fluent way to build a {@link Table}.
 *
 * @see Table
 * @see Column
 */
public final class TableBuilder {
    private Table table;

    private TableBuilder(String name) {
        table = new Table(name);
    }

    private TableBuilder(Table table) {
        this.table = table;
    }

    public static TableBuilder newTable(String name) {
        return new TableBuilder(name);
    }

    public TableBuilder withColumn(Column column) {
        table.addColumn(column);
        return this;
    }

    public TableBuilder withColumn(ColumnBuilder columnBuilder) {
        return withColumn(columnBuilder.build());
    }

    public Table build() {
        return table;
    }
}