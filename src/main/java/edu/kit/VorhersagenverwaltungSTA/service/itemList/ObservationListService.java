package edu.kit.VorhersagenverwaltungSTA.service.itemList;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.Observation;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection.PrimitiveDefaultKeysFactory;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.MultiSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * This class is the {@link Service} used to load a list of {@link Observation}.
 *
 * @author Dennis Moschina
 */
@Service
public class ObservationListService extends ItemListService<Observation> {
    private static final Set<String> KEYS = new PrimitiveDefaultKeysFactory().getDefaultKeys(ObjectType.OBSERVATION);

    @Override
    protected MultiSelection buildSelection(int itemsCount, long startIndex) {
        MultiSelection selection = new MultiSelection(KEYS, ObjectType.OBSERVATION);
        selection.setCount(itemsCount);
        selection.setSkip(startIndex);
        selection.setFilter(this.filter);
        return selection;
    }
}