package edu.kit.VorhersagenverwaltungSTA.model.dataModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * This class describes a License of the SensorThingsAPI.
 *
 * @author Elias Dirks
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class License extends Entity {

    private String name;
    private String description;
    private JsonNode properties;

    @JsonProperty("DataSources@navigationLink")
    private String dataSourceURL;
    @JsonProperty("DataSources")
    private DataSource[] dataSources;

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

    public JsonNode getProperties() {
        return properties;
    }

    public void setProperties(JsonNode properties) {
        this.properties = properties;
    }

    public String getDataSourceURL() {
        return dataSourceURL;
    }

    public void setDataSourceURL(String dataSourceURL) {
        this.dataSourceURL = dataSourceURL;
    }

    public DataSource[] getDataSources() {
        return this.dataSources;
    }

    public void setDataSources(DataSource[] dataSources) {
        this.dataSources = dataSources;
    }
}