package edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream;

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
    @JsonProperty("Datastream")
    private Datastream datastream;
    @JsonProperty("FeatureOfInterest@iot.navigationLink")
    private String featureOfInterestURL;
    @JsonProperty("FeatureOfInterest")
    private FeatureOfInterest featureOfInterest;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Pair<Instant, Instant> getPhenomenonTime() {
        return phenomenonTime;
    }

    public void setPhenomenonTime(Pair<Instant, Instant> phenomenonTime) {
        this.phenomenonTime = phenomenonTime;
    }

    public Instant getResultTime() {
        return resultTime;
    }

    public void setResultTime(Instant resultTime) {
        this.resultTime = resultTime;
    }

    public JsonNode getResult() {
        return result;
    }

    public void setResult(JsonNode result) {
        this.result = result;
    }

    public Pair<Instant, Instant> getValidTime() {
        return validTime;
    }

    public void setValidTime(Pair<Instant, Instant> validTime) {
        this.validTime = validTime;
    }

    public JsonNode getParameters() {
        return parameters;
    }

    public void setParameters(JsonNode parameters) {
        this.parameters = parameters;
    }

    public String getDatastreamURL() {
        return datastreamURL;
    }

    public void setDatastreamURL(String datastreamURL) {
        this.datastreamURL = datastreamURL;
    }

    public String getFeatureOfInterestURL() {
        return featureOfInterestURL;
    }

    public void setFeatureOfInterestURL(String featureOfInterestURL) {
        this.featureOfInterestURL = featureOfInterestURL;
    }

    @Override
    public String toString() {
        return "Observation{" +
                "id=" + id +
                ", phenomenonTime=" + phenomenonTime +
                ", resultTime=" + resultTime +
                ", result=" + result +
                ", validTime=" + validTime +
                ", parameters=" + parameters +
                ", datastreamURL='" + datastreamURL + '\'' +
                ", featureOfInterestURL='" + featureOfInterestURL + '\'' +
                '}';
    }
}
