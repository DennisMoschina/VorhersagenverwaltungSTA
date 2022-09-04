package edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;

import java.util.Set;

public class PrimitiveDefaultKeysFactory implements DefaultKeysFactory {
    public Set<String> getDefaultKeys(ObjectType objectType) {
        Set<String> keys;
        switch (objectType) {
            case OBSERVATION -> keys = Set.of("result", "phenomenonTime");
            case HISTORICAL_LOCATION -> keys = Set.of("time");
            case LOCATION -> keys = Set.of("name", "description", "location");
            default -> keys = Set.of("name", "description");
        }
        return keys;
    }
}