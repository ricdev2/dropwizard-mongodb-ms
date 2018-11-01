/**
 * File: DonutTest.java
 * User: Rich Lopez
 * Date: 23, Oct 2018
 */
package com.demo.api;

import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.testing.FixtureHelpers;

/**
 * Test class for {@link Donut} model.
 * @version 1.0.0
 * @since 1.0.0
 * @author Rich Lopez 
 */
public class DonutTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        final Donut donut = new Donut();
        donut.setId(new ObjectId("507f1f77bcf86cd799439011"));
        donut.setPrice(25.00);
        donut.setFlavor("chocolate");

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(FixtureHelpers.fixture("fixtures/donut.json"), Donut.class));

        Assert.assertEquals(expected, MAPPER.writeValueAsString(donut));
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        final Donut donut = new Donut();
        donut.setId(new ObjectId("507f1f77bcf86cd799439011"));
        donut.setPrice(25.00);
        donut.setFlavor("chocolate");

        Assert.assertEquals(MAPPER.readValue(FixtureHelpers.fixture("fixtures/donut.json"), Donut.class), donut);
    }
}
