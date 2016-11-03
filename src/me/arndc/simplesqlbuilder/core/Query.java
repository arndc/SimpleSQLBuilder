package me.arndc.simplesqlbuilder.core;

import static me.arndc.simplesqlbuilder.util.StatementEnhancer.trim;

/**
 * This class represents the parts of an sql query.
 */
public final class Query implements Statement {
    private String select = "", from = "", whereClause = "", orderBy = "";
    private boolean distinct = false;

    public enum Order {
        ASCENDING("ASC"), DESCENDING("DESC");

        private String value;

        Order(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public void setSelect(CharSequence... columnNames) {
        this.select = columnNames.length > 0 ? String.join(", ", columnNames) : "*";
    }

    public void setFrom(String tableName) {
        this.from = tableName;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public void setOrderBy(String columnName, Order order) {
        this.orderBy = columnName + " " + order;
    }

    public String getSelect() {
        return select;
    }

    public String getFrom() {
        return from;
    }

    public String getWhereClause() {
        return whereClause;
    }

    @Override
    public String statement() {

        String statement = "SELECT ";

        if (distinct)
            statement += "DISTINCT ";

        statement += select;

        statement += from.length() > 0 ? " FROM " + from : "";
        statement += whereClause.length() > 0 ? " WHERE " + whereClause : "";
        statement += orderBy.length() > 0 ? " ORDER BY " + orderBy : "";
        statement += ";";

        return trim(statement);
    }
}
