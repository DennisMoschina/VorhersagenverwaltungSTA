package edu.kit.VorhersagenverwaltungSTA.service.itemList;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;

public class ThingListServiceTest extends AbstractListServiceTest {
    public ThingListServiceTest() {
        super(ObjectType.THING);
    }

    @Override
    protected void assign() {
        this.service = new ThingListService();
    }
}