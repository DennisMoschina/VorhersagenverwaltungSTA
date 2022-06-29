package edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection;

import java.util.Collection;
import java.util.HashSet;

public class MultiSelection extends Selection {
    private int count = 20;
    private long skip = 0;

    private Order orderBy;

    //TODO: implement Filter

    public MultiSelection(Collection<String> keys, Collection<Selection> objectsToExpand, ObjectType objectType) {
        super(keys, objectsToExpand, objectType);
    }

    public MultiSelection(Collection<String> keys, ObjectType objectType) {
        this(keys, new HashSet<>(), objectType);
    }

    public MultiSelection(ObjectType objectType) {
        this(new HashSet<>(), objectType);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getSkip() {
        return skip;
    }

    public void setSkip(long skip) {
        this.skip = skip;
    }

    public Order getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Order orderBy) {
        this.orderBy = orderBy;
    }

    @Override
    public Class<?> getObjectTypeClass() {
        return this.getObjectType().getListClass();
    }
}