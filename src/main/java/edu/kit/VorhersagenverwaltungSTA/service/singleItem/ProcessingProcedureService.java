package edu.kit.VorhersagenverwaltungSTA.service.singleItem;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.ProcessingProcedure;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.SingleSelection;
import org.springframework.stereotype.Service;

@Service
public class ProcessingProcedureService extends SingleItemService<ProcessingProcedure> {

    @Override
    protected SingleSelection buildSelection(long id) {
        return new SingleSelection(ObjectType.PROCESSING_PROCEDURE, id);
    }
}