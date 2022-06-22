package edu.kit.VorhersagenverwaltungSTA.model.dataModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class FeatureOfInterest {

    @JsonProperty("@iot.id")
    private long id;
    private String name;
    private String description;
    private String encodingType;
    private JsonNode feature;
    private JsonNode properties;

    @JsonProperty("Observations@iot.navigationLink")
    private String observationsURL;
    @JsonProperty("Observations")
    private List<Observation> observations;

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

    public JsonNode getFeature() {
        return feature;
    }

    public void setFeature(JsonNode feature) {
        this.feature = feature;
    }

    public JsonNode getProperties() {
        return properties;
    }

    public void setProperties(JsonNode properties) {
        this.properties = properties;
    }

    public String getObservationsURL() {
        return observationsURL;
    }

    public void setObservationsURL(String observationsURL) {
        this.observationsURL = observationsURL;
    }

    @Override
    public String toString() {
        return "FeatureOfInterest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", encodingType='" + encodingType + '\'' +
                ", feature=" + feature +
                ", properties=" + properties +
                ", observationsURL='" + observationsURL + '\'' +
                '}';
    }
}
