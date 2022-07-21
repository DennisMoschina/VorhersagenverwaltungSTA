package edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.Entity;

/**
 * This class describes a ObservedProperty as defined in the
 * <a href="http://www.opengis.net/doc/is/sensorthings/1.1#observedproperty">SensorThingsAPI</a>
 *
 * @author Elias Dirks
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ObservedProperty extends Entity {

    private String name;
    private String definition;
    private String description;
    private JsonNode properties;

    @JsonProperty("Datastream@iot.navigationLink")
    private String datastreamURL;
    @JsonProperty("Datastreams")
    private Datastream[] datastreams;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JsonNode getProperties() {
        return properties;
    }

    public void setProperties(JsonNode properties) {
        this.properties = properties;
    }

    public String getDatastreamURL() {
        return datastreamURL;
    }

    public void setDatastreamURL(String datastreamURL) {
        this.datastreamURL = datastreamURL;
    }

    public Datastream[] getDatastreams() {
        return this.datastreams;
    }

    public void setDatastreams(Datastream[] datastreams) {
        this.datastreams = datastreams;
    }

    @Override
    public String toString() {
        return "ObservedProperty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", definition='" + definition + '\'' +
                ", description='" + description + '\'' +
                ", properties=" + properties +
                ", datastreamURL='" + datastreamURL + '\'' +
                '}';
    }
}