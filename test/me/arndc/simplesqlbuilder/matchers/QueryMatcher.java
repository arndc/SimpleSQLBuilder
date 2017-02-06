package me.arndc.simplesqlbuilder.matchers;

import me.arndc.simplesqlbuilder.core.Query;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class QueryMatcher extends TypeSafeDiagnosingMatcher<Query> {
    private final Matcher<? super String> select;
    private final Matcher<? super String> from;
    private final Matcher<? super String> whereClause;
    private Query expectedQuery;

    public QueryMatcher(Query expectedQuery) {
        this.expectedQuery = expectedQuery;
        select = is(equalTo(expectedQuery.getSelect()));
        from = is(equalTo(expectedQuery.getFrom()));
        whereClause = is(equalTo(expectedQuery.getWhereClause()));
    }

    @Factory
    public static QueryMatcher isQuery(Query expectedQuery) {
        return new QueryMatcher(expectedQuery);
    }

    @Override
    protected boolean matchesSafely(Query query, Description description) {
        boolean matches = true;

        if (!select.matches(query.getSelect())) {
            MismatchReporter.reportMismatch("select", select, query.getSelect(), description, matches);
            matches = false;
        }

        if (!select.matches(query.getSelect())) {
            MismatchReporter.reportMismatch("from", from, query.getFrom(), description, matches);
            matches = false;
        }

        if (!select.matches(query.getSelect())) {
            MismatchReporter.reportMismatch("where clause", whereClause, query.getWhereClause(), description, matches);
            matches = false;
        }

        return matches;
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendText(" a query with a select ")
                .appendValue(expectedQuery.getSelect())
                .appendText(" and with a from ")
                .appendValue(expectedQuery.getFrom())
                .appendText(" and with a where clause of ")
                .appendValue(expectedQuery.getWhereClause());
    }
}
