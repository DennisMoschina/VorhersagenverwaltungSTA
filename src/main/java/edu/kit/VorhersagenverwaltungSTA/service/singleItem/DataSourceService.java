package edu.kit.VorhersagenverwaltungSTA.service.singleItem;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.DataSource;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.Selection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.SingleSelection;

public class DataSourceService extends SingleItemService<DataSource> {

    @Override
    protected Selection buildSelection(long id) {
        return new SingleSelection(ObjectType.DATASOURCE, id);
    }
}
