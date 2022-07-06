package edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.Entity;

import java.util.List;
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

    @JsonProperty("Datastream@iot.navigationLink")
    private String datastreamURL;
    @JsonProperty("Datastreams")
    private List<Datastream> datastreams;
    @JsonProperty("Locations@iot.navigationLink")
    private String locationsURL;
    @JsonProperty("Locations")
    private List<Location> locations;
    @JsonProperty("HistoricalLocations@iot.navigationLink")
    private String historicalLocationsURL;
    @JsonProperty("HistoricalLocations")
    private List<HistoricalLocation> historicalLocations;

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

    public String getDatastreamURL() {
        return datastreamURL;
    }

    public void setDatastreamURL(String datastreamURL) {
        this.datastreamURL = datastreamURL;
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

    public List<Datastream> getDatastreams() {
        return List.copyOf(datastreams);
    }

    public void setDatastreams(List<Datastream> datastreams) {
        this.datastreams = datastreams;
    }

    public List<Location> getLocations() {
        return List.copyOf(locations);
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<HistoricalLocation> getHistoricalLocations() {
        return List.copyOf(historicalLocations);
    }

    public void setHistoricalLocations(List<HistoricalLocation> historicalLocations) {
        this.historicalLocations = historicalLocations;
    }

    @Override
    public String toString() {
        return "Thing{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", properties=" + properties +
                ", datastreamURL='" + datastreamURL + '\'' +
                ", locationsURL='" + locationsURL + '\'' +
                ", historicalLocationsURL='" + historicalLocationsURL + '\'' +
                '}';
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