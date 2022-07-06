package edu.kit.VorhersagenverwaltungSTA.model.dataModel.catalogue;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.DataSource;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.Entity;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.ProcessingProcedure;

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