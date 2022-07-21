package edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection;

import java.util.HashSet;
import java.util.Set;

public class SingleSelection extends Selection {
    private final long selectedId;

    public SingleSelection(Set<String> keys, ObjectType type, long selectedId) {
        super(keys, type);
        this.selectedId = selectedId;
    }

    public SingleSelection(ObjectType type, long selectedId) {
        this(new HashSet<>(), type, selectedId);
    }

    public SingleSelection(Set<String> keys, ObjectType type) {
        this(keys, type, -1);
    }

    public SingleSelection(ObjectType type) {
        this(type, -1);
    }

    public long getSelectedId() {
        return selectedId;
    }
}