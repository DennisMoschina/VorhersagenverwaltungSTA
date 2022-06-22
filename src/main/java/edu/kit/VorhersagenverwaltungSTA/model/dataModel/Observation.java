package edu.kit.VorhersagenverwaltungSTA.model.dataModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import edu.kit.VorhersagenverwaltungSTA.model.core.Pair;

import java.time.Instant;

public class Observation {

    @JsonProperty("@iot.id")
    private long id;

    private Pair<Instant, Instant> phenomenonTime;
    private Instant resultTime;
    private JsonNode result;
    //private DQ_Element dqElement;
    private Pair<Instant, Instant> validTime;
    private JsonNode parameters;

    @JsonProperty("Datastream@iot.navigationLink")
    private String datastreamURL;
    @JsonProperty("FeatureOfInterest@iot.navigationLink")
    private String featureOfInterestURL;
}
