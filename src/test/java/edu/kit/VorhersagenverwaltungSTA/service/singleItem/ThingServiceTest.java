package edu.kit.VorhersagenverwaltungSTA.service.singleItem;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;

public class ThingServiceTest extends AbstractSingleItemServiceTest {
    public ThingServiceTest() {
        super(ObjectType.THING);
    }

    @Override
    protected void assign() {
        this.service = new ThingService();
    }
}