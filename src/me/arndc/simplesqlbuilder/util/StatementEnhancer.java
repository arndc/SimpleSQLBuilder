package me.arndc.simplesqlbuilder.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * This class provides only static methods which help to optimise statements.
 */
public final class StatementEnhancer {

    public static String trim(String statement) {
        return statement.replaceAll("\\s+", " ");
    }

    /**
     * If het object is not of type {@link String}, {@link Date}, {@link LocalDate} or {@link Number}
     * the {@code toString()} method is called and the outcome is escaped like a {@link String} value.
     */
    public static String escapeValue(Object value) {
        if (value instanceof String) return "'" + ((String) value).replaceAll("'", "''") + "'";
        else if (value instanceof Date) return formatDate((Date) value);
        else if (value instanceof LocalDate) return formatDate((LocalDate) value);
        else if (value instanceof Number) return value.toString();
        else return escapeValue(value.toString());
    }

    public static String formatDate(Date date) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ROOT);
        return escapeValue(formatter.format(date));
    }

    public static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return escapeValue(date.format(formatter));
    }
}
