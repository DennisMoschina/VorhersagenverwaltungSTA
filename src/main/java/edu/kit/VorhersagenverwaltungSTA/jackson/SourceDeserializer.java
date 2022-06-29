package edu.kit.VorhersagenverwaltungSTA.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.Source;

import java.io.IOException;

public class SourceDeserializer extends StdDeserializer<Source> {

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
        //only works if accessData has field "url"
        return new Source(node.get("url").asText());
    }
}
