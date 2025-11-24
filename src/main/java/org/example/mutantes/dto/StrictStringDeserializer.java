package org.example.mutantes.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.IOException;

public class StrictStringDeserializer extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {

        // Si el token NO es un string → 400
        if (!p.currentToken().isScalarValue()) {
            throw new JsonMappingException(p, "Los valores de 'dna' deben ser strings");
        }

        return p.getValueAsString();
    }
}

