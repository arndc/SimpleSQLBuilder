package me.arndc.simplesqlbuilder.builders;

import me.arndc.simplesqlbuilder.core.Column;
import me.arndc.simplesqlbuilder.core.Table;
import me.arndc.simplesqlbuilder.matchers.TableMatcher;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TableBuilderTest {

    private final static String testTableName = "test_table_name";
    private final static String testColumnName = "test_col_name";
    private final static String testColumnDataType = "test_col_data_type";

    private static Column testColumn1, testColumn2, testColumn3;

    @Before
    public void setUp() throws Exception {
        testColumn1 = new Column(testColumnName + "_1", testColumnDataType);
        testColumn2 = new Column(testColumnName + "_2", testColumnDataType);
        testColumn3 = new Column(testColumnName + "_3", testColumnDataType);
    }

    @Test
    public void testBuildingATableWithOneColumn() throws Exception {
        // Assign
        Table expectedTable = new Table(testTableName);
        expectedTable.addColumn(testColumn1);

        // Act
        TableBuilder builder = TableBuilder.newTable(testTableName);
        Table table = builder.withColumn(ColumnBuilder.newColumn(testColumnName, testColumnDataType)).build();

        // Assert
        assertThat(table, is(TableMatcher.isTable(expectedTable)));
    }

    @Test
    public void testBuildingATableWithMultipleColumn() throws Exception {
        // Assign
        Table expectedTable = new Table(testTableName);
        expectedTable.addColumn(testColumn1, testColumn2, testColumn3);

        // Act
        TableBuilder builder = TableBuilder.newTable(testTableName);
        Table table = builder
                .withColumn(ColumnBuilder.newColumn(testColumn1.getName(), testColumn1.getDataType()))
                .withColumn(ColumnBuilder.newColumn(testColumn2.getName(), testColumn2.getDataType()))
                .withColumn(ColumnBuilder.newColumn(testColumn3.getName(), testColumn3.getDataType()))
                .build();

        // Assert
        assertThat(table, is(TableMatcher.isTable(expectedTable)));
    }
}
