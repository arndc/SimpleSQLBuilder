package me.arndc.simplesqlbuilder.builders;

import me.arndc.simplesqlbuilder.core.Column;
import me.arndc.simplesqlbuilder.core.Operator;
import me.arndc.simplesqlbuilder.core.Table;
import me.arndc.simplesqlbuilder.core.UpdateStatement;
import me.arndc.simplesqlbuilder.matchers.UpdateStatementMatcher;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

public class UpdateStatementBuilderTest {
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
    public void testBuildingAUpdateStatement() throws Exception {
        // Assign
        String value = "val";

        UpdateStatement expected = new UpdateStatement(testTable.getName());
        expected.addSetter(testColumn1.getName(), value);
        expected.addSetter(testColumn2.getName(), value);
        expected.setWhereClause(testColumn1.is(Operator.between("A", "B")));

        // Act
        UpdateStatementBuilder builder = UpdateStatementBuilder
                .updateFrom(testTable)
                .set(testColumn1, value)
                .set(testColumn2, value)
                .where(testColumn1.is(Operator.between("A", "B")));

        UpdateStatement actual = builder.build();

        // Assert
        MatcherAssert.assertThat(actual, UpdateStatementMatcher.isUpdateStatement(expected));
    }

}
