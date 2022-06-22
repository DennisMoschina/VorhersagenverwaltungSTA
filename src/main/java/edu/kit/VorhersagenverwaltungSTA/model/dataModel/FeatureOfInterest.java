package edu.kit.VorhersagenverwaltungSTA.model.dataModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

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
}
