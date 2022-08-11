package edu.kit.VorhersagenverwaltungSTA.service.itemList;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.Location;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection.PrimitiveDefaultKeysFactory;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.MultiSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class LocationListService extends ItemListService<Location> {
    private static final Set<String> KEYS = new PrimitiveDefaultKeysFactory().getDefaultKeys(ObjectType.LOCATION);

    @Override
    protected MultiSelection buildSelection(int itemsCount, long startIndex) {
        MultiSelection selection = new MultiSelection(KEYS, ObjectType.LOCATION);
        selection.setCount(itemsCount);
        selection.setSkip(startIndex);
        selection.setFilter(this.filter);
        return selection;
    }
}