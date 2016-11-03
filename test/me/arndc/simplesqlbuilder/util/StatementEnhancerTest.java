package me.arndc.simplesqlbuilder.util;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;

/**
 * Created by ArneD on 27/10/16.
 */
public class StatementEnhancerTest {
    @Test
    public void testTrimAStatementWithMultipleConsecutiveWhiteSpaces() throws Exception {
        // Assign
        String statement = " SELECT columnName  FROM     tableName   WHERE   columnName   =  'test';";
        String expectedStatement = " SELECT columnName FROM tableName WHERE columnName = 'test';";

        // Act
        String actualStatement = StatementEnhancer.trim(statement);

        // Assert
        MatcherAssert.assertThat(actualStatement, equalTo(expectedStatement));
    }

    @Test
    public void testEscapeAStringValue() throws Exception {
        // Assign
        String value = "test";
        String expectedValue = "'test'";

        // Act
        String actualValue = StatementEnhancer.escapeValue(value);

        // Assert
        MatcherAssert.assertThat(actualValue, equalTo(expectedValue));
    }

    @Test
    public void testEscapeAStringValueContainingAnApostrophe() throws Exception {
        // Assign
        String value = "test's";
        String expectedValue = "'test''s'";

        // Act
        String actualValue = StatementEnhancer.escapeValue(value);

        // Assert
        MatcherAssert.assertThat(actualValue, equalTo(expectedValue));
    }

    @Test
    public void testEscapeAIntegerValue() throws Exception {
        // Assign
        int value = 3;
        String expectedValue = "3";

        // Act
        String actualValue = StatementEnhancer.escapeValue(value);

        // Assert
        MatcherAssert.assertThat(actualValue, equalTo(expectedValue));
    }

    @Test
    public void testEscapeADateValue() throws Exception {
        // Assign
        LocalDate date =  LocalDate.of(2000, 12, 31);
        Date value = Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        String expectedValue = "'2000-12-31'";

        // Act
        String actualValue = StatementEnhancer.escapeValue(value);

        // Assert
        MatcherAssert.assertThat(actualValue, equalTo(expectedValue));
    }

    @Test
    public void testEscapeALocalDateValue() throws Exception {
        // Assign
        LocalDate value =  LocalDate.of(2000, 12, 31);
        String expectedValue = "'2000-12-31'";

        // Act
        String actualValue = StatementEnhancer.escapeValue(value);

        // Assert
        MatcherAssert.assertThat(actualValue, equalTo(expectedValue));
    }

    @Test
    public void testEscapeACustomObject() throws Exception {
        // Assign
        class CustomObject {
            private String customText;

            public CustomObject(String customText) {
                this.customText = customText;
            }

            @Override
            public String toString() {
                return "customText=" + customText;
            }
        }

        CustomObject customObject = new CustomObject("custom");
        String expectedValue = "'customText=custom'";

        // Act
        String actualValue = StatementEnhancer.escapeValue(customObject);

        // Assert
        MatcherAssert.assertThat(actualValue, equalTo(expectedValue));
    }
}
