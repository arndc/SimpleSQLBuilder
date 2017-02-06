package me.arndc.simplesqlbuilder.builders;

/**
 * This builder class provides a chaining mechanism to bind fluently multiple conditions into one where clause.
 */
public final class WhereChainBuilder {
    private String whereClause;

    public WhereChainBuilder(String whereClause) {
        this.whereClause = whereClause;
    }

    public static WhereChainBuilder whereChain(String startWhereClause) {
        return new WhereChainBuilder(startWhereClause);
    }

    public WhereChainBuilder and(String extraClause) {
        bind("AND", extraClause);
        return this;
    }

    public WhereChainBuilder or(String extraClause) {
        bind("OR", extraClause);
        return this;
    }

    String end() {
        return whereClause;
    }

    private void bind(String binder, String extraClause) {
        this.whereClause += (" " + binder + " ") + extraClause;
    }
}
