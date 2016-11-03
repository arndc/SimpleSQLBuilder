package me.arndc.simplesqlbuilder.core;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * This class provides only static methods for doing arithmetic operations on columns.
 * These operations can be used inside a {@link Function}.
 *
 * @see Function
 */
public final class ArithmeticColumnOperation {
    public static String multiply(Column... columns) {
        return Arrays.stream(columns)
                .map(Column::getName)
                .collect(Collectors.joining(" * "));
    }

    public static String multiply(CharSequence... columnNames) {
        return String.join(" * ", columnNames);
    }
}
