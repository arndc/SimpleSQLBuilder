package me.arndc.simplesqlbuilder.core;


import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

public class DeleteStatementTest {
    private final static String testTableName = "test_col_name";
    private final static String testColumnName = "test_col_name";
    private final static String testColumnDataType = "test_col_data_type";

    private static Column testColumn;

    @Before
    public void setUp() throws Exception {
        testColumn = new Column(testColumnName, testColumnDataType);
    }

    @Test
    public void testCreateADeleteStatement() throws Exception {
        // Assign
        String expected = "DELETE FROM " + testTableName
                + " WHERE " + testColumn.getName() + " BETWEEN 'A' AND 'B';";

        // Act
        DeleteStatement deleteStatement = new DeleteStatement(testTableName);
        deleteStatement.setWhereClause(testColumn.is(Operator.between("A", "B")));

        String statement = deleteStatement.statement();

        // Assert
        MatcherAssert.assertThat(statement, CoreMatchers.equalTo(expected));
    }
}
