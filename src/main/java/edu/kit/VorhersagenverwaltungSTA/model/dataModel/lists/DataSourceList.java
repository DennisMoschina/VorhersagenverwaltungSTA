package edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.DataSource;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;

public class DataSourceList extends STAObjectList<DataSource> {
    public DataSourceList() {
        super(ObjectType.DATASOURCE);
    }
}
