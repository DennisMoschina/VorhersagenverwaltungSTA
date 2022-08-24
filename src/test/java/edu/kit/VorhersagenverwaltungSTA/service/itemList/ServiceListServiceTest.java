package edu.kit.VorhersagenverwaltungSTA.service.itemList;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;

public class ServiceListServiceTest extends AbstractListServiceTest {
    public ServiceListServiceTest() {
        super(ObjectType.SERVICE);
    }

    @Override
    protected void assign() {
        this.service = new ServiceListService();
    }
}