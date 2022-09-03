package edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection.PluralObjectTypeEncoder;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection.SingularObjectTypeEncoder;

public class Relation {
    private final boolean asList;
    private final ObjectType objectType;
    private final String name;

    public Relation(ObjectType objectType) {
        this(false, objectType);
    }

    public Relation(boolean asList, ObjectType objectType) {
        this(asList,
                objectType,
                (asList ? new PluralObjectTypeEncoder() : new SingularObjectTypeEncoder()).encode(objectType));
    }

    public Relation(ObjectType objectType, String name) {
        this(false, objectType, name);
    }

    public Relation(boolean asList, ObjectType objectType, String name) {
        this.asList = asList;
        this.objectType = objectType;
        this.name = name;
    }

    public boolean isAsList() {
        return asList;
    }

    public ObjectType getObjectType() {
        return objectType;
    }

    public String getName() {
        return name;
    }
}