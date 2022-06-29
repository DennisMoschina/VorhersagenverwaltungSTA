package edu.kit.VorhersagenverwaltungSTA.model.dataModel;

import org.springframework.beans.BeanUtils;

public class Entity {

    public void update(Object mergeSource) {
        BeanUtils.copyProperties(mergeSource, this);
    }
}
