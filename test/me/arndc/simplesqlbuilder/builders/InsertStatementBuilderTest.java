package me.arndc.simplesqlbuilder.builders;

import me.arndc.simplesqlbuilder.core.Column;
import me.arndc.simplesqlbuilder.core.InsertStatement;
import me.arndc.simplesqlbuilder.core.Table;
import me.arndc.simplesqlbuilder.matchers.InsertStatementMatcher;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

public class InsertStatementBuilderTest {
    private final static String testTableName = "test_table_name";
    private final static String testColumnName = "test_col_name";

    private static Table testTable;
    private static Column testColumn1, testColumn2;

    @Before
    public void setUp() throws Exception {
        testTable = new Table(testTableName);

        testColumn1 = new Column(testColumnName + "_1", "dummy_data_type");
        testColumn2 = new Column(testColumnName + "_2", "dummy_data_type");
    }

    @Test
    public void testBuildingAnInsertStatement() throws Exception {
        // Arrange
        String value1 = "val01", value2 = "val02";
        InsertStatement expected = new InsertStatement(testTable.getName());
        expected.addValue(testColumn1.getName(), value1);
        expected.addValue(testColumn2.getName(), value2);

        // Act
        InsertStatementBuilder builder = InsertStatementBuilder
                .insertInto(testTable)
                .withValue(testColumn1, value1)
                .withValue(testColumn2, value2);

        InsertStatement actual = builder.build();

        // Assert
        MatcherAssert.assertThat(actual, InsertStatementMatcher.isInsertStatement(expected));
    }

}
