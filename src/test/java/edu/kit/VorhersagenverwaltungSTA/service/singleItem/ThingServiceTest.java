package edu.kit.VorhersagenverwaltungSTA.service.singleItem;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;

public class ThingServiceTest extends AbstractServiceTest {
    public ThingServiceTest() {
        super(ObjectType.THING);
    }

    @Override
    protected void assign() {
        this.service = new ThingService();
    }
}