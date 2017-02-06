package me.arndc.simplesqlbuilder.core;


import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

public class UpdateStatementTest {
    private final static String testTableName = "test_col_name";
    private final static String testColumnName = "test_col_name";
    private final static String testColumnDataType = "test_col_data_type";

    private static Column testColumn1, testColumn2;

    @Before
    public void setUp() throws Exception {
        testColumn1 = new Column(testColumnName + "_1", testColumnDataType);
        testColumn2 = new Column(testColumnName + "_2", testColumnDataType);
    }

    @Test
    public void testCreateUpdateStatementForOneColumn() throws Exception {
        // Assign
        String value = "val";
        String expected = "UPDATE " + testTableName
                + " SET " + testColumn1.getName() + " = '" + value + "'"
                + " WHERE " + testColumn1.getName() + " BETWEEN 'A' AND 'B';";

        // Act
        UpdateStatement deleteStatement = new UpdateStatement(testTableName);
        deleteStatement.addSetter(testColumn1.getName(), value);
        deleteStatement.setWhereClause(testColumn1.is(Operator.between("A", "B")));

        String statement = deleteStatement.statement();

        // Assert
        MatcherAssert.assertThat(statement, CoreMatchers.equalTo(expected));
    }

    @Test
    public void testCreateUpdateStatementForMultipleColumns() throws Exception {
        // Assign
        String value = "val";

        String expected = "UPDATE " + testTableName
                + " SET " + testColumn1.getName() + " = '" + value + "', " + testColumn2
                .getName() + " = '" + value + "'"
                + " WHERE " + testColumn1.getName() + " BETWEEN 'A' AND 'B';";

        // Act
        UpdateStatement deleteStatement = new UpdateStatement(testTableName);
        deleteStatement.addSetter(testColumn1.getName(), value);
        deleteStatement.addSetter(testColumn2.getName(), value);
        deleteStatement.setWhereClause(testColumn1.is(Operator.between("A", "B")));

        String statement = deleteStatement.statement();

        // Assert
        MatcherAssert.assertThat(statement, CoreMatchers.equalTo(expected));
    }
}
