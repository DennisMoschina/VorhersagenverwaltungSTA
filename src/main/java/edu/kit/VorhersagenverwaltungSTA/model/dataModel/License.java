package edu.kit.VorhersagenverwaltungSTA.model.dataModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class License {

    //@JsonProperty("@id") or just "id"?
    private long id;
    private String name;
    private String description;
    private JsonNode properties;

    @JsonProperty("DataSources@navigationLink")
    private String dataSourceURL;
    @JsonProperty("DataSources")
    private List<DataSource> dataSources;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public List<DataSource> getDataSources() {
        return dataSources;
    }

    public void setDataSources(List<DataSource> dataSources) {
        this.dataSources = dataSources;
    }

    @Override
    public String toString() {
        return "License{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", properties=" + properties +
                ", dataSourceURL='" + dataSourceURL + '\'' +
                ", dataSources=" + dataSources +
                '}';
    }
}
