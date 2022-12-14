package edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.kit.VorhersagenverwaltungSTA.jackson.IntervalDeserializer;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.Entity;
import org.geojson.GeoJsonObject;
import org.threeten.extra.Interval;

import java.util.Objects;

/**
 * This class describes a Datastream as defined in the
 * <a href="http://www.opengis.net/doc/is/sensorthings/1.1#datastream">SensorThingsAPI</a>
 *
 * @author Elias Dirks, Dennis Moschina
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Datastream extends Entity {

    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("observationType")
    private String observationType;
    @JsonProperty("unitOfMeasurement")
    private JsonNode unitOfMeasurement;
    @JsonProperty("observedArea")
    private GeoJsonObject observedArea;
    @JsonIgnoreProperties( value = {
            "empty",
            "unboundedStart",
            "unboundedEnd"
    })
    @JsonDeserialize(using = IntervalDeserializer.class)
    private Interval phenomenonTime;
    @JsonDeserialize(using = IntervalDeserializer.class)
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
    private Observation[] observations;


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

    public String getObservationType() {
        return observationType;
    }

    public void setObservationType(String observationType) {
        this.observationType = observationType;
    }

    public JsonNode getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(JsonNode unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public GeoJsonObject getObservedArea() {
        return observedArea;
    }

    public void setObservedArea(GeoJsonObject observedArea) {
        this.observedArea = observedArea;
    }

    public Interval getPhenomenonTime() {
        return phenomenonTime;
    }

    public void setPhenomenonTime(Interval phenomenonTime) {
        this.phenomenonTime = phenomenonTime;
    }

    public Interval getResultTime() {
        return resultTime;
    }

    public void setResultTime(Interval resultTime) {
        this.resultTime = resultTime;
    }

    public JsonNode getProperties() {
        return properties;
    }

    public void setProperties(JsonNode properties) {
        this.properties = properties;
    }

    public String getThingURL() {
        return thingURL;
    }

    public void setThingURL(String thingURL) {
        this.thingURL = thingURL;
    }

    public Thing getThing() {
        return thing;
    }

    public void setThing(Thing thing) {
        this.thing = thing;
    }

    public String getSensorURL() {
        return sensorURL;
    }

    public void setSensorURL(String sensorURL) {
        this.sensorURL = sensorURL;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public String getObservedPropertyURL() {
        return observedPropertyURL;
    }

    public void setObservedPropertyURL(String observedPropertyURL) {
        this.observedPropertyURL = observedPropertyURL;
    }

    public ObservedProperty getObservedProperty() {
        return observedProperty;
    }

    public void setObservedProperty(ObservedProperty observedProperty) {
        this.observedProperty = observedProperty;
    }

    public String getObservationsURL() {
        return observationsURL;
    }

    public void setObservationsURL(String observationsURL) {
        this.observationsURL = observationsURL;
    }

    public Observation[] getObservations() {
        return this.observations;
    }

    public void setObservations(Observation[] observations) {
        this.observations = observations;
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