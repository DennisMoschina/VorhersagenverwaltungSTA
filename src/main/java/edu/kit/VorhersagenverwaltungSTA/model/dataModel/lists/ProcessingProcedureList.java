package edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.ProcessingProcedure;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;

public class ProcessingProcedureList extends STAObjectList<ProcessingProcedure> {

    public ProcessingProcedureList() {
        super(ObjectType.PROCESSING_PROCEDURE);
    }
}
