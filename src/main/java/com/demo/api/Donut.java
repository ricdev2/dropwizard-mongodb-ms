/**
 * File: Donut.java
 * User: Rich Lopez
 * Date: 13, Oct 2018
 */
package com.demo.api;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

import org.bson.types.ObjectId;

import com.demo.util.ObjectIdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * This class has the porpuse of be a business model.
 * @version 1.0.0
 * @since 1.0.0
 * @author Rich Lopez
 */
public class Donut implements Serializable {

    /** The id.*/
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId id;

    /** The price. */
    @NotNull
    private double price;

    /** The principal flavor.*/
    @NotNull
    private String flavor;

    /**
     * Constructor.
     */
    public Donut() {
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Donut donut = (Donut) o;
        return Double.compare(donut.price, price) == 0 &&
                Objects.equals(id, donut.id) &&
                Objects.equals(flavor, donut.flavor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, flavor);
    }

    /**
     * Gets the id.
     *
     * @return the value id.
     */
    public ObjectId getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id value.
     */
    public void setId(final ObjectId id) {
        this.id = id;
    }

    /**
     * Gets the price.
     *
     * @return the value price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price.
     *
     * @param price value.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the flavor.
     *
     * @return the value flavor.
     */
    public String getFlavor() {
        return flavor;
    }

    /**
     * Sets the flavor.
     *
     * @param flavor value.
     */
    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    @Override
    public String toString() {
        return "Donut{"
                + "id=" + id
                + ", price=" + price
                + ", flavor='" + flavor + '\''
                + '}';
    }
}
