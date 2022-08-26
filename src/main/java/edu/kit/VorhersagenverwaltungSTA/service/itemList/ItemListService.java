package edu.kit.VorhersagenverwaltungSTA.service.itemList;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.STAObjectList;
import edu.kit.VorhersagenverwaltungSTA.service.AbstractService;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.MultiSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectAssociatedSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.Selection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.SingleSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.filter.Filter;
import org.springframework.stereotype.Service;

/**
 * This class is the {@link Service} used to load a list of a specific type.
 * Extend this class and override {@link #buildSelection(int, long)} to load a list of an object.
 *
 * @author Dennis Moschina
 */
@Service
public abstract class ItemListService<T> extends AbstractService {
    protected static final String NAME_KEY = "name";
    protected static final String DESCRIPTION_KEY = "description";
    protected Filter filter;

    public STAObjectList<T> load(int itemsCount, long startIndex) {
        final MultiSelection selection = this.buildSelection(itemsCount, startIndex);
        return this.loadFrom(selection);
    }

    public STAObjectList<T> getFromAssociatedObject(ObjectType associatedObjectType,
                                        long id,
                                        int itemsCount,
                                        long startIndex,
                                        String associationName) {
        final SingleSelection sourceSelection = new SingleSelection(associatedObjectType, id);
        final Selection objectSelection = this.buildSelection(itemsCount, startIndex);
        final Selection associatedObjectSelection = associationName != null ?
                new ObjectAssociatedSelection(sourceSelection, objectSelection, associationName)
                : new ObjectAssociatedSelection(sourceSelection, objectSelection);

        return this.loadFrom(associatedObjectSelection);
    }

    public STAObjectList<T> getFromAssociatedObject(ObjectType associatedObjectType, long id, int itemsCount, long startIndex) {
        return this.getFromAssociatedObject(associatedObjectType, id, itemsCount, startIndex, null);
    }

    public void addFilter(Filter filter) {
        this.filter = filter;
    }

    public void removeFilter() {
        this.filter = null;
    }

    @SuppressWarnings("unchecked")
    private STAObjectList<T> loadFrom(Selection selection) {
        return (STAObjectList<T>) this.requestManager.request(selection);
    }

    protected abstract MultiSelection buildSelection(int itemsCount, long startIndex);
}