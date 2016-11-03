package me.arndc.simplesqlbuilder.core;


import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class QueryTest {
    private final static String testTableName = "test_table_name";
    private final static String testColumnName = "test_col_name";
    private final static String testWhereClause = testColumnName + " IS BETWEEN " + 5 + " AND " + 10;


    @Test
    public void testCreatingAQueryWithOneColumn() throws Exception {
        // Arrange
        Query query = new Query();
        query.setSelect(testColumnName);
        query.setFrom(testTableName);
        query.setWhereClause(testWhereClause);

        String expectedQuery = String.format("SELECT %s FROM %s WHERE %s;",
                testColumnName, testTableName, testWhereClause);

        // Act
        String statement = query.statement();

        // Assert
        assertThat(statement, equalTo(expectedQuery));
    }

    @Test
    public void testCreatingAQueryWithMultipleColumns() throws Exception {
            // Arrange
            Query query = new Query();
            query.setSelect(testColumnName, testColumnName);
            query.setFrom(testTableName);
            query.setWhereClause(testWhereClause);

            String expectedQuery = String.format("SELECT %s, %s FROM %s WHERE %s;",
                    testColumnName, testColumnName, testTableName, testWhereClause);

            // Act
            String statement = query.statement();

            // Assert
            assertThat(statement, equalTo(expectedQuery));
    }

    @Test
    public void testCreatingAQueryWithoutWhereClause() throws Exception {
        // Arrange
        Query query = new Query();
        query.setSelect(testColumnName, testColumnName);
        query.setFrom(testTableName);

        String expectedQuery = String.format("SELECT %s, %s FROM %s;",
                testColumnName, testColumnName, testTableName);

        // Act
        String statement = query.statement();

        // Assert
        assertThat(statement, equalTo(expectedQuery));
    }

    @Test
    public void testCreatingADistinctQueryWithOneColumn() throws Exception {
        // Arrange
        Query query = new Query();
        query.setSelect(testColumnName);
        query.setDistinct(true);
        query.setFrom(testTableName);

        String expectedQuery = String.format("SELECT DISTINCT %s FROM %s;",
                testColumnName, testTableName);

        // Act
        String statement = query.statement();

        // Assert
        assertThat(statement, equalTo(expectedQuery));
    }

    @Test
    public void testCreatingAQueryOrderedByAscending() throws Exception {
        // Arrange
        Query query = new Query();
        query.setSelect(testColumnName);
        query.setFrom(testTableName);
        query.setOrderBy(testColumnName, Query.Order.ASCENDING);

        String expectedQuery = String.format("SELECT %s FROM %s ORDER BY %s ASC;",
                testColumnName, testTableName, testColumnName);

        // Act
        String statement = query.statement();

        // Assert
        assertThat(statement, equalTo(expectedQuery));
    }

}
