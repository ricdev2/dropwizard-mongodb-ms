/**
 * File: DonutResource.java
 * User: Rich Lopez
 * Date: 14, Oct 2018
 */
package com.demo.resources;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.types.ObjectId;

import com.demo.api.Donut;
import com.demo.db.daos.DonutDAO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class has receive all REST request and process it.
 *
 * @author Rich Lopez
 * @version 1.0.0
 * @since 1.0.0
 */
@Api(value = "donuts",
     description = "Donuts REST API for handle Donuts CRUD operations on donuts collection.",
     tags = {"donuts"})
@Path("/donuts")
@Produces(MediaType.APPLICATION_JSON)
public class DonutResource {

    /**
     * Logger class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DonutResource.class);

    /**
     * DAO donut.
     */
    private DonutDAO donutDAO;

    /**
     * Constructor.
     *
     * @param donutDAO the dao donut.
     */
    public DonutResource(final DonutDAO donutDAO) {
        this.donutDAO = donutDAO;
    }

    /**
     * Get all {@link Donut} objects.
     *
     * @return A object {@link Response} with the information of result this method.
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation success."),
            @ApiResponse(code = 404, message = "Donuts not found")
    })
    @GET
    public Response all() {
        LOGGER.info("List all Donuts.");
        final List<Donut> donutsFind = donutDAO.getAll();
        if (donutsFind.isEmpty()) {
            return Response.accepted(new com.demo.api.Response("Donuts not found."))
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        return Response.ok(donutsFind).build();
    }

    /**
     * Get a {@link Donut} by identifier.
     *
     * @param id the identifier.
     * @return A object {@link Response} with the information of result this method.
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation success."),
            @ApiResponse(code = 404, message = "Donuts not found")
    })
    @GET
    @Path("/{id}")
    public Response getOne(@ApiParam(value = "id") @PathParam("id") @NotNull final ObjectId id) {
        LOGGER.info("Find the donut by identifier : " + id.toString());
        final Donut donut = donutDAO.getOne(id);
        if (donut != null) {
            return Response.ok(donut).build();
        }
        return Response.accepted(new com.demo.api.Response("Donut not found.")).build();
    }

    /**
     * Persis a {@link Donut} object in a collection.
     *
     * @param donut The objecto to persist.
     * @return A object {@link Response} with the information of result this method.
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation success.")
    })
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@ApiParam(value = "Donut") @NotNull final Donut donut) {
        LOGGER.info("Persist a donut in collection with the information: {}", donut);
        donutDAO.save(donut);
        return Response.status(Response.Status.CREATED).build();
    }

    /**
     * Update the information of a {@link Donut}.
     *
     * @param id    The identifier.
     * @param donut the donut information.
     * @return A object {@link Response} with the information of result this method.
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation success.")
    })
    @PUT
    @Path("/{id}")
    public Response update(@ApiParam(value = "id") @PathParam("id") @NotNull final ObjectId id,
                           @ApiParam(value = "Donut") @NotNull final Donut donut) {
        LOGGER.info("Update the information of a donut : {} ", donut);
        donutDAO.update(id, donut);
        return Response.ok().build();
    }

    /**
     * Delete a {@link Donut} object.
      * @param id   the identifier.
     * @return  A object {@link Response} with the information of result this method.
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation success.")
    })
    @DELETE
    @Path("/{id}")
    public Response delete(@ApiParam(value = "id") @PathParam("id") @NotNull final ObjectId id) {
        LOGGER.info("Delete a donut from collection with identifier: " + id.toString());
        donutDAO.delete(id);
        return Response.ok().build();
    }
}
