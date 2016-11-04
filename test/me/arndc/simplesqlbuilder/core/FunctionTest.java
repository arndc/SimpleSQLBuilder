package me.arndc.simplesqlbuilder.core;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class FunctionTest {
    private final static String testColumnName = "test_col_name";

    private static Column testColumn;

    @Before
    public void setUp() throws Exception {
        testColumn = new Column(testColumnName, "test_data_type");
    }

    @Test
    public void testTakingTheAverageOfAColumn() throws Exception {
        // Assign
        String expected = String.format("avg(%s)", testColumn.getName());

        // Act
        String actual = Function.avg(testColumn);

        // Assert
        MatcherAssert.assertThat(actual, equalTo(expected));
    }

    @Test
    public void testTakingTheCountOfAColumn() throws Exception {
        // Assign
        String expected = String.format("count(%s)", testColumn.getName());

        // Act
        String actual = Function.count(testColumn);

        // Assert
        MatcherAssert.assertThat(actual, equalTo(expected));
    }

    @Test
    public void testTakingTheMaximumOfAColumn() throws Exception {
        // Assign
        String expected = String.format("max(%s)", testColumn.getName());

        // Act
        String actual = Function.max(testColumn);

        // Assert
        MatcherAssert.assertThat(actual, equalTo(expected));
    }

    @Test
    public void testTakingTheMinimumOfAColumn() throws Exception {
        // Assign
        String expected = String.format("min(%s)", testColumn.getName());

        // Act
        String actual = Function.min(testColumn);

        // Assert
        MatcherAssert.assertThat(actual, equalTo(expected));
    }

    @Test
    public void testTakingTheSumOfAColumn() throws Exception {
        // Assign
        String expected = String.format("sum(%s)", testColumn.getName());

        // Act
        String actual = Function.sum(testColumn);

        // Assert
        MatcherAssert.assertThat(actual, equalTo(expected));
    }
}
