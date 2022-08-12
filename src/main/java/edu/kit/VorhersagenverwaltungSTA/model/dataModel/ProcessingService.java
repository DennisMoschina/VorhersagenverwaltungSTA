package edu.kit.VorhersagenverwaltungSTA.model.dataModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    private DataSource[] writesSource;
    @JsonProperty("ReadsSources")
    private DataSource[] readsSources;
    @JsonProperty("AppliesMethods")
    private ProcessingProcedure[] appliesMethods;

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

    public DataSource[] getWritesSource() {
        return writesSource;
    }

    public void setWritesSource(DataSource[] writesSource) {
        this.writesSource = writesSource;
    }

    public DataSource[] getReadsSources() {
        return readsSources;
    }

    public void setReadsSources(DataSource[] readsSources) {
        this.readsSources = readsSources;
    }

    public ProcessingProcedure[] getAppliesMethods() {
        return appliesMethods;
    }

    public void setAppliesMethods(ProcessingProcedure[] appliesMethods) {
        this.appliesMethods = appliesMethods;
    }
}