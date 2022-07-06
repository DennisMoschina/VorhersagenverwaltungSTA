package edu.kit.VorhersagenverwaltungSTA.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.threeten.extra.Interval;

import java.io.IOException;
import java.time.Duration;

/**
 * This class is used to deserialize a {@link Interval} out of a json node.
 * @author Elias Dirks
 */
public class IntervalDeserializer extends StdDeserializer<Interval> {

    public IntervalDeserializer() {
        this(null);
    }

    public IntervalDeserializer(Class<?> valueClass) {
        super(valueClass);
    }

    @Override
    public Interval deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        return Interval.parse(node.asText());
    }
}
