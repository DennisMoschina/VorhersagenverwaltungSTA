package edu.kit.VorhersagenverwaltungSTA.model.dataModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class Contact {

    //@JsonProperty("@id") or just "id"?
    private long id;
    private String name;
    private String description;
    private String address;
    private String email;
    private String web;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
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
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", web='" + web + '\'' +
                ", properties=" + properties +
                ", dataSourceURL='" + dataSourceURL + '\'' +
                ", dataSources=" + dataSources +
                '}';
    }
}
