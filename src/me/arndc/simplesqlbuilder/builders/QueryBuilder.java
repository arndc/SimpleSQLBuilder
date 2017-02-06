package me.arndc.simplesqlbuilder.builders;


import me.arndc.simplesqlbuilder.core.Column;
import me.arndc.simplesqlbuilder.core.Query;
import me.arndc.simplesqlbuilder.core.Table;

import java.util.Arrays;

/**
 * This builder class provides a fluent way to build a {@link Query}.
 *
 * @see Query
 * @see WhereChainBuilder
 */
public final class QueryBuilder {
    private Query query;

    private QueryBuilder() {
        query = new Query();
    }

    public static QueryBuilder newQuery() {
        return new QueryBuilder();
    }

    public QueryBuilder select(CharSequence... columnNames) {
        query.setSelect(columnNames);
        return this;
    }

    public QueryBuilder select(Column... columns) {
        return select(Arrays.stream(columns).map(Column::getName).toArray(CharSequence[]::new));
    }

    public QueryBuilder selectAll() {
        query.setSelect();
        return this;
    }

    public QueryBuilder selectDistinct(CharSequence... columnNames) {
        query.setSelect(columnNames);
        query.setDistinct(true);
        return this;
    }

    public QueryBuilder selectDistinct(Column... columns) {
        return selectDistinct(Arrays.stream(columns).map(Column::getName).toArray(CharSequence[]::new));
    }

    public QueryBuilder selectDistinctAll() {
        query.setSelect();
        query.setDistinct(true);
        return this;
    }

    public QueryBuilder from(Table table) {
        return from(table.getName());
    }

    public QueryBuilder from(String tableName) {
        query.setFrom(tableName);
        return this;
    }

    public QueryBuilder where(String whereClause) {
        query.setWhereClause(whereClause);
        return this;
    }

    public QueryBuilder where(WhereChainBuilder whereChain) {
        query.setWhereClause(whereChain.end());
        return this;
    }

    public QueryBuilder limit(long maxSize){
        query.setLimit(maxSize);
        return this;
    }

    public QueryBuilder limit(long maxSize, long offset){
        query.setLimit(maxSize);
        query.setOffset(offset);
        return this;
    }

    public QueryBuilder orderBy(String columnName, Query.Order order) {
        query.setOrderBy(columnName, order);
        return this;
    }

    public QueryBuilder orderBy(Column column, Query.Order order) {
        return orderBy(column.getName(), order);
    }

    public Query build() {
        return query;
    }

    public String buildStatement() {
        return query.statement();
    }
}
