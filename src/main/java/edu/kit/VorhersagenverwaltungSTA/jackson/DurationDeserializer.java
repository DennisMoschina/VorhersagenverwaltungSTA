package edu.kit.VorhersagenverwaltungSTA.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.threeten.extra.Interval;

import java.io.IOException;
import java.time.Duration;

/**
 * This class is used to deserialize a {@link Duration} out of a json node.
 * @author Elias Dirks
 */
public class DurationDeserializer extends StdDeserializer<Duration> {

    public DurationDeserializer() {
        this(null);
    }

    public DurationDeserializer(Class<?> valueClass) {
        super(valueClass);
    }

    @Override
    public Duration deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        return Duration.parse(node.asText());
    }
}
