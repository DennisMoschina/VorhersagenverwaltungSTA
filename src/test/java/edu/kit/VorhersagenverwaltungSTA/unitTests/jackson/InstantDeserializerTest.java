package edu.kit.VorhersagenverwaltungSTA.unitTests.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.kit.VorhersagenverwaltungSTA.jackson.InstantDeserializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;

public class InstantDeserializerTest {

    private static final String FIELD_NAME = "instant";
    private static final String INSTANT_JSON = "{\"" + FIELD_NAME + "\": \"2017-12-31T23:00:00Z\"}";

    private static final Instant EXPECTED = Instant.parse("2017-12-31T23:00:00Z");

    private InstantTestClass instantTestClass;

    @Test
    public void testInstantDeserializing() {
        Assertions.assertDoesNotThrow( () -> this.instantTestClass
                = new ObjectMapper().readerFor(InstantTestClass.class).readValue(INSTANT_JSON));
        Assertions.assertEquals(EXPECTED, this.instantTestClass.getInstant());
    }

    private static class InstantTestClass {
        @JsonDeserialize(using = InstantDeserializer.class)
        @JsonProperty(FIELD_NAME)
        private Instant instant;
        public Instant getInstant() {
            return this.instant;
        }
    }
}