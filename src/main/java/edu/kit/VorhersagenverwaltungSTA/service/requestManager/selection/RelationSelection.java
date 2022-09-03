package edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection;

public class RelationSelection extends Selection {
    private final Relation relation;
    private final Selection selection;

    public RelationSelection(Selection selection, Relation relation) {
        super(selection.getObjectType());
        this.relation = relation;
        this.selection = selection;
    }

    public Relation getRelation() {
        return relation;
    }

    public Selection getSelection() {
        return selection;
    }
}