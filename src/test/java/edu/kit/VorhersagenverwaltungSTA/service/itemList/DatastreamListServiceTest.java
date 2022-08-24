package edu.kit.VorhersagenverwaltungSTA.service.itemList;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;

public class DatastreamListServiceTest extends AbstractListServiceTest {
    public DatastreamListServiceTest() {
        super(ObjectType.DATASTREAM);
    }

    @Override
    protected void assign() {
        this.service = new DatastreamsListService();
    }
}