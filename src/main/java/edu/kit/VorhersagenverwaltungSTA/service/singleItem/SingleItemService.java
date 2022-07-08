package edu.kit.VorhersagenverwaltungSTA.service.singleItem;

import edu.kit.VorhersagenverwaltungSTA.service.AbstractService;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.Selection;
import org.springframework.stereotype.Service;


@Service
public abstract class SingleItemService<T> extends AbstractService<T> {
    @SuppressWarnings("unchecked")
    public void load(long id) {
        final Selection selection = this.buildSelection(id);
        this.requestManager.request(selection);
        this.setData((T) this.requestManager.getResult());
    }

    protected abstract Selection buildSelection(long id);
}