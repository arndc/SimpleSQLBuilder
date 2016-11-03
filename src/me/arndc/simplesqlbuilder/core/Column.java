package me.arndc.simplesqlbuilder.core;

/**
 * This class represents a column in a database.
 */
public final class Column {

    private final String name;
    private final String dataType;

    private boolean primaryKey;
    private boolean autoIncrement;
    private boolean unique;

    public Column(String name, String dataType) {
        this.name = name;
        this.dataType = dataType;
    }


    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }


    public String getName() {
        return name;
    }

    public String getDataType() {
        return dataType;
    }

    public boolean hasPrimaryKey() {
        return primaryKey;
    }

    public boolean hasAutoIncrement() {
        return autoIncrement;
    }

    public boolean isUnique() {
        return unique;
    }


    String createDefinition() {
        String declaration = String.format("%s %s", this.name, this.dataType);

        if (primaryKey) {
            declaration += " PRIMARY KEY";
            if (autoIncrement)
                declaration += " AUTOINCREMENT";
        } else if (unique) {
            declaration += " UNIQUE";
        }

        return declaration;
    }


    /**
     * @param operator the condition that the column has to fulfill.
     * @return an operation that is particularly used in a where clause.
     */
    public String is(Operator operator) {
        return " " + this.name + operator.execute();
    }
}
