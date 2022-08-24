package edu.kit.VorhersagenverwaltungSTA.service.singleItem;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;

public class ProcessingServiceServiceTest extends AbstractSingleItemServiceTest {
    public ProcessingServiceServiceTest() {
        super(ObjectType.SERVICE);
    }

    @Override
    protected void assign() {
        this.service = new ProcessingServiceService();
    }
}