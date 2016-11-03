package me.arndc.simplesqlbuilder.core;

/**
 * This class provides only static methods that can be used in a {@link Query}.
 */
public final class Function {

    public static String avg(Column column) {
        return avg(column.getName());
    }

    public static String avg(String columnName) {
        return generateFunction("avg", columnName);
    }


    public static String count(Column column) {
        return count(column.getName());
    }

    public static String count(String columnName) {
        return generateFunction("count", columnName);
    }


    public static String max(Column column) {
        return max(column.getName());
    }

    public static String max(String columnName) {
        return generateFunction("max", columnName);
    }


    public static String min(Column column) {
        return min(column.getName());
    }

    public static String min(String columnName) {
        return generateFunction("min", columnName);
    }


    public static String sum(Column column) {
        return sum(column.getName());
    }

    public static String sum(String columnName) {
        return generateFunction("sum", columnName);
    }


    private static String generateFunction(String name, String value) {
        return name + "(" + value + ")";
    }

}