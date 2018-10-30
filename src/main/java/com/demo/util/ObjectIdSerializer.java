/**
 * File: ObjectIdSerializer.java
 * User: Rich Lopez
 * Date: 19, Oct 2018
 */
package com.demo.util;

import java.io.IOException;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;


/**
 * Serializer for ObjectId to String.
 *
 * @author Rich Lopez
 * @version 1.0.0
 * @since 1.0.0
 */
public class ObjectIdSerializer extends JsonSerializer<ObjectId> {
    @Override
    public void serialize(final ObjectId objectId, final JsonGenerator jsonGenerator,
                          final SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(objectId.toString());
    }
}
