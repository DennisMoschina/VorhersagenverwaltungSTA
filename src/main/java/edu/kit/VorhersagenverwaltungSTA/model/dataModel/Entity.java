package edu.kit.VorhersagenverwaltungSTA.model.dataModel;

import com.fasterxml.jackson.annotation.JsonAlias;
import org.springframework.beans.BeanUtils;

/**
 * This class describes an Entity of the SensorThingsAPI.
 *
 * @author Elias Dirks
 */
public class Entity {

    @JsonAlias({"id", "@iot.id"})
    public long id;

    /**
     * Updates the attributes of this entity with the given one.
     *
     * @param mergeSource the object whose attributes are used to update this ones
     */
    public void update(Object mergeSource) {
        BeanUtils.copyProperties(mergeSource, this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
