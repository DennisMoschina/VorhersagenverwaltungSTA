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

    private JsonNode properties;

    @JsonProperty("Datastream@iot.navigationLink")
    private String datastreamURL;
}