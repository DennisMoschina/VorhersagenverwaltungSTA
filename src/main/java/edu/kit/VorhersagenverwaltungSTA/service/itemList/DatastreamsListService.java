package edu.kit.VorhersagenverwaltungSTA.service.itemList;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.catalogue.Catalogue;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.Datastream;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.MultiSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * This class is the {@link Service} used to load a list of {@link Datastream}.
 *
 * @author Dennis Moschina
 */
@Service
public class DatastreamsListService extends ItemListService<Datastream> {
    private static final Set<String> KEYS = Set.of(NAME_KEY, DESCRIPTION_KEY);
    @Override
    protected MultiSelection buildSelection(int itemsCount, long startIndex) {
        MultiSelection newSelection = new MultiSelection(KEYS, ObjectType.DATASTREAM);
        newSelection.setCount(itemsCount);
        newSelection.setSkip(startIndex);
        newSelection.setFilter(this.filter);
        return newSelection;
    }
}