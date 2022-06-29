package edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class Selection {
    private final Set<String> keys;
    private final Set<Selection> objectsToExpand;
    private final ObjectType objectType;

    public Selection(Collection<String> keys, Collection<Selection> objectsToExpand, ObjectType objectType) {
        this.keys = new HashSet<>();
        this.keys.add("id");
        this.keys.addAll(keys);
        this.objectsToExpand = new HashSet<>(objectsToExpand);
        this.objectType = objectType;
    }

    public Selection(Collection<String> keys, ObjectType objectType) {
        this(keys, new HashSet<>(), objectType);
    }

    public Selection(ObjectType objectType) {
        this(new HashSet<>(), objectType);
    }

    public void addKey(String key) {
        keys.add(key);
    }

    public void addObjectToExpand(Selection objectToExpand) {
        objectsToExpand.add(objectToExpand);
    }

    public Set<String> getKeys() {
        return new HashSet<>(this.keys);
    }

    public Set<Selection> getObjectsToExpand() {
        return new HashSet<>(this.objectsToExpand);
    }

    public ObjectType getObjectType() {
        return objectType;
    }

    public boolean hasSelectKeys() {
        return this.keys.size() > 1;
    }

    public Class<?> getObjectTypeClass() {
        return this.objectType.getObjectClass();
    }
}