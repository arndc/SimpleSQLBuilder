package me.arndc.simplesqlbuilder.core;


import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ArithmeticColumnOperationTest {
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
    public void testMultiplyingTwoColumns() throws Exception {
        // Arrange
        String expectedOperation = String.format("%s * %s",
                testColumn1.getName(), testColumn2.getName());

        // Act
        String actualOperation1 = ArithmeticColumnOperation.multiply(testColumn1, testColumn2);
        String actualOperation2 = ArithmeticColumnOperation.multiply(testColumn1.getName(), testColumn2.getName());

        // Assert
        assertThat(actualOperation1, equalTo(expectedOperation));
        assertThat(actualOperation2, equalTo(expectedOperation));

    }

    @Test
    public void testMultiplyingMoreThanTwoColumns() throws Exception {
        // Arrange
        String expectedOperation = String.format("%s * %s * %s",
                testColumn1.getName(), testColumn3.getName(), testColumn2.getName());

        // Act
        String actualOperation1 = ArithmeticColumnOperation.multiply(testColumn1, testColumn3, testColumn2);
        String actualOperation2 = ArithmeticColumnOperation.multiply(testColumn1.getName(), testColumn3.getName(), testColumn2.getName());

        // Assert
        assertThat(actualOperation1, equalTo(expectedOperation));
        assertThat(actualOperation2, equalTo(expectedOperation));
    }
}
