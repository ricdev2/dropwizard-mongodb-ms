/**
 * File: DonutClientTest.java
 * User: Rich Lopez
 * Date: 27, Oct 2018
 */
package com.demo.client;

import com.demo.api.Donut;
import io.dropwizard.testing.junit.DropwizardClientRule;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

/**
 * Unit test for {@link DonutClient}.
 * @version 1.0.0
 * @since 1.0.0
 * @author Rich Lopez 
 */
public class DonutClientTest {

    private static final Donut donut;

    public static final String ID_DONUT = "507f1f77bcf86cd799439011";

    static {
        donut = new Donut();
        donut.setId(new ObjectId(ID_DONUT));
        donut.setFlavor("Strawberry");
        donut.setPrice(12.00);
    }


    @Path("/donuts")
    @Produces(MediaType.APPLICATION_JSON)
    public static class DonutResource {

        @GET
        public List<Donut> all(){
            return Arrays.asList(donut);
        }

        @GET
        @Path("/{id}")
        public Donut getOne(@PathParam("id") @NotNull final String id) {
            if (id.equals(ID_DONUT)) {
                final Donut donut = new Donut();
                donut.setId(new ObjectId(ID_DONUT));
                donut.setFlavor("Strawberry");
                donut.setPrice(12.00);
                return donut;
            } else {
                return null;
            }
        }

        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        public void save(final Donut donut) {
            if (donut == null) {
                throw new IllegalArgumentException("Information donut not valid.");
            }
        }

        @PUT
        @Path("/{id}")
        public void update(@PathParam("id") @NotNull final ObjectId id, @NotNull final Donut donut) {
            if (id != null) {
                //process the update.
            } else {
                throw new IllegalArgumentException("The information for update can not be null.");
            }
        }

        @DELETE
        @Path("/{id}")
        public void delete(@PathParam("id") @NotNull final String id) {
            if (id == null) {
                throw new IllegalArgumentException("Information donut not valid.");
            }
        }
    }

    @ClassRule
    public static final DropwizardClientRule DROPWIZARD_CLIENT_RULE = new DropwizardClientRule(new DonutResource());

    @Test
    public void test_all(){
        final DonutClient donutClient = new DonutClient(ClientBuilder.newClient(),DROPWIZARD_CLIENT_RULE.baseUri()+"/donuts");
        final List<Donut> donuts = donutClient.all();
        Assert.assertNotNull(donuts);
        Assert.assertFalse(donuts.isEmpty());
    }

    @Test
    public void test_getOne() {
        final DonutClient donutClient = new DonutClient(ClientBuilder.newClient(),DROPWIZARD_CLIENT_RULE.baseUri()+"/donuts");
        final Donut donut = donutClient.getOne(ID_DONUT);
        Assert.assertNotNull(donut);
    }

    @Test
    public void test_save() {
        final DonutClient donutClient = new DonutClient(ClientBuilder.newClient(),DROPWIZARD_CLIENT_RULE.baseUri()+"/donuts");
        try {
            donutClient.save(donut);
        } catch (final Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void update() {
        final DonutClient donutClient = new DonutClient(ClientBuilder.newClient(),DROPWIZARD_CLIENT_RULE.baseUri()+"/donuts");
        try {
            donutClient.update(ID_DONUT, donut);
        } catch (final Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void delete() {
        final DonutClient donutClient = new DonutClient(ClientBuilder.newClient(),DROPWIZARD_CLIENT_RULE.baseUri()+"/donuts");
        try {
            donutClient.delete(ID_DONUT);
        } catch (final Exception e) {

            Assert.fail();
        }

    }
}
