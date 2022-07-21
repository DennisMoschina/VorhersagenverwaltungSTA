package edu.kit.VorhersagenverwaltungSTA.unitTests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.kit.VorhersagenverwaltungSTA.jackson.SourceDeserializer;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.Source;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SourceDeserializerTest {

    private static final String FIELD_NAME = "accessData";
    private static final String SOURCE_JSON = "{\"" + FIELD_NAME + "\": {\"url\": \"https://example.com\"}}";
    private static final Source EXPECTED = new Source("https://example.com");

    private SourceTestClass sourceTestClass;

    @Test
    public void testSourceDeserializing() {
        Assertions.assertDoesNotThrow( () -> this.sourceTestClass
                = new ObjectMapper().readerFor(SourceTestClass.class).readValue(SOURCE_JSON));
        Assertions.assertEquals(EXPECTED, this.sourceTestClass.getSource());
    }

    private static class SourceTestClass {
        @JsonDeserialize(using = SourceDeserializer.class)
        @JsonProperty(FIELD_NAME)
        private Source source;
        public Source getSource() {
            return this.source;
        }
    }
}
