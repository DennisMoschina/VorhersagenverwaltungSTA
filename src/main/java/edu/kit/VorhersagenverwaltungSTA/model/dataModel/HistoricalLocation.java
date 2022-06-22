package edu.kit.VorhersagenverwaltungSTA.model.dataModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class HistoricalLocation {

    @JsonProperty("@iot.id")
    private String id;
    private Instant time;

    @JsonProperty("Thing@iot.navigationLink")
    private String thingURL;
    @JsonProperty("Locations@iot.navigationLink")
    private String locationsURL;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getThingURL() {
        return thingURL;
    }

    public void setThingURL(String thingURL) {
        this.thingURL = thingURL;
    }

    public String getLocationsURL() {
        return locationsURL;
    }

    public void setLocationsURL(String locationsURL) {
        this.locationsURL = locationsURL;
    }

    @Override
    public String toString() {
        return "HistoricalLocation{" +
                "id='" + id + '\'' +
                ", time=" + time +
                ", thingURL='" + thingURL + '\'' +
                ", locationsURL='" + locationsURL + '\'' +
                '}';
    }
}
