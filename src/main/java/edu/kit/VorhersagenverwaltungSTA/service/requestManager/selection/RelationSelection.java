package edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection;

/**
 * This class represents a {@link Selection} based only from a {@link Relation} to an {@link ObjectType object}.
 *
 * @author Dennis Moschina
 */
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