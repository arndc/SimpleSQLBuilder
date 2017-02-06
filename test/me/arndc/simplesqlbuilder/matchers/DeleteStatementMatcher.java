package me.arndc.simplesqlbuilder.matchers;

import me.arndc.simplesqlbuilder.core.DeleteStatement;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import static me.arndc.simplesqlbuilder.matchers.MismatchReporter.reportMismatch;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class DeleteStatementMatcher extends TypeSafeDiagnosingMatcher<DeleteStatement> {
    private final Matcher<? super String> tableName;
    private final Matcher<? super String> whereClause;
    private DeleteStatement expectedDeleteStatement;

    public DeleteStatementMatcher(DeleteStatement expectedDelete) {
        this.expectedDeleteStatement = expectedDelete;
        tableName = is(equalTo(this.expectedDeleteStatement.getTableName()));
        whereClause = is(equalTo(this.expectedDeleteStatement.getWhereClause()));
    }

    @Factory
    public static DeleteStatementMatcher isDeleteStatement(DeleteStatement expectedDeleteStatement) {
        return new DeleteStatementMatcher(expectedDeleteStatement);
    }

    @Override
    protected boolean matchesSafely(DeleteStatement deleteStatement, Description description) {
        boolean matches = true;

        if (!tableName.matches(deleteStatement.getTableName())) {
            reportMismatch("table name", tableName, expectedDeleteStatement.getTableName(), description, matches);
            matches = false;
        }

        if (!whereClause.matches(deleteStatement.getWhereClause())) {
            reportMismatch("where clause", whereClause, expectedDeleteStatement.getWhereClause(), description, matches);
            matches = false;
        }

        return matches;
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendText(" a delete statement with table ")
                .appendValue(expectedDeleteStatement.getTableName())
                .appendText(" and a where clause ")
                .appendValue(expectedDeleteStatement.getWhereClause());
    }
}
