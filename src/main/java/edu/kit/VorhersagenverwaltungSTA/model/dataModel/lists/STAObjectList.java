package edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;

import java.util.List;

/**
 * This class is used to construct lists of a specific {@link ObjectType}.
 *
 * @param <T> the type parameter for the list
 * @author Elias Dirks, Dennis Moschina
 */
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

    /**
     * Returns a {@link STAObjectList} of the same type containing only the elements as specified in
     * {@link List#subList(int, int)}.
     *
     * @param startIndex the startIndex of the list (inclusive)
     * @param endIndex the endIndex of the list (exclusive)
     * @return a sublist of this STAObjectList
     */
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

    public STAObjectList<T> copyOf(){
        return new STAObjectList<>(this.type, this.getList());
    }
}