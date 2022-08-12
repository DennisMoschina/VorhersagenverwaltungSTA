package edu.kit.VorhersagenverwaltungSTA.service.singleItem;

import edu.kit.VorhersagenverwaltungSTA.service.AbstractService;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection.DefaultKeysFactory;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection.PrimitiveDefaultKeysFactory;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.MultiSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectAssociatedSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.RelationSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.Selection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.SingleSelection;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public abstract class SingleItemService<T> extends AbstractService<T> {
    private DefaultKeysFactory defaultKeysFactory = new PrimitiveDefaultKeysFactory();

    public void load(long id) {
        final SingleSelection selection = this.buildSelection(id);
        this.updateSelection(selection);
        this.loadFrom(selection);
    }

    public void getFromAssociatedObject(ObjectType associatedObjectType, long id) {
        final SingleSelection sourceSelection = new SingleSelection(associatedObjectType, id);
        final SingleSelection objectSelection = this.buildSelection(-1);
        this.updateSelection(objectSelection);
        final Selection associatedObjectSelection = new ObjectAssociatedSelection(sourceSelection, objectSelection);

        this.loadFrom(associatedObjectSelection);
    }

    public void setDefaultKeysFactory(DefaultKeysFactory defaultKeysFactory) {
        this.defaultKeysFactory = defaultKeysFactory;
    }

    protected abstract SingleSelection buildSelection(long id);

    private void updateSelection(SingleSelection selection) {
        for (ObjectType.Relation relation : selection.getObjectType().getRelations()) {
            Set<String> keys = this.defaultKeysFactory.getDefaultKeys(relation.getObjectType());
            if (relation.getName() != null) {
                if (relation.isAsList()) {
                    selection.addObjectToExpand(
                            new RelationSelection(new MultiSelection(keys, relation.getObjectType()), relation));
                } else {
                    selection.addObjectToExpand(
                            new RelationSelection(new SingleSelection(keys, relation.getObjectType()), relation));
                }
            } else {
                if (relation.isAsList()) {
                    selection.addObjectToExpand(new MultiSelection(keys, relation.getObjectType()));
                } else {
                    selection.addObjectToExpand(new SingleSelection(keys, relation.getObjectType()));
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void loadFrom(Selection selection) {
        this.requestManager.request(selection);
        this.setData((T) this.requestManager.getResult());
    }
}