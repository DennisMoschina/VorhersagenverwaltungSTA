package edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.kit.VorhersagenverwaltungSTA.jackson.InstantDeserializer;
import edu.kit.VorhersagenverwaltungSTA.jackson.IntervalDeserializer;
import edu.kit.VorhersagenverwaltungSTA.model.core.Pair;
import org.threeten.extra.Interval;

import java.time.Instant;
import java.time.ZonedDateTime;


public class Observation {

    @JsonProperty("@iot.id")
    private long id;

    private TimeObject phenomenonTime;
    @JsonDeserialize(using = InstantDeserializer.class)
    private Instant resultTime;
    private Object result;
    private Object dqElement; // dataQuality
    @JsonDeserialize(using = IntervalDeserializer.class)
    private Interval validTime;
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

    public TimeObject getPhenomenonTime() {
        return phenomenonTime;
    }

    public void setPhenomenonTime(TimeObject phenomenonTime) {
        this.phenomenonTime = phenomenonTime;
    }

    public Instant getResultTime() {
        return resultTime;
    }

    public void setResultTime(Instant resultTime) {
        this.resultTime = resultTime;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Object getDqElement() {
        return dqElement;
    }

    public void setDqElement(Object dqElement) {
        this.dqElement = dqElement;
    }

    public Interval getValidTime() {
        return validTime;
    }

    public void setValidTime(Interval validTime) {
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

    public Datastream getDatastream() {
        return datastream;
    }

    public void setDatastream(Datastream datastream) {
        this.datastream = datastream;
    }

    public FeatureOfInterest getFeatureOfInterest() {
        return featureOfInterest;
    }

    public void setFeatureOfInterest(FeatureOfInterest featureOfInterest) {
        this.featureOfInterest = featureOfInterest;
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
