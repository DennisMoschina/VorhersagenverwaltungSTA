package edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.Entity;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.STAObjectList;
import org.geojson.GeoJsonObject;

/**
 * This class describes a FeatureOfInterest as defined in the
 * <a href="http://www.opengis.net/doc/is/sensorthings/1.1#featureofinterest">SensorThingsAPI</a>
 *
 * @author Elias Dirks
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeatureOfInterest extends Entity {

    @JsonProperty("@iot.id")
    private long id;
    private String name;
    private String description;
    private String encodingType;
    private GeoJsonObject feature;
    private JsonNode properties;

    @JsonProperty("Observations@iot.navigationLink")
    private String observationsURL;
    @JsonProperty("Observations")
    private STAObjectList<Observation> observations;


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

    public GeoJsonObject getFeature() {
        return feature;
    }

    public void setFeature(GeoJsonObject feature) {
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

    public STAObjectList<Observation> getObservations() {
        return this.observations.copyOf();
    }

    public void setObservations(STAObjectList<Observation> observations) {
        this.observations = observations;
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
