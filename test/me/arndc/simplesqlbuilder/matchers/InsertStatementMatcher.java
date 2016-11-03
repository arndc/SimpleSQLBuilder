package me.arndc.simplesqlbuilder.matchers;

import me.arndc.simplesqlbuilder.core.Column;
import me.arndc.simplesqlbuilder.core.InsertStatement;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class InsertStatementMatcher extends TypeSafeDiagnosingMatcher<InsertStatement> {
    private InsertStatement expectedInsertStatement;
    private final Matcher<? super String> tableName;
    private final Matcher<? super Map<Column, String>> valueCount;


    private InsertStatementMatcher(InsertStatement expectedInsertStatement) {
        this.expectedInsertStatement = expectedInsertStatement;
        this.tableName = is(equalTo(expectedInsertStatement.getTableName()));
        this.valueCount = is(expectedInsertStatement.getValues().size());
    }

    @Override
    protected boolean matchesSafely(InsertStatement insertStatement, Description description) {
        boolean matches = true;

        if (!tableName.matches(insertStatement.getTableName())) {
            MismatchReporter.reportMismatch("table", tableName, insertStatement.getTableName(), description, matches);
            matches = false;
        }

        if (!valueCount.matches(insertStatement.getValues().size())) {
            MismatchReporter.reportMismatch("value-count", valueCount, insertStatement.getValues().size(), description, matches);
            matches = false;
        }
        

        return matches;
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendText(" a insert with table ")
                .appendValue(expectedInsertStatement.getTableName());
    }


    @Factory
    public static InsertStatementMatcher isInsertStatement(InsertStatement expectedInsertStatement) {
        return new InsertStatementMatcher(expectedInsertStatement);
    }
}
