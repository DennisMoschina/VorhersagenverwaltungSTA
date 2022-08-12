package edu.kit.VorhersagenverwaltungSTA.unitTests.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.kit.VorhersagenverwaltungSTA.jackson.IntervalDeserializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.threeten.extra.Interval;

public class IntervalDeserializerTest {

    private static final String FIELD_NAME = "interval";
    private static final String INTERVAL_JSON = "{\"" + FIELD_NAME + "\": \"2017-12-31T23:00:00Z/2022-07-21T17:00:00Z\"}";

    private static final Interval EXPECTED = Interval.parse("2017-12-31T23:00:00Z/2022-07-21T17:00:00Z");

    private IntervalTestClass intervalTestClass;

    @Test
    public void testIntervalDeserializing() {
        Assertions.assertDoesNotThrow( () -> this.intervalTestClass
                = new ObjectMapper().readerFor(IntervalTestClass.class).readValue(INTERVAL_JSON));
        Assertions.assertEquals(EXPECTED, this.intervalTestClass.getInterval());
    }

    private static class IntervalTestClass {
        @JsonDeserialize(using = IntervalDeserializer.class)
        @JsonProperty(FIELD_NAME)
        private Interval interval;
        public Interval getInterval() {
            return this.interval;
        }
    }
}