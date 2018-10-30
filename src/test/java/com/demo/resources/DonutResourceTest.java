/**
 * File: DonutResourceTest.java
 * User: Rich Lopez
 * Date: 24, Oct 2018
 */
package com.demo.resources;

import com.demo.api.Donut;
import com.demo.db.daos.DonutDAO;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.bson.types.ObjectId;
import org.junit.*;
import org.mockito.Mockito;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Tests class for resource {@link DonutResource}.
 * @version 1.0.0
 * @since 1.0.0
 * @author Rich Lopez 
 */
public class DonutResourceTest {

    private static final DonutDAO dao = Mockito.mock(DonutDAO.class);

    private static final ObjectId objectId = new ObjectId("507f1f77bcf86cd799439011");

    public static final String CONTEXT = "/donuts";

    private static List<Donut> donuts;

    private static Donut donut;

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new DonutResource(dao))
            .build();

    @Before
    public void setUp(){
        donuts = new ArrayList<>();
        donut = new Donut();
        donut.setId(new ObjectId("507f1f77bcf86cd799439011"));
        donut.setPrice(23.00);
        donut.setFlavor("Chocolate");
        donuts.add(donut);
    }

    @After
    public void tearDown(){
        Mockito.reset(dao);
    }

    @Test
    public void test_all(){
        Mockito.when(dao.getAll()).thenReturn(donuts);
        final Response responseTest = resources.target(CONTEXT).request().get();
        Assert.assertNotNull(responseTest);
        Assert.assertEquals(Response.Status.OK.getStatusCode() ,responseTest.getStatus());
    }

    @Test
    public void test_getOne(){
        Mockito.when(dao.getOne(objectId)).thenReturn(donut);
        final Response responseTest = resources.target(CONTEXT).path("/507f1f77bcf86cd799439011").request().get();
        Assert.assertNotNull(responseTest);
        Assert.assertEquals(Response.Status.OK.getStatusCode() ,responseTest.getStatus());
        Assert.assertEquals(donut, responseTest.readEntity(Donut.class));
    }

    @Test
    public void test_save(){
        Mockito.doNothing().when(dao).save(donut);
        final Response responseTest = resources.target(CONTEXT).request().post(Entity.entity(donut, MediaType.APPLICATION_JSON));
        Assert.assertNotNull(responseTest);
        Assert.assertEquals(Response.Status.CREATED.getStatusCode() ,responseTest.getStatus());
        Mockito.verify(dao, Mockito.times(1)).save(donut);
    }

    @Test
    public void test_update(){
        Mockito.doNothing().when(dao).update(objectId, donut);
        final Response responseTest = resources.target(CONTEXT).path("/507f1f77bcf86cd799439011").request().put(Entity.entity(donut, MediaType.APPLICATION_JSON));
        Assert.assertNotNull(responseTest);
        Assert.assertEquals(Response.Status.OK.getStatusCode() ,responseTest.getStatus());
    }

    @Test
    public void test_delete(){
        Mockito.doNothing().when(dao).delete(objectId);
        final Response responseTest = resources.target(CONTEXT).path("/507f1f77bcf86cd799439011").request().delete();
        Assert.assertNotNull(responseTest);
        Assert.assertEquals(Response.Status.OK.getStatusCode() ,responseTest.getStatus());
    }
}
