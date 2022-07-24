package edu.kit.VorhersagenverwaltungSTA.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.Source;

import java.io.IOException;

/**
 * This class is used to deserialize a {@link Source} out of a json node.
 *
 * @author Elias Dirks
 */
public class SourceDeserializer extends StdDeserializer<Source> {

    private static final String SOURCE_FIELD_NAME = "url";
    public SourceDeserializer() {
        this(null);
    }

    public SourceDeserializer(Class<?> valueClass) {
        super(valueClass);
    }

    @Override
    public Source deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        //only works if the source field has a field named "url"
        return new Source(node.get(SOURCE_FIELD_NAME).asText());
    }
}
