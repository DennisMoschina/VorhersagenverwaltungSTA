package edu.kit.VorhersagenverwaltungSTA.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

/**
 * This class is used to deserialize a {@link Instant} out of a json node.
 * @author Elias Dirks
 */
public class InstantDeserializer extends StdDeserializer<Instant> {

    public InstantDeserializer() {
        this(null);
    }

    public InstantDeserializer(Class<?> valueClass) {
        super(valueClass);
    }

    @Override
    public Instant deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        return Instant.parse(node.asText());
    }
}
