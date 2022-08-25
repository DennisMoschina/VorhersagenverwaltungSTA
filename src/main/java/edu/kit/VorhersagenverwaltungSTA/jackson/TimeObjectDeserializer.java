package edu.kit.VorhersagenverwaltungSTA.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.TimeObject;

import java.io.IOException;

/**
 * This class is used to deserialize a {@link TimeObject} out of a json node.
 *
 * @author Elias Dirks
 */
public class TimeObjectDeserializer extends StdDeserializer<TimeObject> {

    private static final String START_TIME_FIELD_NAME = "start";
    private static final String ALTERNATIVE_START_TIME_FIELD_NAME = "dateTime";
    private static final String END_TIME_FIELD_NAME = "end";
    private static final String TIME_FIELD_SEPARATOR = "/";

    public TimeObjectDeserializer() {
        this(null);
    }

    public TimeObjectDeserializer(Class<?> valueClass) {
        super(valueClass);
    }

    @Override
    public TimeObject deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String time = "";
        /* only works if the json fields are named "start" or "dateTime" and "end", or if there
         * is only one field named "start" or "dateTime", or if there are no json fields
         */
        if (node.isTextual()) {
            time = node.asText();
        } else {
            if (node.get(START_TIME_FIELD_NAME) != null) {
                time = node.get(START_TIME_FIELD_NAME).asText();
            } else if (node.get(ALTERNATIVE_START_TIME_FIELD_NAME) != null) {
                time = node.get(ALTERNATIVE_START_TIME_FIELD_NAME).asText();
            }
            if (node.get(END_TIME_FIELD_NAME) != null) {
                time += TIME_FIELD_SEPARATOR + node.get(END_TIME_FIELD_NAME).asText();
            }
        }
        return TimeObject.parse(time);
    }
}