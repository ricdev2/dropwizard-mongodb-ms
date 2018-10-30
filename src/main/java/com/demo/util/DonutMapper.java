/**
 * File: DonutMapper.java
 * User: Rich Lopez
 * Date: 17, Oct 2018
 */
package com.demo.util;

import com.demo.api.Donut;
import org.bson.Document;

/**
 * Mapper class for Donuts objects.
 *
 * @author Rich Lopez
 * @version 1.0.0
 * @since 1.0.0
 */
public class DonutMapper {

    /**
     * Map objects {@link Document} to {@link Donut}.
     *
     * @param donutDocument the information document.
     * @return A object {@link Donut}.
     */
    public static Donut map(final Document donutDocument) {
        final Donut donut = new Donut();
        donut.setId(donutDocument.getObjectId("_id"));
        donut.setFlavor(donutDocument.getString("flavor"));
        donut.setPrice(donutDocument.getDouble("price"));
        return donut;
    }
}
