package edu.kit.VorhersagenverwaltungSTA.service.singleItem;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;

public class ProcessingProcedureServiceTest extends AbstractSingleItemServiceTest {
    public ProcessingProcedureServiceTest() {
        super(ObjectType.PROCESSING_PROCEDURE);
    }

    @Override
    protected void assign() {
        this.service = new ProcessingProcedureService();
    }
}