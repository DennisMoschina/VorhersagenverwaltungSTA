package edu.kit.VorhersagenverwaltungSTA.model.core;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * This class describes a Proxy for an {@link ObjectContainer} which only stores a given amount of Objects and
 * replaces them, using the Least-Recently-Used strategy.
 * @param <K> the key type
 * @param <V> the value type
 */
public class CacheProxyObjectContainer<K, V> extends ObjectContainer<K, V> {
    private static final int DEFAULT_CAPACITY = 50;
    private final ObjectContainer<K, V> realContainer;
    private final int capacity;

    private final Queue<K> keyQueue = new LinkedList<>();

    public CacheProxyObjectContainer(ObjectContainer<K, V> realContainer, int capacity) {
        this.realContainer = realContainer;
        this.capacity = capacity;
    }

    public CacheProxyObjectContainer(int capacity) {
        this(new ObjectContainer<>(), capacity);
    }

    public CacheProxyObjectContainer(ObjectContainer<K, V> realContainer) {
        this(realContainer, DEFAULT_CAPACITY);
    }

    public CacheProxyObjectContainer() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public void add(K id, V object) {
        if (this.keyQueue.contains(id)) {
            this.keyQueue.remove(id);
        } else if (this.keyQueue.size() >= this.capacity) {
            K removedKey = this.keyQueue.remove();
            this.remove(removedKey);
        }
        this.keyQueue.add(id);
        realContainer.add(id, object);
    }

    @Override
    public V get(K id) {
        if (this.contains(id)) {
            this.keyQueue.remove(id);
            this.keyQueue.add(id);
        }
        return realContainer.get(id);
    }

    @Override
    public boolean contains(K id) {
        return realContainer.contains(id);
    }

    @Override
    public void remove(K id) {
        realContainer.remove(id);
        this.keyQueue.remove(id);
    }

    @Override
    public List<V> getValues() {
        return realContainer.getValues();
    }

    @Override
    public void clear() {
        realContainer.clear();
    }
}