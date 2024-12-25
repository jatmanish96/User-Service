package com.ecp.us.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public class BooleanDeserializerUtils extends JsonDeserializer<Boolean> {

    @Override
    public Boolean deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getText();
        if (value == null || value.isEmpty()) {
            return null; // Allow null if no value is provided
        }
        return value.equalsIgnoreCase("true"); // Convert "true"/"false" string to Boolean
    }
}
