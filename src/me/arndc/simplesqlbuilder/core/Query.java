package me.arndc.simplesqlbuilder.core;

import static me.arndc.simplesqlbuilder.util.StatementEnhancer.trim;

/**
 * This class represents the parts of an sql query.
 */
public final class Query implements Statement {
    private String select = "", from = "", whereClause = "", orderBy = "";
    private long limit, offset;
    private boolean distinct = false;

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public void setOrderBy(String columnName, Order order) {
        this.orderBy = columnName + " " + order;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(CharSequence... columnNames) {
        this.select = columnNames.length > 0 ? String.join(", ", columnNames) : "*";
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String tableName) {
        this.from = tableName;
    }

    public String getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long maxSize) {
        this.limit = maxSize;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    @Override
    public String statement() {
        StringBuilder statement = new StringBuilder("SELECT ");

        if (distinct)
            statement.append("DISTINCT ");

        statement.append(select)
                 .append(from.length() > 0 ? " FROM " + from : "")
                 .append(whereClause.length() > 0 ? " WHERE " + whereClause : "");

        if (limit > 0) {
            statement.append(" LIMIT ").append(limit);

            if (offset > 0)
                statement.append(" OFFSET ").append(offset);
        }

        statement.append(orderBy.length() > 0 ? " ORDER BY " + orderBy : "")
                 .append(";");

        return trim(statement.toString());
    }

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
}
