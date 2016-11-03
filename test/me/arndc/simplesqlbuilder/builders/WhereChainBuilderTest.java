package me.arndc.simplesqlbuilder.builders;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

public class WhereChainBuilderTest {

    @Test
    public void testBuildingAWhereChain() throws Exception {
        // Arrange
        String expectedWhereClause = "column = \"test\""
                .concat(" AND ")
                .concat("column IS BETWEEN \"A\" AND \"B\"")
                .concat(" OR ")
                .concat("column IS NOT NULL");

        // Act
        WhereChainBuilder whereClause = WhereChainBuilder
                .whereChain("column = \"test\"")
                .and("column IS BETWEEN \"A\" AND \"B\"")
                .or("column IS NOT NULL");

        // Assert
        MatcherAssert.assertThat(whereClause.end(), CoreMatchers.equalTo(expectedWhereClause));
    }
}

