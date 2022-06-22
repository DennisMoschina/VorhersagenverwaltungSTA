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