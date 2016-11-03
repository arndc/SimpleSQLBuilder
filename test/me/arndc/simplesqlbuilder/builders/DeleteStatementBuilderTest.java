package me.arndc.simplesqlbuilder.builders;

import me.arndc.simplesqlbuilder.core.Column;
import me.arndc.simplesqlbuilder.core.DeleteStatement;
import me.arndc.simplesqlbuilder.core.Operator;
import me.arndc.simplesqlbuilder.core.Table;
import me.arndc.simplesqlbuilder.matchers.DeleteStatementMatcher;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

public class DeleteStatementBuilderTest {
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
    public void testBuildingADeleteStatement() throws Exception {
        // Arrange
        DeleteStatement expected = new DeleteStatement(testTable.getName());
        expected.setWhereClause(testColumn1.is(Operator.between("A", "B")));

        // Act
        DeleteStatementBuilder builder = DeleteStatementBuilder
                .deleteFrom(testTable)
                .where(testColumn1.is(Operator.between("A", "B")));

        DeleteStatement actual = builder.build();

        // Assert
        MatcherAssert.assertThat(actual, DeleteStatementMatcher.isDeleteStatement(expected));
    }

}
