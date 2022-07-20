package edu.kit.VorhersagenverwaltungSTA.service.itemList;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.Thing;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.MultiSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * This class is the {@link Service} used to load a list of {@link Thing}.
 *
 * @author Dennis Moschina
 */
@Service
public class ThingListService extends ItemListService<Thing> {
    private static final Set<String> KEYS = Set.of("name", "description");

    @Override
    protected MultiSelection buildSelection(int itemsCount, long startIndex) {
        MultiSelection selection = new MultiSelection(KEYS, ObjectType.THING);
        selection.setCount(itemsCount);
        selection.setSkip(startIndex);
        return selection;
    }
}