package edu.kit.VorhersagenverwaltungSTA.service.singleItem;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;

public class DataSourceServiceTest extends AbstractServiceTest {
    public DataSourceServiceTest() {
        super(ObjectType.DATASOURCE);
    }

    @Override
    protected void assign() {
        this.service = new DataSourceService();
    }
}