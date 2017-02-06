package me.arndc.simplesqlbuilder.matchers;

import me.arndc.simplesqlbuilder.core.Column;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class ColumnMatcher extends TypeSafeDiagnosingMatcher<Column> {
    private final Matcher<? super String> name;
    private final Matcher<? super Boolean> primaryKey;
    private final Matcher<? super Boolean> autoincrement;
    private final Matcher<? super Boolean> unique;
    private Column expectedColumn;


    private ColumnMatcher(Column expectedColumn) {
        this.expectedColumn = expectedColumn;
        name = is(equalTo(expectedColumn.getName()));
        primaryKey = is(expectedColumn.hasPrimaryKey());
        autoincrement = is(expectedColumn.hasAutoIncrement());
        unique = is(expectedColumn.isUnique());
    }

    @Factory
    public static ColumnMatcher isColumn(Column expectedColumn) {
        return new ColumnMatcher(expectedColumn);
    }

    @Override
    protected boolean matchesSafely(Column column, Description description) {
        boolean matches = true;

        if (!name.matches(column.getName())) {
            MismatchReporter.reportMismatch("name", name, column.getName(), description, matches);
            matches = false;
        }

        if (!primaryKey.matches(column.hasPrimaryKey())) {
            MismatchReporter.reportMismatch("primary key", primaryKey, column.hasPrimaryKey(), description, matches);
            matches = false;
        }

        if (!autoincrement.matches(column.hasAutoIncrement())) {
            MismatchReporter
                    .reportMismatch("auto increment", autoincrement, column.hasAutoIncrement(), description, matches);
            matches = false;
        }

        if (!unique.matches(column.isUnique())) {
            MismatchReporter.reportMismatch("unique constraint", unique, column.isUnique(), description, matches);
            matches = false;
        }

        return matches;
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendText(" a column with a name ")
                .appendValue(expectedColumn.getName());
    }
}
