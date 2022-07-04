package edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.catalogue.Catalogue;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;

public class CatalogueList extends STAObjectList<Catalogue> {
    public CatalogueList() {
        super(ObjectType.CATALOGUE);
    }
}
