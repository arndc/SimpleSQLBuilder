package me.arndc.simplesqlbuilder.builders;

import me.arndc.simplesqlbuilder.core.Column;
import me.arndc.simplesqlbuilder.matchers.ColumnMatcher;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class ColumnBuilderTest {
    private final static String testColumnName = "test_col_name";
    private final static String testColumnDataType = "test_col_data_type";

    @Test
    public void testBuildingAColumn() throws Exception {
        // Arrange
        Column expectedColumn = new Column(testColumnName, testColumnDataType);
        ColumnBuilder builder = ColumnBuilder.newColumn(testColumnName, testColumnDataType);

        // Act
        Column column = builder.build();

        // Assert
        assertThat(column, CoreMatchers.is(ColumnMatcher.isColumn(expectedColumn)));

    }

    @Test
    public void testBuildingAColumnWithAPrimaryKey() throws Exception {
        // Arrange
        Column expectedColumn = new Column(testColumnName, testColumnDataType);
        expectedColumn.setPrimaryKey(true);

        // Act
        ColumnBuilder builder = ColumnBuilder.newColumn(testColumnName, testColumnDataType);
        Column column = builder
                .hasPrimaryKey()
                .build();

        // Assert
        assertThat(column, CoreMatchers.is(ColumnMatcher.isColumn(expectedColumn)));
    }


    @Test
    public void testBuildingAColumnWithAutoIncrementShouldOnlyWorkWhenThePrimaryKeyIsEnabled() throws Exception {
        // Arrange
        Column expectedColumn = new Column(testColumnName, testColumnDataType);
        expectedColumn.setAutoIncrement(false);

        // Act
        ColumnBuilder builder = ColumnBuilder.newColumn(testColumnName, testColumnDataType);
        Column column = builder
                .withAutoIncrement()
                .build();

        // Assert
        assertThat(column, CoreMatchers.is(ColumnMatcher.isColumn(expectedColumn)));
    }

    @Test
    public void testBuildingAColumnWithAutoIncrementWhenThePrimaryKeyIsSetShouldSucceed() throws Exception {
        // Arrange
        Column expectedColumn = new Column(testColumnName, testColumnDataType);
        expectedColumn.setPrimaryKey(true);
        expectedColumn.setAutoIncrement(true);

        // Act
        ColumnBuilder builder = ColumnBuilder.newColumn(testColumnName, testColumnDataType);
        Column column = builder
                .hasPrimaryKey()
                .withAutoIncrement()
                .build();

        // Assert
        assertThat(column, CoreMatchers.is(ColumnMatcher.isColumn(expectedColumn)));
    }

    @Test
    public void testBuildingAColumnWithAPrimaryKeyAndUniqueShouldOnlyContainThePrimaryKey() throws Exception {
        // Arrange
        Column expectedColumn = new Column(testColumnName, testColumnDataType);
        expectedColumn.setPrimaryKey(true);
        expectedColumn.setUnique(false);

        // Act
        ColumnBuilder builder = ColumnBuilder.newColumn(testColumnName, testColumnDataType);
        Column column = builder
                .hasPrimaryKey()
                .withUniqueConstraint()
                .build();

        // Assert
        assertThat(column, CoreMatchers.is(ColumnMatcher.isColumn(expectedColumn)));
    }
}

