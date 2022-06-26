package edu.kit.VorhersagenverwaltungSTA.controller;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.catalogue.Catalogue;
import edu.kit.VorhersagenverwaltungSTA.service.itemList.CatalogueListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class CatalogueController {
    private final CatalogueListService service;

    CatalogueController(CatalogueListService service) {
        this.service = service;
    }

    @GetMapping("/catalogues/{items}/{page}")
    List<Catalogue> getDataCatalogueList(@PathVariable int items, @PathVariable long page) {
        //TODO: use items and page
        return service.getCatalogues();
    }

    @GetMapping("/datacatalogue/{id}")
    Catalogue getDataCatalogue(@PathVariable long id) {
        //TODO: implement
        return null;
    }
}