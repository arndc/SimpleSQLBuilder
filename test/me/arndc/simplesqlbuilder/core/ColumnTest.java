package me.arndc.simplesqlbuilder.core;


import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ColumnTest {
    private final static String testColumnName = "test_col_name";
    private final static String testColumnDataType = "test_col_data_type";


    @Test
    public void testCreateADefinition() throws Exception {
        // Arrange
        Column column = new Column(testColumnName, testColumnDataType);

        String expectedDefinition = String.format("%s %s",
                testColumnName, testColumnDataType);

        // Act
        String definition = column.createDefinition();

        // Assert
        assertThat(definition, equalTo(expectedDefinition));
    }


    @Test
    public void testCreateADefinitionWithPrimaryKey() throws Exception {
        // Arrange
        Column column = new Column(testColumnName, testColumnDataType);
        column.setPrimaryKey(true);

        String expectedDefinition = String.format("%s %s PRIMARY KEY",
                testColumnName, testColumnDataType);

        // Act
        String definition = column.createDefinition();

        // Assert
        assertThat(definition, equalTo(expectedDefinition));
    }

    @Test
    public void testCreateADefinitionWithPrimaryKeyAndAutoIncrement() throws Exception {
        // Arrange
        Column column = new Column(testColumnName, testColumnDataType);
        column.setPrimaryKey(true);
        column.setAutoIncrement(true);

        String expectedDefinition = String.format("%s %s PRIMARY KEY AUTOINCREMENT",
                testColumnName, testColumnDataType);

        // Act
        String definition = column.createDefinition();

        // Assert
        assertThat(definition, equalTo(expectedDefinition));
    }

    @Test
    public void testCreateADefinitionWithAutoIncrementOnlyShouldNotAddItToTheDefinition() throws Exception {
        // Arrange
        Column column = new Column(testColumnName, testColumnDataType);
        column.setAutoIncrement(true);

        String expectedDefinition = String.format("%s %s",
                testColumnName, testColumnDataType);

        // Act
        String definition = column.createDefinition();

        // Assert
        assertThat(definition, equalTo(expectedDefinition));
    }

    @Test
    public void testCreateADefinitionWithUniqueConstraint() throws Exception {
        // Arrange
        Column column = new Column(testColumnName, testColumnDataType);
        column.setUnique(true);

        String expectedDefinition = String.format("%s %s UNIQUE",
                testColumnName, testColumnDataType);

        // Act
        String definition = column.createDefinition();

        // Assert
        assertThat(definition, equalTo(expectedDefinition));
    }

    @Test
    public void testCreateADefinitionWithAPrimaryKeyAndAUniqueConstraintShouldOnlyContainThePrimaryKey() throws Exception {
        // Arrange
        Column column = new Column(testColumnName, testColumnDataType);
        column.setPrimaryKey(true);
        column.setUnique(true);

        String expectedDefinition = String.format("%s %s PRIMARY KEY",
                testColumnName, testColumnDataType);

        // Act
        String definition = column.createDefinition();

        // Assert
        assertThat(definition, equalTo(expectedDefinition));
    }
}
