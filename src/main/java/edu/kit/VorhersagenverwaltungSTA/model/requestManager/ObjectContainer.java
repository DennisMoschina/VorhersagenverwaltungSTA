package edu.kit.VorhersagenverwaltungSTA.model.requestManager;

import java.util.HashMap;
import java.util.Map;

public class ObjectContainer<T> {
    private final Map<Long, T> objects = new HashMap<>();

    public void add(long id, T object) {
        objects.put(id, object);
    }

    public T get(long id) {
        return objects.get(id);
    }

    public boolean contains(long id) {
        return objects.containsKey(id);
    }

    public void remove(long id) {
        objects.remove(id);
    }
}