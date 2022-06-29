package edu.kit.VorhersagenverwaltungSTA.controller;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.catalogue.Catalogue;
import edu.kit.VorhersagenverwaltungSTA.service.itemList.CatalogueListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class CatalogueController {
    public static final String GET_DATACATALOGUE_LINK = "/datacatalogue/{catalogueId}";
    private final CatalogueListService service;

    CatalogueController(CatalogueListService service) {
        this.service = service;
    }

    @GetMapping("/catalogues/{items}/{page}")
    List<Catalogue> getDataCatalogueList(@PathVariable int items, @PathVariable int page) {
        List<Catalogue> catalogues = this.service.getCatalogues();
        int startIndex = items * page;
        if (startIndex > catalogues.size()) startIndex = (catalogues.size() / items) ;
        int endIndex = Math.min(catalogues.size(), startIndex + items);
        return service.getCatalogues().subList(startIndex, endIndex);
    }

    @GetMapping(GET_DATACATALOGUE_LINK)
    Catalogue getDataCatalogue(@PathVariable int catalogueId) {
        return this.service.getCatalogue(catalogueId);
    }
}