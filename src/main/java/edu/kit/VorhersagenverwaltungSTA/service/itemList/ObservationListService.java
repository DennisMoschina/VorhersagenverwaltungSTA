package edu.kit.VorhersagenverwaltungSTA.service.itemList;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.Observation;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.MultiSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;
import org.springframework.stereotype.Service;

/**
 * This class is the {@link Service} used to load a list of {@link Observation}.
 *
 * @author Dennis Moschina
 */
@Service
public class ObservationListService extends ItemListService<Observation> {
    @Override
    protected MultiSelection buildSelection(int itemsCount, long startIndex) {
        MultiSelection selection = new MultiSelection(ObjectType.OBSERVATION);
        selection.setCount(itemsCount);
        selection.setSkip(startIndex);
        selection.setFilter(this.filter);
        return selection;
    }
}