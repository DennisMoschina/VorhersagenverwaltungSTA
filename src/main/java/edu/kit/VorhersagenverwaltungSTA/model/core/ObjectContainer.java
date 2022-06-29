package edu.kit.VorhersagenverwaltungSTA.model.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectContainer<K, V> {
    private final Map<K, V> objects = new HashMap<>();

    public void add(K id, V object) {
        objects.put(id, object);
    }

    public V get(K id) {
        return objects.get(id);
    }

    public boolean contains(K id) {
        return objects.containsKey(id);
    }

    public void remove(K id) {
        objects.remove(id);
    }

    public List<V> getValues() {
        return this.objects.values().stream().toList();
    }

    public void clear() {
        this.objects.clear();
    }
}