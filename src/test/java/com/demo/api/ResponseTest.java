/**
 * File: ResponseTest.java
 * User: Rich Lopez
 * Date: 23, Oct 2018
 */
package com.demo.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.testing.FixtureHelpers;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link Response}.
 * @version 1.0.0
 * @since 1.0.0
 * @author Rich Lopez 
 */
public class ResponseTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        final Response response = new Response("Donuts not found.");

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(FixtureHelpers.fixture("fixtures/response.json"), Response.class));

        Assert.assertEquals(expected, MAPPER.writeValueAsString(response));
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        final Response response = new Response("Donuts not found.");

        Assert.assertEquals(MAPPER.readValue(FixtureHelpers.fixture("fixtures/response.json"), Response.class), response);
    }
}
