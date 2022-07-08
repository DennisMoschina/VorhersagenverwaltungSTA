package edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.Entity;

import java.util.List;

/**
 * This class describes a Sensor as defined in the
 * <a href="http://www.opengis.net/doc/is/sensorthings/1.1#sensor">SensorThingsAPI</a>
 *
 * @author Elias Dirks, Dennis Moschina
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sensor extends Entity {

    private String name;
    private String description;
    private String encodingType;
    private Object metadata;
    private JsonNode properties;
    @JsonProperty("Datastream@iot.navigationLink")
    private String datastreamURL;
    @JsonProperty("Datastreams")
    private List<Datastream> datastreams;


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

    public Object getMetadata() {
        return metadata;
    }

    public void setMetadata(Object metadata) {
        this.metadata = metadata;
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

    public List<Datastream> getDatastreams() {
        return List.copyOf(datastreams);
    }

    public void setDatastreams(List<Datastream> datastreams) {
        this.datastreams = datastreams;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", encodingType='" + encodingType + '\'' +
                ", metadata=" + metadata +
                ", properties=" + properties +
                ", datastreamURL='" + datastreamURL + '\'' +
                '}';
    }
}