package edu.kit.VorhersagenverwaltungSTA.service.itemList;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.catalogue.Catalogue;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.CatalogueLoader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogueListService {
    private final CatalogueLoader catalogueLoader;

    public CatalogueListService(CatalogueLoader catalogueLoader) {
        this.catalogueLoader = catalogueLoader;
    }

    public List<Catalogue> getCatalogues() {
        this.catalogueLoader.loadCatalogues();
        return this.catalogueLoader.getCatalogues();
    }
}