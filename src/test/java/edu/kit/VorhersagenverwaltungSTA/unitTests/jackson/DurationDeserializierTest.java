package edu.kit.VorhersagenverwaltungSTA.unitTests.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.kit.VorhersagenverwaltungSTA.jackson.DurationDeserializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

public class DurationDeserializierTest {

    private static final String FIELD_NAME = "duration";
    private static final String DURATION_JSON = "{\"" + FIELD_NAME + "\": \"PT1H\"}";
    private static final Duration EXPECTED = Duration.parse("PT1H");

    private DurationTestClass durationTestClass;

    @Test
    public void testDurationDeserializing() {
        Assertions.assertDoesNotThrow( () -> this.durationTestClass
                = new ObjectMapper().readerFor(DurationTestClass.class).readValue(DURATION_JSON));
        Assertions.assertEquals(EXPECTED, this.durationTestClass.getDuration());
    }

    private static class DurationTestClass {
        @JsonDeserialize(using = DurationDeserializer.class)
        @JsonProperty(FIELD_NAME)
        private Duration duration;
        public Duration getDuration() {
            return this.duration;
        }
    }
}