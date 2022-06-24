package edu.kit.VorhersagenverwaltungSTA.service.itemList;

import edu.kit.VorhersagenverwaltungSTA.service.AbstractService;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.MultiSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.filter.Filter;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public abstract class ItemListService<T> extends AbstractService<List<T>> {
    protected List<Filter> filter = new LinkedList<>();

    public void load() {
        final MultiSelection selection = this.buildSelection();
        this.requestManager.request(selection);
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

    protected abstract MultiSelection buildSelection();
}