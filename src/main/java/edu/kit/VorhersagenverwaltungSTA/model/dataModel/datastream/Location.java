package edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class Location {

    @JsonProperty("@iot.id")
    private long id;
    private String name;
    private String description;
    private String encodingType;
    private Object location;
    private JsonNode properties;
    @JsonProperty("HistoricalLocations@iot.navigationLink")
    private String historicalLocationsURL;
    @JsonProperty("HistoricalLocations")
    private List<HistoricalLocation> historicalLocations;
    @JsonProperty("Things@iot.navigationLink")
    private String thingsURL;
    @JsonProperty("Things")
    private List<Thing> things;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEncodingType() {
        return encodingType;
    }

    public void setEncodingType(String encodingType) {
        this.encodingType = encodingType;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }

    public JsonNode getProperties() {
        return properties;
    }

    public void setProperties(JsonNode properties) {
        this.properties = properties;
    }

    public String getHistoricalLocationsURL() {
        return historicalLocationsURL;
    }

    public void setHistoricalLocationsURL(String historicalLocationsURL) {
        this.historicalLocationsURL = historicalLocationsURL;
    }

    public String getThingsURL() {
        return thingsURL;
    }

    public void setThingsURL(String thingsURL) {
        this.thingsURL = thingsURL;
    }

    public List<HistoricalLocation> getHistoricalLocations() {
        return List.copyOf(historicalLocations);
    }

    public void setHistoricalLocations(List<HistoricalLocation> historicalLocations) {
        this.historicalLocations = historicalLocations;
    }

    public List<Thing> getThings() {
        return List.copyOf(things);
    }

    public void setThings(List<Thing> things) {
        this.things = things;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", encodingType='" + encodingType + '\'' +
                ", location=" + location +
                ", properties=" + properties +
                ", historicalLocationsURL='" + historicalLocationsURL + '\'' +
                ", thingsURL='" + thingsURL + '\'' +
                '}';
    }
}
