package edu.kit.VorhersagenverwaltungSTA.model.dataModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Objects;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Datastream {
    @JsonProperty("@iot.id")
    private long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("observationType")
    private String observationType;
    //TODO: add unitOfMeasurement
    //TODO: add observedArea
    //TODO: add phenomenonTime
    //TODO: add resultTime
    @JsonProperty("properties")
    private JsonNode properties;

    @JsonProperty("Thing@iot.navigationLink")
    private String thingURL;
    @JsonProperty("Thing")
    private Thing thing;

    @JsonProperty("Sensor@iot.navigationLink")
    private String sensorURL;
    @JsonProperty("Sensor")
    private Sensor sensor;

    @JsonProperty("ObservedProperty@iot.navigationLink")
    private String observedPropertyURL;

    @JsonProperty("Observations@iot.navigationLink")
    private String observationsURL;

    @Override
    public String toString() {
        return "Datastream{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", observationType='" + observationType + '\'' +
                ", properties=" + properties +
                ", thingURL='" + thingURL + '\'' +
                ", thing=" + thing +
                ", sensorURL='" + sensorURL + '\'' +
                ", sensor=" + sensor +
                ", observedPropertyURL='" + observedPropertyURL + '\'' +
                ", observationsURL='" + observationsURL + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Datastream that)) return false;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}