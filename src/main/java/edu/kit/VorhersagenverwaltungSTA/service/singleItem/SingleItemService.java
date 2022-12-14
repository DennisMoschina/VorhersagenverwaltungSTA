package edu.kit.VorhersagenverwaltungSTA.service.singleItem;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.Entity;
import edu.kit.VorhersagenverwaltungSTA.service.AbstractService;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection.DefaultKeysFactory;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection.PrimitiveDefaultKeysFactory;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.MultiSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectAssociatedSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.Relation;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.RelationSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.Selection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.SingleSelection;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * This class represents a service to manage a single {@link Entity object}.
 * Extend this class and override {@link #buildSelection(long)} to load an object.
 *
 * @author Dennis Moschina
 *
 * @param <T> the type of {@link Entity} this Service manages
 */
@Service
public abstract class SingleItemService<T extends Entity> extends AbstractService {
    private DefaultKeysFactory defaultKeysFactory = new PrimitiveDefaultKeysFactory();

    /**
     * Load the {@link Entity} with a specified id.
     *
     * @param id the id of the {@link Entity} to load.
     * @return the {@link Entity} with the specified id
     */
    public T load(long id) {
        final SingleSelection selection = this.buildSelection(id);
        this.updateSelection(selection);
        return this.loadFrom(selection);
    }

    /**
     * Get the object from another object this one is related to.
     *
     * @param associatedObjectType the {@link ObjectType type of object} to load this one from
     * @param id the id of the {@link Entity object} to load this from
     * @return the {@link Entity object} loaded
     */
    public T getFromAssociatedObject(ObjectType associatedObjectType, long id) {
        final SingleSelection sourceSelection = new SingleSelection(associatedObjectType, id);
        final SingleSelection objectSelection = this.buildSelection(-1);
        this.updateSelection(objectSelection);
        final Selection associatedObjectSelection = new ObjectAssociatedSelection(sourceSelection, objectSelection);

        return this.loadFrom(associatedObjectSelection);
    }

    public void setDefaultKeysFactory(DefaultKeysFactory defaultKeysFactory) {
        this.defaultKeysFactory = defaultKeysFactory;
    }

    protected abstract SingleSelection buildSelection(long id);

    private void updateSelection(SingleSelection selection) {
        for (Relation relation : selection.getObjectType().getRelations()) {
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
    private T loadFrom(Selection selection) {
        return (T) this.requestManager.request(selection);
    }
}