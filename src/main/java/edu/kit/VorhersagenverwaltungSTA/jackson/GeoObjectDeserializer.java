package edu.kit.VorhersagenverwaltungSTA.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.GeoObject;
import org.geojson.GeoJsonObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * This class is used to deserialize a {@link GeoObject} out of a json node.
 *
 * @author Elias Dirks
 */
public class GeoObjectDeserializer extends StdDeserializer<GeoObject> {

    private static final String GEO_TYPE_FIELD_NAME = "type";
    private static final List<String> POSSIBLE_GEO_TYPES = Arrays.asList("Point", "Multipoint", "LineString",
            "MultiLineString", "Polygon", "Multipolygon", "Feature", "FeatureCollection", "GeometryCollection");

    public GeoObjectDeserializer() {
        this(null);
    }

    public GeoObjectDeserializer(Class<?> valueClass) {
        super(valueClass);
    }

    @Override
    public GeoObject deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        if (node.has(GEO_TYPE_FIELD_NAME) && POSSIBLE_GEO_TYPES.contains(node.get(GEO_TYPE_FIELD_NAME).asText())) {
            GeoJsonObject geoJsonObject = new ObjectMapper().readValue(node.toString(), GeoJsonObject.class);
            return new GeoObject(geoJsonObject);
        } else {
            JsonNode jsonNode = new ObjectMapper().readValue(node.toString(), JsonNode.class);
            return new GeoObject(jsonNode);
        }
    }
}
