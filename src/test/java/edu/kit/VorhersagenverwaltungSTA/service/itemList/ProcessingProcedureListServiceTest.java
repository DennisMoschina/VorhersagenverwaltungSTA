package edu.kit.VorhersagenverwaltungSTA.service.itemList;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;

public class ProcessingProcedureListServiceTest extends AbstractListServiceTest {
    public ProcessingProcedureListServiceTest() {
        super(ObjectType.PROCESSING_PROCEDURE);
    }

    @Override
    protected void assign() {
        this.service = new ProcessingProcedureListService();
    }
}