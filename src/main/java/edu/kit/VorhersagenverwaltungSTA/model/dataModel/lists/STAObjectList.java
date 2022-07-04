package edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class STAObjectList<T> {
    @JsonIgnore
    private final ObjectType type;

    @JsonProperty("value")
    protected List<T> list;

    @JsonAlias({"count", "@iot.count", "@count"})
    protected long count;

    public STAObjectList(ObjectType type) {
        this.type = type;
    }

    public STAObjectList(ObjectType type, List<T> list) {
        this.type = type;
        this.list = list;
        this.count = list.size();
    }

    public STAObjectList<T> subList(int startIndex, int endIndex) {
        return new STAObjectList<>(this.type,  this.list.subList(startIndex,endIndex));
    }
    public ObjectType getType() {
        return type;
    }

    public List<T> getList() {
        return List.copyOf(list);
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}