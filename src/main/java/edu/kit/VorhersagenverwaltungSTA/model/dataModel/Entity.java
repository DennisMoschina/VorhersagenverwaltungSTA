package edu.kit.VorhersagenverwaltungSTA.model.dataModel;

import com.fasterxml.jackson.annotation.JsonAlias;
import org.springframework.beans.BeanUtils;

public class Entity {

    @JsonAlias({"id", "@iot.id"})
    public long id;
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
