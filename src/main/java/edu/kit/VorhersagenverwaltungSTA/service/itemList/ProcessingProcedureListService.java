package edu.kit.VorhersagenverwaltungSTA.service.itemList;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.ProcessingProcedure;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.MultiSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * This class is the {@link Service} used to load a list of {@link ProcessingProcedure}.
 *
 * @author Elias Dirks
 */
@Service
public class ProcessingProcedureListService extends ItemListService<ProcessingProcedure>{

    private static final Set<String> KEYS = Set.of(NAME_KEY, DESCRIPTION_KEY);
    @Override
    protected MultiSelection buildSelection(int itemsCount, long startIndex) {
        MultiSelection newSelection = new MultiSelection(KEYS, ObjectType.PROCESSING_PROCEDURE);
        newSelection.setCount(itemsCount);
        newSelection.setSkip(startIndex);
        return newSelection;
    }
}
