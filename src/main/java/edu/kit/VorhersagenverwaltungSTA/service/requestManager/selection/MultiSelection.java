package edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.filter.Filter;

import java.util.Collection;
import java.util.HashSet;

/**
 * This class represents a {@link Selection} with a List of the Object desired.
 *
 * @author Dennis Moschina
 */
public class MultiSelection extends Selection {
    private int count = 20;
    private long skip = 0;

    private Order orderBy;

    private Filter filter;

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

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    @Override
    public Class<?> getObjectTypeClass() {
        return this.getObjectType().getListClass();
    }
}