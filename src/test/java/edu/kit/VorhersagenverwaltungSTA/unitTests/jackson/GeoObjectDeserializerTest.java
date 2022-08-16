package edu.kit.VorhersagenverwaltungSTA.unitTests.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.kit.VorhersagenverwaltungSTA.jackson.GeoObjectDeserializer;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.GeoObject;
import org.geojson.GeoJsonObject;
import org.geojson.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GeoObjectDeserializerTest {

    private static final String FIELD_NAME = "location";
    private static final String GEO_JSON_JSON = "{\"" + FIELD_NAME + "\": {\"type\": \"Point\",\"coordinates\": [9.597696,47.239445]}}";
    private static final String ANY_LOCATION_JSON = "{\"" + FIELD_NAME + "\": {\"name\":\"kitchen\",\"properties\": {\"heatingPlates\": 4}}}";
    private static final GeoJsonObject EXPECTED_GEO_JSON = new Point(9.597696,47.239445);
    private static final String EXPECTED_ANY_LOCATION = "{\"name\":\"kitchen\",\"properties\": {\"heatingPlates\": 4}}";
    private GeoObjectTestClass geoObjectTestClass;

    @BeforeEach
    public void setup() {
        this.geoObjectTestClass = null;
    }

    @Test
    public void testGeoJsonObjectDeserializing() {
        Assertions.assertDoesNotThrow( () -> this.geoObjectTestClass
                = new ObjectMapper().readerFor(GeoObjectTestClass.class).readValue(GEO_JSON_JSON));
        Assertions.assertEquals(EXPECTED_GEO_JSON, this.geoObjectTestClass.getGeoObject().getGeoJsonObject());
        Assertions.assertTrue(this.geoObjectTestClass.getGeoObject().isGeoJson());
    }

    @Test
    public void testAnyLocationDeserializing() {
        JsonNode expectedAnyLocation = null;
        try {
            expectedAnyLocation = new ObjectMapper().readTree(EXPECTED_ANY_LOCATION);
        } catch (JsonProcessingException j) {
            //does not occur
        }
        Assertions.assertDoesNotThrow( () -> this.geoObjectTestClass
                = new ObjectMapper().readerFor(GeoObjectTestClass.class).readValue(ANY_LOCATION_JSON));
        Assertions.assertEquals(expectedAnyLocation, this.geoObjectTestClass.getGeoObject().getLocation());
        Assertions.assertFalse(this.geoObjectTestClass.getGeoObject().isGeoJson());
    }

    private static class GeoObjectTestClass {
        @JsonDeserialize(using = GeoObjectDeserializer.class)
        @JsonProperty(FIELD_NAME)
        private GeoObject geoObject;

        public GeoObject getGeoObject() {
            return geoObject;
        }

        public void setGeoObject(GeoObject geoObject) {
            this.geoObject = geoObject;
        }
    }
}
