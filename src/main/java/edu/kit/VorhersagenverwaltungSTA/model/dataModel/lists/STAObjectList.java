package edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;

import java.util.Arrays;


@JsonIgnoreProperties(ignoreUnknown = true)
public class STAObjectList<T> {
    private final ObjectType type;

    @JsonProperty("value")
    protected T[] list;

    @JsonAlias({"count", "@iot.count", "@count"})
    protected long count;

    public STAObjectList(ObjectType type) {
        this.type = type;
    }

    public ObjectType getType() {
        return type;
    }

    public T[] getList() {
        return Arrays.copyOf(list, list.length);
    }

    public void setList(T[] list) {
        this.list = list;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}