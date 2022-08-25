package edu.kit.VorhersagenverwaltungSTA.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.threeten.extra.Interval;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * This class is used to deserialize a {@link Interval} out of a json node.
 * @author Elias Dirks
 */
public class IntervalDeserializer extends StdDeserializer<Interval> {

    private static final String START_TIME_FIELD_NAME = "start";
    private static final String END_TIME_FIELD_NAME = "end";
    private static final String TIME_FIELD_SEPARATOR = "/";
    private static final String ALTERNATIVE_TIME_FIELD_SEPARATOR = ",";

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
        String time = "";
        /* only works if the json fields are named "start" and "end" or if there are no json fields
         */
        if (node.isTextual()) {
            time = node.asText();
            if (time.contains(ALTERNATIVE_TIME_FIELD_SEPARATOR)) {
                time = time.replace(ALTERNATIVE_TIME_FIELD_SEPARATOR, TIME_FIELD_SEPARATOR);
            }
        } else {
            if (node.get(START_TIME_FIELD_NAME) != null && node.get(END_TIME_FIELD_NAME) != null) {
                time = node.get(START_TIME_FIELD_NAME).asText() + TIME_FIELD_SEPARATOR + node.get(END_TIME_FIELD_NAME).asText();
            }
        }
        try {
            return Interval.parse(time);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
