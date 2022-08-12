package edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection;

public class RelationSelection extends Selection {
    private final ObjectType.Relation relation;
    private final Selection selection;

    public RelationSelection(Selection selection, ObjectType.Relation relation) {
        super(selection.getObjectType());
        this.relation = relation;
        this.selection = selection;
    }

    public ObjectType.Relation getRelation() {
        return relation;
    }

    public Selection getSelection() {
        return selection;
    }
}