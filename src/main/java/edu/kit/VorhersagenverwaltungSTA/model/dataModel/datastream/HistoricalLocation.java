package edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.List;

public class HistoricalLocation {

    @JsonProperty("@iot.id")
    private String id;
    private ZonedDateTime time;

    @JsonProperty("Thing@iot.navigationLink")
    private String thingURL;
    @JsonProperty("Thing")
    private Thing thing;
    @JsonProperty("Locations@iot.navigationLink")
    private String locationsURL;
    @JsonProperty("Locations")
    private List<Location> locations;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public void setTime(ZonedDateTime time) {
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
