package edu.kit.VorhersagenverwaltungSTA.service.requestManager;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.catalogue.Catalogue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogueLoader {
    private List<Catalogue> catalogues;

    private final List<String> urls;

    public CatalogueLoader(@Value("${data.catalogue.list}") List<String> urls) {
        this.urls = urls;
    }

    public void loadCatalogues() {
        this.catalogues = this.urls.stream().map(Catalogue::new).toList();
    }

    public List<Catalogue> getCatalogues() {
        return List.copyOf(this.catalogues);
    }
}