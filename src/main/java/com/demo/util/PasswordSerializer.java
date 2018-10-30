/**
 * File: PasswordSerializer.java
 * User: Rich Lopez
 * Date: 19, Oct 2018
 */
package com.demo.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Serializer for String to char[].
 *
 * @author Rich Lopez
 * @version 1.0.0
 * @since 1.0.0
 */
public class PasswordSerializer extends JsonSerializer<String> {
    @Override
    public void serialize(final String input, final JsonGenerator jsonGenerator,
                          final SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(input.toCharArray(), 0, input.toCharArray().length);
    }

    @Override
    public Class<String> handledType() {
        return String.class;
    }
}
