package me.arndc.simplesqlbuilder.core;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.equalTo;

/**
 * Created by ArneD on 24/10/16.
 */
public class OperatorTest {
    @Test
    public void testOperatorBetweenTwoStrings() throws Exception {
        // Assign
        String val01 = "A", val02 = "B";
        String expected = String.format(" BETWEEN '%s' AND '%s'", val01, val02);

        // Act
        Operator operator = Operator.between(val01, val02);
        String actual = operator.execute();

        // Assert
        MatcherAssert.assertThat(actual, equalTo(expected));
    }

    @Test
    public void testOperatorBetweenTwoDates() throws Exception {
        // Assign
        LocalDate date1 = LocalDate.of(2016, 11, 1);
        LocalDate date2 = LocalDate.of(2017, 12, 2);

        String expected = String.format(" BETWEEN '%s' AND '%s'", "2016-11-01", "2017-12-02");

        // Act
        Operator operator = Operator.between(date1, date2);
        String actual = operator.execute();

        // Assert
        MatcherAssert.assertThat(actual, equalTo(expected));
    }

    @Test
    public void testOperatorEqualsAString() throws Exception {
        // Assign
        String val01 = "A";
        String expected = String.format(" = '%s'", val01);

        // Act
        Operator operator = Operator.equalsTo(val01);
        String actual = operator.execute();

        // Assert
        MatcherAssert.assertThat(actual, equalTo(expected));
    }
}
