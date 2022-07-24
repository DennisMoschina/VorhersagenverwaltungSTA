package edu.kit.VorhersagenverwaltungSTA.model.dataModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.kit.VorhersagenverwaltungSTA.jackson.DurationDeserializer;
import edu.kit.VorhersagenverwaltungSTA.jackson.SourceDeserializer;
import edu.kit.VorhersagenverwaltungSTA.jackson.TimeObjectDeserializer;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.TimeObject;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.Source;
import org.geojson.GeoJsonObject;

import java.time.Duration;

/**
 * This class describes a DataSource of the SensorThingsAPI.
 *
 * @author Elias Dirks
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataSource extends Entity {
    private String name;
    private String description;
    private String sourceSystem;
    @JsonDeserialize(using = TimeObjectDeserializer.class)
    private TimeObject phenomenonTime;
    private String dataType;
    private JsonNode dataQuality;
    @JsonDeserialize(using = DurationDeserializer.class)
    private Duration recordingPeriod;
    @JsonDeserialize(using = DurationDeserializer.class)
    private Duration aggregationPeriod;
    @JsonDeserialize(using = DurationDeserializer.class)
    private Duration transmissionPeriod;
    private String spatialDistribution;
    private GeoJsonObject observedArea;
    @JsonDeserialize(using = SourceDeserializer.class)
    private Source accessData;
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

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public TimeObject getPhenomenonTime() {
        return phenomenonTime;
    }

    public void setPhenomenonTime(TimeObject phenomenonTime) {
        this.phenomenonTime = phenomenonTime;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public JsonNode getDataQuality() {
        return dataQuality;
    }

    public void setDataQuality(JsonNode dataQuality) {
        this.dataQuality = dataQuality;
    }

    public Duration getRecordingPeriod() {
        return recordingPeriod;
    }

    public void setRecordingPeriod(Duration recordingPeriod) {
        this.recordingPeriod = recordingPeriod;
    }

    public Duration getAggregationPeriod() {
        return aggregationPeriod;
    }

    public void setAggregationPeriod(Duration aggregationPeriod) {
        this.aggregationPeriod = aggregationPeriod;
    }

    public Duration getTransmissionPeriod() {
        return transmissionPeriod;
    }

    public void setTransmissionPeriod(Duration transmissionPeriod) {
        this.transmissionPeriod = transmissionPeriod;
    }

    public String getSpatialDistribution() {
        return spatialDistribution;
    }

    public void setSpatialDistribution(String spatialDistribution) {
        this.spatialDistribution = spatialDistribution;
    }

    public GeoJsonObject getObservedArea() {
        return observedArea;
    }

    public void setObservedArea(GeoJsonObject observedArea) {
        this.observedArea = observedArea;
    }

    public Source getAccessData() {
        return accessData;
    }

    public void setAccessData(Source accessData) {
        this.accessData = accessData;
    }

    public JsonNode getProperties() {
        return properties;
    }

    public void setProperties(JsonNode properties) {
        this.properties = properties;
    }

    public String getOwnerURL() {
        return ownerURL;
    }

    public void setOwnerURL(String ownerURL) {
        this.ownerURL = ownerURL;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getContactURL() {
        return contactURL;
    }

    public void setContactURL(String contactURL) {
        this.contactURL = contactURL;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getLicenseURL() {
        return licenseURL;
    }

    public void setLicenseURL(String licenseURL) {
        this.licenseURL = licenseURL;
    }

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    public String getAccessInterfaceURL() {
        return accessInterfaceURL;
    }

    public void setAccessInterfaceURL(String accessInterfaceURL) {
        this.accessInterfaceURL = accessInterfaceURL;
    }

    public AccessInterface getAccessInterface() {
        return accessInterface;
    }

    public void setAccessInterface(AccessInterface accessInterface) {
        this.accessInterface = accessInterface;
    }

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
