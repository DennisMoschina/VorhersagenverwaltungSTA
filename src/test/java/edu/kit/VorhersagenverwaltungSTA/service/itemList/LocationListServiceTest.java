package edu.kit.VorhersagenverwaltungSTA.service.itemList;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;

public class LocationListServiceTest extends AbstractListServiceTest {
    public LocationListServiceTest() {
        super(ObjectType.LOCATION);
    }

    @Override
    protected void assign() {
        this.service = new LocationListService();
    }
}