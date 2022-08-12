package edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection;

import java.util.Set;

/**
 * This class is a {@link Selection} for Objects associated with another Object.
 * @author Dennis Moschina
 */
public class ObjectAssociatedSelection extends Selection {
    private final SingleSelection sourceObjectSelection;
    private final Selection selection;

    private final ObjectType.Relation relation;

    public ObjectAssociatedSelection(SingleSelection sourceObject, Selection selection) {
        this(sourceObject, selection, (ObjectType.Relation) null);
    }
    public ObjectAssociatedSelection(SingleSelection sourceObject, Selection selection, String relationName) {
        this(sourceObject,
                selection,
                sourceObject.getObjectType().getRelations().stream()
                        .filter(r -> relationName.equalsIgnoreCase(r.getName()))
                        .findFirst().orElse(null));
    }

    public ObjectAssociatedSelection(SingleSelection sourceObject, Selection selection, ObjectType.Relation relation) {
        super(selection.getObjectType());

        this.sourceObjectSelection = sourceObject;
        this.selection = selection;
        this.relation = relation;

        if (relation != null) {
            if (relation.isAsList() && !(selection instanceof MultiSelection)
                    || !relation.isAsList() && selection instanceof MultiSelection)
                throw new IllegalArgumentException("the relation and selection do not match");
        }
    }


    public SingleSelection getSourceObjectSelection() {
        return sourceObjectSelection;
    }

    public Selection getSelection() {
        return selection;
    }

    public ObjectType.Relation getRelation() {
        return relation;
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