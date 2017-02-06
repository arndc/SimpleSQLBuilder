package me.arndc.simplesqlbuilder.matchers;

import me.arndc.simplesqlbuilder.core.UpdateStatement;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import static me.arndc.simplesqlbuilder.matchers.MismatchReporter.reportMismatch;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class UpdateStatementMatcher extends TypeSafeDiagnosingMatcher<UpdateStatement> {
    private final Matcher<? super String> tableName;
    private final Matcher<? super Integer> setterCount;
    private final Matcher<? super String> whereClause;
    private UpdateStatement expectedUpdateStatement;

    public UpdateStatementMatcher(UpdateStatement expectedUpdate) {
        this.expectedUpdateStatement = expectedUpdate;
        tableName = is(equalTo(this.expectedUpdateStatement.getTableName()));
        setterCount = is(equalTo(this.expectedUpdateStatement.getSetters().size()));
        whereClause = is(equalTo(this.expectedUpdateStatement.getWhereClause()));
    }

    @Factory
    public static UpdateStatementMatcher isUpdateStatement(UpdateStatement expectedUpdateStatement) {
        return new UpdateStatementMatcher(expectedUpdateStatement);
    }

    @Override
    protected boolean matchesSafely(UpdateStatement updateStatement, Description description) {
        boolean matches = true;

        if (!tableName.matches(updateStatement.getTableName())) {
            reportMismatch("table name",
                           setterCount,
                           expectedUpdateStatement.getSetters().size(),
                           description,
                           matches);
            matches = false;
        }

        if (!setterCount.matches(updateStatement.getSetters().size())) {
            reportMismatch("setter count", tableName, expectedUpdateStatement.getTableName(), description, matches);
            matches = false;
        }

        if (!whereClause.matches(updateStatement.getWhereClause())) {
            reportMismatch("where clause", whereClause, expectedUpdateStatement.getWhereClause(), description, matches);
            matches = false;
        }

        return matches;
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendText(" a update statement with table ")
                .appendValue(expectedUpdateStatement.getTableName())
                .appendText(" and with ")
                .appendValue(setterCount)
                .appendText(" setters and a where clause ")
                .appendValue(expectedUpdateStatement.getWhereClause());
    }
}
