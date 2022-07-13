package edu.kit.VorhersagenverwaltungSTA.model.dataModel.catalogue;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.DataSource;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.Entity;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.ProcessingProcedure;

import java.util.Objects;

/**
 * This class describes a catalogue that contains multiple
 * {@link DataSource} and {@link ProcessingProcedure}.
 *
 * @author Elias Dirks, Dennis Moschina
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Catalogue extends Entity {

    @JsonProperty("url")
    private String url;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Catalogue catalogue)) return false;
        return Objects.equals(getId(), catalogue.getId())
                && Objects.equals(getUrl(), catalogue.getUrl())
                && Objects.equals(getName(), catalogue.getName())
                && Objects.equals(getDescription(), catalogue.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUrl(), getName(), getDescription());
    }
}