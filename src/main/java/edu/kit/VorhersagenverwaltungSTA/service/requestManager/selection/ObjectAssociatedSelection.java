package edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection;

import java.util.Set;

/**
 * This class is a {@link Selection} for Objects associated with another Object.
 * @author Dennis Moschina
 */
public class ObjectAssociatedSelection extends Selection {
    private final SingleSelection sourceObjectSelection;
    private final Selection selection;

    public ObjectAssociatedSelection(SingleSelection sourceObject, Selection selection) {
        super(null);

        this.sourceObjectSelection = sourceObject;
        this.selection = selection;
    }

    public SingleSelection getSourceObjectSelection() {
        return sourceObjectSelection;
    }

    public Selection getSelection() {
        return selection;
    }

    @Override
    public void addKey(String key) {
        selection.addKey(key);
    }

    @Override
    public void addObjectToExpand(Selection objectToExpand) {
        selection.addObjectToExpand(objectToExpand);
    }

    @Override
    public Set<String> getKeys() {
        return selection.getKeys();
    }

    @Override
    public Set<Selection> getObjectsToExpand() {
        return selection.getObjectsToExpand();
    }

    @Override
    public ObjectType getObjectType() {
        return selection.getObjectType();
    }

    @Override
    public boolean hasSelectKeys() {
        return selection.hasSelectKeys();
    }

    @Override
    public Class<?> getObjectTypeClass() {
        return selection.getObjectTypeClass();
    }
}