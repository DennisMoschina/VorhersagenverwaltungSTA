package edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import org.geojson.GeoJsonObject;

/**
 * A geo object can either be a {@link GeoJsonObject} or simply anything that can be displayed
 * as a json node.
 *
 * @author Elias Dirks
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeoObject {

    private GeoJsonObject geoJsonObject;

    private JsonNode location;

    private boolean isGeoJson;

    public GeoObject(GeoJsonObject geoJsonObject) {
        this.geoJsonObject = geoJsonObject;
        this.isGeoJson = true;
    }

    public GeoObject(JsonNode location) {
        this.location = location;
        this.isGeoJson = false;
    }

    public JsonNode getLocation() {
        return location;
    }

    public void setLocation(JsonNode location) {
        this.location = location;
    }

    public GeoJsonObject getGeoJsonObject() {
        return geoJsonObject;
    }

    public void setGeoJsonObject(GeoJsonObject geoJsonObject) {
        this.geoJsonObject = geoJsonObject;
    }

    public boolean isGeoJson() {
        return isGeoJson;
    }

    public void setGeoJson(boolean geoJson) {
        isGeoJson = geoJson;
    }

}
