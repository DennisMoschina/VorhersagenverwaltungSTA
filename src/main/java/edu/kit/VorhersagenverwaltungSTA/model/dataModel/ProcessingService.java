package edu.kit.VorhersagenverwaltungSTA.model.dataModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.STAObjectList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProcessingService extends Entity {
    @JsonProperty("id")
    private long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("implementationURL")
    private String implementationURL;
    @JsonProperty("WritesSources")
    private STAObjectList<DataSource> writesSource;
    @JsonProperty("ReadsSources")
    private STAObjectList<DataSource> readsSources;
    @JsonProperty("AppliesMethods")
    private STAObjectList<ProcessingProcedure> appliesMethods;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

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

    public String getImplementationURL() {
        return implementationURL;
    }

    public void setImplementationURL(String implementationURL) {
        this.implementationURL = implementationURL;
    }

    public STAObjectList<DataSource> getWritesSource() {
        return writesSource;
    }

    public void setWritesSource(STAObjectList<DataSource> writesSource) {
        this.writesSource = writesSource;
    }

    public STAObjectList<DataSource> getReadsSources() {
        return readsSources;
    }

    public void setReadsSources(STAObjectList<DataSource> readsSources) {
        this.readsSources = readsSources;
    }

    public STAObjectList<ProcessingProcedure> getAppliesMethods() {
        return appliesMethods;
    }

    public void setAppliesMethods(STAObjectList<ProcessingProcedure> appliesMethods) {
        this.appliesMethods = appliesMethods;
    }
}