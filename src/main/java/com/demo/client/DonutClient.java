/**
 * File: DonutClient.java
 * User: Rich Lopez
 * Date: 17, Oct 2018
 */
package com.demo.client;

import com.demo.api.Donut;

import java.util.Arrays;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * This class serve the purpose of client jersey for this API.
 *
 * @author Rich Lopez
 * @version 1.0.0
 * @since 1.0.0
 */
public class DonutClient {

    /**
     * Client to connect.
     */
    private Client client;

    /**
     * Base of URL to connect.
     */
    private String basePath;

    /**
     * Constructor.
     *
     * @param client   the client jersey.
     * @param basePath the base path.
     */
    public DonutClient(final Client client, final String basePath) {
        this.client = client;
        this.basePath = basePath;
    }

    /**
     * Get all {@link Donut} objects.
     *
     * @return A list of {@link Donut} in other case null.
     */
    public List<Donut> all() {
        final WebTarget webTarget = client.target(basePath);
        final Invocation.Builder builder = webTarget.request();
        final Response response = builder.accept(MediaType.APPLICATION_JSON).get();

        if (Response.Status.OK.getStatusCode() == response.getStatus()) {
            return Arrays.asList(response.readEntity(Donut[].class));
        }
        return null;
    }

    /**
     * Get a {@link Donut} object.
     *
     * @param id the identifier
     * @return A object {@link Donut} or null in case not found.
     */
    public Donut getOne(final String id) {
        final WebTarget webTarget = client.target(basePath).path("/").path(id);
        final Invocation.Builder builder = webTarget.request();
        final Response response = builder.accept(MediaType.APPLICATION_JSON).get();

        if (Response.Status.OK.getStatusCode() == response.getStatus()) {
            return response.readEntity(Donut.class);
        }
        return null;
    }

    /**
     * Persist a object of type {@link Donut}.
     *
     * @param donut the donut.
     * @throws Exception when can not save.
     */
    public void save(final Donut donut) throws Exception {
        final WebTarget webTarget = client.target(basePath);
        final Invocation.Builder builder = webTarget.request();
        final Response response = builder.accept(MediaType.APPLICATION_JSON)
                .post(Entity.entity(donut, MediaType.APPLICATION_JSON));

        if (Response.Status.NO_CONTENT.getStatusCode() != response.getStatus()) {
            throw new Exception("Can not save Donut.");
        }
    }

    /**
     * Update the information about a {@link Donut}.
     *
     * @param id    the identifier.
     * @param donut the donut information.
     * @throws Exception when can not update the Dounut object.
     */
    public void update(final String id, Donut donut) throws Exception {
        final WebTarget webTarget = client.target(basePath).path("/").path(id);
        final Invocation.Builder builder = webTarget.request();
        final Response response = builder.accept(MediaType.APPLICATION_JSON)
                .put(Entity.entity(donut, MediaType.APPLICATION_JSON));

        if (Response.Status.NO_CONTENT.getStatusCode() != response.getStatus()) {
            throw new Exception("Can not save Donut.");
        }
    }

    /**
     * Delete a {@link Donut} the donut object.
     *
     * @param id the identifier of Donut.
     */
    public void delete(final String id) throws Exception {
        final WebTarget webTarget = client.target(basePath).path("/").path(id);
        final Invocation.Builder builder = webTarget.request();
        final Response response = builder.accept(MediaType.APPLICATION_JSON)
                .delete();

        if (Response.Status.NO_CONTENT.getStatusCode() != response.getStatus()) {
            throw new Exception("Can not save Donut.");
        }
    }

}
