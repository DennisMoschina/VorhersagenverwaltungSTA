package edu.kit.VorhersagenverwaltungSTA.model.dataModel.catalogue;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.Entity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Catalogue extends Entity {
    @JsonProperty("id")
    private int id;

    @JsonProperty("url")
    private String url;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}