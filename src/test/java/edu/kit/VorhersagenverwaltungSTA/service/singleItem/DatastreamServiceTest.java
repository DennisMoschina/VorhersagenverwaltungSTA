package edu.kit.VorhersagenverwaltungSTA.service.singleItem;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;

public class DatastreamServiceTest extends  AbstractServiceTest {
    public DatastreamServiceTest() {
        super(ObjectType.DATASTREAM);
    }

    @Override
    protected void assign() {
        this.service = new DatastreamService();
    }
}