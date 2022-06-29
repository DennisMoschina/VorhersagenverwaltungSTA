package edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class STAObjectList<T> {
    private final ObjectType type;

    @JsonProperty("value")
    protected T[] list;

    @JsonProperty("@iot.count")
    protected long count;

    public STAObjectList(ObjectType type) {
        this.type = type;
    }

    public ObjectType getType() {
        return type;
    }
}