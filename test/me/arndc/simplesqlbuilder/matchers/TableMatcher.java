package me.arndc.simplesqlbuilder.matchers;

import me.arndc.simplesqlbuilder.core.Table;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class TableMatcher extends TypeSafeDiagnosingMatcher<Table> {
    private final Matcher<? super String> name;
    private final Matcher<? super Integer> columnCount;
    private Table expectedTable;

    private TableMatcher(Table expectedTable) {
        this.expectedTable = expectedTable;
        name = is(equalTo(expectedTable.getName()));
        columnCount = is(expectedTable.getColumns().size());
    }

    @Factory
    public static TableMatcher isTable(Table expectedTable) {
        return new TableMatcher(expectedTable);
    }

    @Override
    protected boolean matchesSafely(Table table, Description description) {
        boolean matches = true;

        if (!name.matches(table.getName())) {
            MismatchReporter.reportMismatch("name", name, table.getName(), description, matches);
            matches = false;
        }

        if (!columnCount.matches(table.getColumns().size())) {
            MismatchReporter
                    .reportMismatch("column-count", columnCount, table.getColumns().size(), description, matches);
            matches = false;
        }

        return matches;
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendText(" a table with a name ")
                .appendValue(expectedTable.getName())
                .appendText(" with the following column-count ")
                .appendValue(expectedTable.getColumns().size());
    }
}
