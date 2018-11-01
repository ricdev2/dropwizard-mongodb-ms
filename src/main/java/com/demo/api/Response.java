/**
 * File: Response.java
 * User: Rich Lopez
 * Date: 19, Oct 2018
 */
package com.demo.api;

import java.util.Objects;

/**
 * The class used for response message.
 * @version 1.0.0
 * @since 1.0.0
 * @author Rich Lopez 
 */
public class Response {

    /**
     * The message.
     */
    private String message;

    /**
     * Constructor.
     */
    public Response() {
    }

    /**
     * Constructor.
     * @param message the message.
     */
    public Response(final String message) {
        this.message = message;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) return false;
        final Response response = (Response) o;
        return Objects.equals(message, response.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    /**
     * Gets the message.
     *
     * @return the value message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message.
     *
     * @param message value.
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Response{"
                + "message='" + message + '\''
                + '}';
    }
}
