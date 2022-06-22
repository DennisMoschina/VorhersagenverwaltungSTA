package edu.kit.VorhersagenverwaltungSTA.model.dataModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sensor {
    @JsonProperty("@iot.id")
    private long id;
    private String name;
    private String description;
    private String encodingType;
    private JsonNode metadata;
    private JsonNode properties;

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

    public JsonNode getMetadata() {
        return metadata;
    }

    public void setMetadata(JsonNode metadata) {
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

    @JsonProperty("Datastream@iot.navigationLink")
    private String datastreamURL;
}