/**
 * File: Credentials.java
 * User: Rich Lopez
 * Date: 13, Oct 2018
 */
package com.demo.db.configuration;

import java.util.Arrays;
import java.util.Objects;

import com.demo.util.PasswordSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * This class is used for credentials.
 * @version 1.0.0
 * @since 1.0.0
 * @author Rich Lopez
 */
public class Credentials {

    /** The user name.*/
    private String username;

    /** The password.*/
    @JsonSerialize(using = PasswordSerializer.class)
    private char[] password;

    /**
     * Constructor.
     */
    public Credentials() {
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Credentials that = (Credentials) o;
        return Objects.equals(username, that.username) &&
                Arrays.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(username);
        result = 31 * result + Arrays.hashCode(password);
        return result;
    }

    /**
     * Gets the user name.
     * @return  the user name.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user name.
     * @param username  the user name.
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Gets the user name.
     * @return  the user name.
     */
    public char[] getPassword() {
        return password;
    }

    /**
     * Sets the password.
     * @param password  the password.
     */
    public void setPassword(final char[] password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Credentials{"
                + "username='" + username + '\''
                + ", password=" + Arrays.toString(password)
                + '}';
    }
}
