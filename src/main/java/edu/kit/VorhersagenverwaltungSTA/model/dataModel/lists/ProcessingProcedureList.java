package edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.ProcessingProcedure;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;

/**
 * This class describes a list of {@link ProcessingProcedure}.
 *
 * @author Elias Dirks
 */
public class ProcessingProcedureList extends STAObjectList<ProcessingProcedure> {

    public ProcessingProcedureList() {
        super(ObjectType.PROCESSING_PROCEDURE);
    }
}
