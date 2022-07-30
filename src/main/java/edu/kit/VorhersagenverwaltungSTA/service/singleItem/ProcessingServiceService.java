package edu.kit.VorhersagenverwaltungSTA.service.singleItem;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.ProcessingService;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.SingleSelection;
import org.springframework.stereotype.Service;

@Service
public class ProcessingServiceService extends SingleItemService<ProcessingService> {
    @Override
    protected SingleSelection buildSelection(long id) {
        return new SingleSelection(ObjectType.SERVICE, id);
    }
}