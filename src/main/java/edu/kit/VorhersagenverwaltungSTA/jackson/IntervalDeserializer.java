package edu.kit.VorhersagenverwaltungSTA.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.threeten.extra.Interval;

import java.io.IOException;

public class IntervalDeserializer extends StdDeserializer<Interval> {

    public IntervalDeserializer() {
        this(null);
    }

    public IntervalDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Interval deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        return Interval.parse(node.asText());
    }
}
