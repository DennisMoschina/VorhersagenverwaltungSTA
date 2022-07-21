package edu.kit.VorhersagenverwaltungSTA.service.singleItem;

import edu.kit.VorhersagenverwaltungSTA.service.AbstractService;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection.DefaultKeysFactory;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection.PrimitiveDefaultKeysFactory;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.MultiSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.Selection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.SingleSelection;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public abstract class SingleItemService<T> extends AbstractService<T> {
    private DefaultKeysFactory defaultKeysFactory = new PrimitiveDefaultKeysFactory();

    @SuppressWarnings("unchecked")
    public void load(long id) {
        final Selection selection = this.buildSelection(id);
        for (ObjectType.Relation relation : selection.getObjectType().getRelations()) {
            Set<String> keys = this.defaultKeysFactory.getDefaultKeys(relation.getObjectType());
            if (relation.isAsList()) {
                selection.addObjectToExpand(new MultiSelection(keys, relation.getObjectType()));
            } else {
                selection.addObjectToExpand(new SingleSelection(keys, relation.getObjectType()));
            }
        }
        this.requestManager.request(selection);
        this.setData((T) this.requestManager.getResult());
    }

    public void setDefaultKeysFactory(DefaultKeysFactory defaultKeysFactory) {
        this.defaultKeysFactory = defaultKeysFactory;
    }

    protected abstract SingleSelection buildSelection(long id);
}