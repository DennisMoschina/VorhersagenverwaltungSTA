package edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.catalogue.Catalogue;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;

/**
 * This class describes a list of {@link Catalogue}.
 *
 * @author Elias Dirks
 */
public class CatalogueList extends STAObjectList<Catalogue> {
    public CatalogueList() {
        super(ObjectType.CATALOGUE);
    }
}
