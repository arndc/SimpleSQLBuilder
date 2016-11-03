package me.arndc.simplesqlbuilder.core;


import me.arndc.simplesqlbuilder.matchers.ColumnMatcher;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TableTest {
    private final static String testTableName = "test_table_name";
    private final static String testColumnName = "test_col_name";
    private final static String testColumnDataType = "test_col_data_type";

    private static Column testColumn1, testColumn2;

    @Before
    public void setUp() throws Exception {
        testColumn1 = new Column(testColumnName + "_1", testColumnDataType);
        testColumn2 = new Column(testColumnName + "_2", testColumnDataType);
    }

    @Test
    public void testCreateShouldReturnAFormattedCreateTableStatement() throws Exception {
        // Arrange
        Table table = new Table(testTableName);
        table.addColumn(testColumn1, testColumn2);
        testColumn1.setPrimaryKey(true);
        testColumn1.setAutoIncrement(true);
        String expectedCreateStatement = String.format("CREATE TABLE %s(%s %s PRIMARY KEY AUTOINCREMENT, %s %s);",
                testTableName,
                testColumn1.getName(), testColumn1.getDataType(),
                testColumn2.getName(), testColumn2.getDataType());

        // Act
        String createStatement = table.createStatement();

        // Assert
        assertThat(createStatement, equalTo(expectedCreateStatement));
    }

    @Test
    public void testDropTableShouldReturnAFormattedDropTableStatement() throws Exception {
        // Arrange
        Table table = new Table(testTableName);
        String expectedDropStatement = String.format("DROP TABLE IF EXISTS %s;", testTableName);

        // Act
        String dropStatement = table.dropStatement();

        // Assert
        assertThat(dropStatement, equalTo(expectedDropStatement));
    }

    @Test
    public void testAddingMultipleColumnsAtOnce() throws Exception {
        // Arrange
        Table table = new Table(testTableName);

        // Act
        table.addColumn(testColumn1, testColumn2);

        // Assert
        assertThat(table.getColumns().size(), is(2));
    }

    @Test
    public void testRetrieveColumnWithColumnNameAsKey() throws Exception {
        // Arrange
        Table table = new Table(testTableName);
        table.addColumn(testColumn1, testColumn2);

        // Act
        Column retrievedColumn = table.getColumn(testColumn2.getName());

        // Assert
        assertThat(testColumn2, ColumnMatcher.isColumn(retrievedColumn));
    }
}
