package edu.kit.VorhersagenverwaltungSTA.unitTests.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.kit.VorhersagenverwaltungSTA.jackson.TimeObjectDeserializer;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.TimeObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TimeObjectDeserializerTest {

    private static final String FIELD_NAME = "timeValue";
    private static final String INSTANT_JSON = "{\"" + FIELD_NAME + "\": \"2017-12-31T23:00:00Z\"}";
    private static final String INTERVAL_JSON = "{\"" + FIELD_NAME + "\": \"2017-12-31T23:00:00Z/2022-07-21T17:00:00Z\"}";
    private static final String DIVIDED_INTERVAL_JSON = "{\"" + FIELD_NAME + "\": {\"start\": \"2017-12-31T23:00:00Z\", \"end\": \"2022-07-21T17:00:00Z\"}}";
    private static final TimeObject EXPECTED_INSTANT = TimeObject.parse("2017-12-31T23:00:00Z");
    private static final TimeObject EXPECTED_INTERVAL = TimeObject.parse("2017-12-31T23:00:00Z/2022-07-21T17:00:00Z");

    private TimeObjectTestClass timeObjectTestClass;

    @BeforeEach
    public void setup() {
        this.timeObjectTestClass = null;
    }

    @Test
    public void testInstantDeserializing() {
        Assertions.assertDoesNotThrow( () -> this.timeObjectTestClass
                = new ObjectMapper().readerFor(TimeObjectTestClass.class).readValue(INSTANT_JSON));
        Assertions.assertEquals(EXPECTED_INSTANT, this.timeObjectTestClass.getTimeObject());
    }

    @Test
    public void testIntervalDeserializing() {
        Assertions.assertDoesNotThrow( () -> this.timeObjectTestClass
                = new ObjectMapper().readerFor(TimeObjectTestClass.class).readValue(INTERVAL_JSON));
        Assertions.assertEquals(EXPECTED_INTERVAL, this.timeObjectTestClass.getTimeObject());
    }

    @Test
    public void testDividedIntervalDeserializing() {
        Assertions.assertDoesNotThrow( () -> this.timeObjectTestClass
                = new ObjectMapper().readerFor(TimeObjectTestClass.class).readValue(DIVIDED_INTERVAL_JSON));
        Assertions.assertEquals(EXPECTED_INTERVAL, this.timeObjectTestClass.getTimeObject());
    }

    private static class TimeObjectTestClass {
        @JsonDeserialize(using = TimeObjectDeserializer.class)
        @JsonProperty(FIELD_NAME)
        private TimeObject timeObject;
        public TimeObject getTimeObject() {
            return this.timeObject;
        }
    }
}