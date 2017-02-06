package me.arndc.simplesqlbuilder.core;

import static me.arndc.simplesqlbuilder.util.StatementEnhancer.escapeValue;

/**
 * This class provides static methods to do checks on a {@link Column} in a where clause.
 *
 * @see Column
 */
public final class Operator {
    private String operation;

    private Operator(String operation) {
        this.operation = operation;
    }

    public static Operator between(Object value, Object otherValue) {
        return new Operator(" BETWEEN " + escapeValue(value) + " AND " + escapeValue(otherValue));
    }

    public static Operator equalsTo(Object value) {
        return new Operator(" = " + escapeValue(value));
    }

    String execute() {
        return operation;
    }
}
