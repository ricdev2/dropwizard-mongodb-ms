/**
 * File: Seed.java
 * User: Rich Lopez
 * Date: 14, Oct 2018
 */
package com.demo.db.configuration;

import java.util.Objects;

/**
 * This class is used for represent a server of MongoDB.
 * @version 1.0.0
 * @since 1.0.0
 * @author Rich Lopez
 */
public class Seed {

    /** The host.*/
    private String host;

    /** The port.*/
    private int port;

    /**
     * Constructor.
     */
    public Seed() {
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Seed seed = (Seed) o;
        return port == seed.port &&
                Objects.equals(host, seed.host);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, port);
    }

    /**
     * Gets the host.
     * @return  the host.
     */
    public String getHost() {
        return host;
    }

    /**
     * Sets the host.
     * @param host  the host.
     */
    public void setHost(final String host) {
        this.host = host;
    }

    /**
     * Gets the port.
     * @return  the port.
     */
    public int getPort() {
        return port;
    }

    /**
     * Sets the port.
     * @param port  the
     */
    public void setPort(final int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "Seed{" +
                "host='" + host + '\'' +
                ", port=" + port +
                '}';
    }
}
