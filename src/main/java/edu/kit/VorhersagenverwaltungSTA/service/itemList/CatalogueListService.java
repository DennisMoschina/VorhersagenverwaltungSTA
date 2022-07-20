package edu.kit.VorhersagenverwaltungSTA.service.itemList;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.catalogue.Catalogue;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.STAObjectList;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.CatalogueLoader;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class is the {@link Service} used to load a list of {@link Catalogue}.
 *
 * @author Elias Dirks, Dennis Moschina
 */
@Service
public class CatalogueListService {
    private final CatalogueLoader catalogueLoader;

    public CatalogueListService(CatalogueLoader catalogueLoader) {
        this.catalogueLoader = catalogueLoader;
    }

    public STAObjectList<Catalogue> getCatalogues() {
        return this.catalogueLoader.getCatalogues();
    }

    public Catalogue getCatalogue(int id) {
        return this.catalogueLoader.getCatalogue(id);
    }
}