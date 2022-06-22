package edu.kit.VorhersagenverwaltungSTA.model.dataModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Thing {
    @JsonProperty("@iot.id")
    private long id;
    private String name;
    private String description;
    private JsonNode properties;
    @JsonProperty("Datastream@iot.navigationLink")
    private String datastreamURL;
    @JsonProperty("Locations@iot.navigationLink")
    private String locationsURL;
    @JsonProperty("HistoricalLocations@iot.navigationLink")
    private String historicalLocationsURL;

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

    @Override
    public String toString() {
        return "Thing{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Thing thing)) return false;
        return id == thing.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}