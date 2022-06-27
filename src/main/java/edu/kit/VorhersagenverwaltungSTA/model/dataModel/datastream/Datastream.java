package edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import edu.kit.VorhersagenverwaltungSTA.model.core.Pair;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import org.geojson.Polygon;
import org.threeten.extra.Interval;


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

    private JsonNode unitOfMeasurement;
    private Polygon observedArea;
    private Interval phenomenonTime;
    private Interval resultTime;

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
    @JsonProperty("ObservedProperty")
    private ObservedProperty observedProperty;

    @JsonProperty("Observations@iot.navigationLink")
    private String observationsURL;
    @JsonProperty("Observations")
    private List<Observation> observations;


    @Override
    public String toString() {
        return "Datastream{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", observationType='" + observationType + '\'' +
                ", unitOfMeasurement=" + unitOfMeasurement +
                ", observedArea=" + observedArea +
                ", phenomenonTime=" + phenomenonTime +
                ", resultTime=" + resultTime +
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