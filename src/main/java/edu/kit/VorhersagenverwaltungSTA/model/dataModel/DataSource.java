package edu.kit.VorhersagenverwaltungSTA.model.dataModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import edu.kit.VorhersagenverwaltungSTA.model.core.Pair;

import java.time.Duration;
import java.time.Instant;

public class DataSource {

    @JsonProperty("id")
    private long id;
    private String name;
    private String description;
    private String sourceSystem;
    private Pair<Instant, Instant> phenomenonTime;
    private String dataType;
    private JsonNode dataQuality;
    private Duration recordingPeriod;
    private Duration aggregationPeriod;
    private Duration transmissionPeriod;
    private String spatialDistribution;
    private JsonNode observedArea;
    private JsonNode accessData;
    private JsonNode properties;

    @JsonProperty("Owner@navigationLink")
    private String ownerURL;
    @JsonProperty("Owner")
    private Owner owner;
    @JsonProperty("Contact@navigationLink")
    private String contactURL;
    @JsonProperty("Contact")
    private Contact contact;
    @JsonProperty("License@navigationLink")
    private String licenseURL;
    @JsonProperty("License")
    private License license;
    @JsonProperty("AccessInterface@navigationLink")
    private String accessInterfaceURL;
    @JsonProperty("AccessInterface")
    private AccessInterface accessInterface;

    @Override
    public String toString() {
        return "DataSource{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", sourceSystem='" + sourceSystem + '\'' +
                ", phenomenonTime=" + phenomenonTime +
                ", dataType='" + dataType + '\'' +
                ", dataQuality=" + dataQuality +
                ", recordingPeriod=" + recordingPeriod +
                ", aggregationPeriod=" + aggregationPeriod +
                ", transmissionPeriod=" + transmissionPeriod +
                ", spatialDistribution='" + spatialDistribution + '\'' +
                ", accessData=" + accessData +
                ", properties=" + properties +
                ", ownerURL='" + ownerURL + '\'' +
                ", owner=" + owner +
                ", contactURL='" + contactURL + '\'' +
                ", contact=" + contact +
                ", licenseURL='" + licenseURL + '\'' +
                ", license=" + license +
                ", accessInterfaceURL='" + accessInterfaceURL + '\'' +
                ", accessInterface=" + accessInterface +
                '}';
    }
}
