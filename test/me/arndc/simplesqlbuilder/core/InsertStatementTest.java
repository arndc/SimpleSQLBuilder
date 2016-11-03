package me.arndc.simplesqlbuilder.core;


import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

public class InsertStatementTest {
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
    public void testCreateAnInsertStatement() throws Exception {
        // Assign
        String value1 = "val01", value2 = "val02";
        String expected = "INSERT INTO " + testTableName
                + " (" + String.join(", ", testColumn1.getName(), testColumn2.getName()) + ")"
                + " VALUES (" + String.join(", ", "'" + value1 + "'", "'" + value2 + "'") + ");";

        // Act
        InsertStatement insertStatement = new InsertStatement(testTableName);
        insertStatement.addValue(testColumn1.getName(), value1);
        insertStatement.addValue(testColumn2.getName(), value2);

        String statement = insertStatement.statement();

        // Assert
        MatcherAssert.assertThat(statement, CoreMatchers.equalTo(expected));
    }
}
