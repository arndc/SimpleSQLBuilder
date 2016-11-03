package me.arndc.simplesqlbuilder.builders;

import me.arndc.simplesqlbuilder.core.Column;

/**
 * This builder class provides a fluent way to build a {@link Column}.
 * In the build process several checks are done before returning the eventual {@link Column} object.
 *
 * @see Column
 */
public final class ColumnBuilder {
    private Column column;

    private ColumnBuilder(String name, String dataType) {
        column = new Column(name, dataType);
    }


    public ColumnBuilder hasPrimaryKey() {
        column.setPrimaryKey(true);
        return this;
    }

    public ColumnBuilder withAutoIncrement() {
        column.setAutoIncrement(true);
        return this;
    }

    public ColumnBuilder withUniqueConstraint() {
        column.setUnique(true);
        return this;
    }


    public Column build() {
        if (column.hasPrimaryKey())
            column.setUnique(false);

        if (!column.hasPrimaryKey())
            column.setAutoIncrement(false);

        return column;
    }

    public static ColumnBuilder newColumn(String name, String dataType) {
        return new ColumnBuilder(name, dataType);
    }
}