package edu.kit.VorhersagenverwaltungSTA.model.requestManager.selection;

import java.util.Set;

public class MultiSelection extends Selection {
    private int count = 20;
    private int skip = 0;

    //TODO: implement Filter

    public MultiSelection(Set<String> keys, Set<Selection> objectsToExpand, ObjectType objectType) {
        super(keys, objectsToExpand, objectType);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }
}