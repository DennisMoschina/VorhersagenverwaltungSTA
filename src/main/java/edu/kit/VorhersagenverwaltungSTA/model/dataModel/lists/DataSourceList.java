package edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.DataSource;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;

/**
 * This class describes a list of {@link DataSource}.
 *
 * @author Elias Dirks
 */
public class DataSourceList extends STAObjectList<DataSource> {
    public DataSourceList() {
        super(ObjectType.DATASOURCE);
    }
}
