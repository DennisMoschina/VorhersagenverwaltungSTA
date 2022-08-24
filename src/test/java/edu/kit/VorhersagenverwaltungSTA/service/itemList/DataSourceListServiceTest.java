package edu.kit.VorhersagenverwaltungSTA.service.itemList;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;

public class DataSourceListServiceTest extends AbstractListServiceTest {

    public DataSourceListServiceTest() {
        super(ObjectType.DATASOURCE);
    }

    @Override
    protected void assign() {
        this.service = new DataSourceListService();
    }
}