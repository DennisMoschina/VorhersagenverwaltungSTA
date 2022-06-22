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
}
