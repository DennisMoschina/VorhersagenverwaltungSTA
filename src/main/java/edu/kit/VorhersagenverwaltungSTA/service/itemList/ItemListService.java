package edu.kit.VorhersagenverwaltungSTA.service.itemList;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.STAObjectList;
import edu.kit.VorhersagenverwaltungSTA.service.AbstractService;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.MultiSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.filter.Filter;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public abstract class ItemListService<T> extends AbstractService<STAObjectList<T>> {
    protected List<Filter> filter = new LinkedList<>();

    @SuppressWarnings("unchecked")
    public void load(int itemsCount, long startIndex) {
        final MultiSelection selection = this.buildSelection(itemsCount, startIndex);
        this.requestManager.request(selection);
        this.setData((STAObjectList<T>) this.requestManager.getResult());
    }

    public void addFilter(Filter filter) {
        this.filter.add(filter);
    }

    public void addFilter(List<Filter> filter) {
        this.filter.addAll(filter);
    }

    public void removeFilter(Filter filter) {
        this.filter.remove(filter);
    }

    public void removeFilter(List<Filter> filter) {
        this.filter.removeAll(filter);
    }

    protected abstract MultiSelection buildSelection(int itemsCount, long startIndex);
}