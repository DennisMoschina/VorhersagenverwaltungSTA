package edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.kit.VorhersagenverwaltungSTA.jackson.InstantDeserializer;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.Entity;

import java.time.Instant;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoricalLocation extends Entity {

    @JsonDeserialize(using = InstantDeserializer.class)
    private Instant time;

    @JsonProperty("Thing@iot.navigationLink")
    private String thingURL;
    @JsonProperty("Thing")
    private Thing thing;
    @JsonProperty("Locations@iot.navigationLink")
    private String locationsURL;
    @JsonProperty("Locations")
    private List<Location> locations;


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

    public Thing getThing() {
        return thing;
    }

    public void setThing(Thing thing) {
        this.thing = thing;
    }

    public List<Location> getLocations() {
        return List.copyOf(locations);
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
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
