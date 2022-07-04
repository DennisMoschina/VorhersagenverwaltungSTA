package edu.kit.VorhersagenverwaltungSTA.controller;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.catalogue.Catalogue;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.STAObjectList;
import edu.kit.VorhersagenverwaltungSTA.service.itemList.CatalogueListService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
class CatalogueController {
    public static final String GET_DATACATALOGUE_LINK = "/datacatalogue/{catalogueId}";
    private final CatalogueListService service;

    CatalogueController(CatalogueListService service) {
        this.service = service;
    }

    @GetMapping("/catalogues/{items}/{page}")
    STAObjectList<Catalogue> getDataCatalogueList(@PathVariable int items, @PathVariable int page) {
        STAObjectList<Catalogue> catalogues = this.service.getCatalogues();
        //page starts at 0; items and page should normally be correct
        int startIndex = items * page;
        if (startIndex > catalogues.getList().size()) startIndex = (catalogues.getList().size() / items) ;
        int endIndex = Math.min(catalogues.getList().size(), startIndex + items);
        return service.getCatalogues().subList(startIndex, endIndex);
    }

    @GetMapping(GET_DATACATALOGUE_LINK)
    Catalogue getDataCatalogue(@PathVariable int catalogueId) {
        return this.service.getCatalogue(catalogueId);
    }
}