package edu.kit.VorhersagenverwaltungSTA.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.TimeObject;

import java.io.IOException;

public class TimeObjectDeserializer extends StdDeserializer<TimeObject> {

    private static final String START_TIME_FIELD_NAME = "start";
    private static final String END_TIME_FIELD_NAME = "end";

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
        /* only works if the json fields are named "start" and "end" or if there
         * is only one field named "start" or if there are no json fields
         */
        String time = node.get(START_TIME_FIELD_NAME).asText();
        if (node.get(END_TIME_FIELD_NAME) != null) {
            time += node.get(END_TIME_FIELD_NAME).asText();
        }
        return TimeObject.parse(time);
    }
}
