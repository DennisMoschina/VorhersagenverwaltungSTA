package edu.kit.VorhersagenverwaltungSTA.service.itemList;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.ProcessingService;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection.PrimitiveDefaultKeysFactory;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.MultiSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;
import org.springframework.stereotype.Service;

@Service
public class ServiceListService extends ItemListService<ProcessingService> {

    @Override
    protected MultiSelection buildSelection(int itemsCount, long startIndex) {
        MultiSelection selection = new MultiSelection(
                new PrimitiveDefaultKeysFactory().getDefaultKeys(ObjectType.SERVICE), ObjectType.SERVICE);
        selection.setSkip(startIndex);
        selection.setCount(itemsCount);
        return selection;
    }
}