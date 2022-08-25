package edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.Entity;

import java.util.Objects;

/**
 * This class describes a Thing as defined in the
 * <a href="http://www.opengis.net/doc/is/sensorthings/1.1#thing">SensorThingsAPI</a>
 *
 * @author Elias Dirks, Dennis Moschina
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Thing extends Entity {
    private String name;
    private String description;
    private JsonNode properties;

    @JsonProperty("Datastreams@iot.navigationLink")
    private String datastreamsURL;
    @JsonProperty("Datastreams")
    private Datastream[] datastreams;
    @JsonProperty("Locations@iot.navigationLink")
    private String locationsURL;
    @JsonProperty("Locations")
    private Location[] locations;
    @JsonProperty("HistoricalLocations@iot.navigationLink")
    private String historicalLocationsURL;
    @JsonProperty("HistoricalLocations")
    private HistoricalLocation[] historicalLocations;

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

    public JsonNode getProperties() {
        return properties;
    }

    public void setProperties(JsonNode properties) {
        this.properties = properties;
    }

    public String getDatastreamsURL() {
        return datastreamsURL;
    }

    public void setDatastreamsURL(String datastreamsURL) {
        this.datastreamsURL = datastreamsURL;
    }

    public String getLocationsURL() {
        return locationsURL;
    }

    public void setLocationsURL(String locationsURL) {
        this.locationsURL = locationsURL;
    }

    public String getHistoricalLocationsURL() {
        return historicalLocationsURL;
    }

    public void setHistoricalLocationsURL(String historicalLocationsURL) {
        this.historicalLocationsURL = historicalLocationsURL;
    }

    public Datastream[] getDatastreams() {
        return this.datastreams;
    }

    public void setDatastreams(Datastream[] datastreams) {
        this.datastreams = datastreams;
    }

    public Location[] getLocations() {
        return locations;
    }

    public void setLocations(Location[] locations) {
        this.locations = locations;
    }

    public HistoricalLocation[] getHistoricalLocations() {
        return historicalLocations;
    }

    public void setHistoricalLocations(HistoricalLocation[] historicalLocations) {
        this.historicalLocations = historicalLocations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Thing thing)) return false;
        return id == thing.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}