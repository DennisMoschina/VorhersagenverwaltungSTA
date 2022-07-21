package edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.Entity;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.STAObjectList;

/**
 * This class describes a Location as defined in the
 * <a href="http://www.opengis.net/doc/is/sensorthings/1.1#location">SensorThingsAPI</a>
 *
 * @author Elias Dirks
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location extends Entity {

    private String name;
    private String description;
    private String encodingType;
    private Object location;
    private JsonNode properties;
    @JsonProperty("HistoricalLocations@iot.navigationLink")
    private String historicalLocationsURL;
    @JsonProperty("HistoricalLocations")
    private STAObjectList<HistoricalLocation> historicalLocations;
    @JsonProperty("Things@iot.navigationLink")
    private String thingsURL;
    @JsonProperty("Things")
    private Thing[] things;


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

    public STAObjectList<HistoricalLocation> getHistoricalLocations() {
        return this.historicalLocations.copyOf();
    }

    public void setHistoricalLocations(STAObjectList<HistoricalLocation> historicalLocations) {
        this.historicalLocations = historicalLocations;
    }

    public Thing[] getThings() {
        return this.things;
    }

    public void setThings(Thing[] things) {
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