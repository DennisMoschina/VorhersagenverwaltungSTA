package edu.kit.VorhersagenverwaltungSTA.controller;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.DataSource;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.ProcessingProcedure;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.catalogue.Catalogue;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.Datastream;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.STAObjectList;
import edu.kit.VorhersagenverwaltungSTA.service.itemList.CatalogueListService;
import edu.kit.VorhersagenverwaltungSTA.service.itemList.DataSourceListService;
import edu.kit.VorhersagenverwaltungSTA.service.itemList.DatastreamsListService;
import edu.kit.VorhersagenverwaltungSTA.service.itemList.ProcessingProcedureListService;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.Source;
import edu.kit.VorhersagenverwaltungSTA.service.singleItem.DataSourceService;
import edu.kit.VorhersagenverwaltungSTA.service.singleItem.DatastreamService;
import edu.kit.VorhersagenverwaltungSTA.service.singleItem.ProcessingProcedureService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationRestController {
    private static final String GET_DATACATALOGUE_LINK = "/catalogue/{catalogueId}";
    private static final String GET_CATALOGUE_LIST_LINK = "/catalogues/{items}/{page}";
    private static final String GET_DATASOURCE_LINK = GET_DATACATALOGUE_LINK + "/datasource/{dataSourceId}";
    private static final String GET_DATASOURCE_LIST_LINK = GET_DATACATALOGUE_LINK + "/datasources/{items}/{page}";
    private static final String GET_DATASTREAM_LINK = GET_DATASOURCE_LINK + "/datastream/{datastreamID}";
    private static final String GET_DATASTREAM_LIST_LINK = GET_DATASOURCE_LINK + "/datastreams/{items}/{page}";
    private static final String GET_PROCESSING_PROCEDURE_LINK
            = GET_DATACATALOGUE_LINK + "/processingprocedure/{processingProcedureID}";
    private static final String GET_PROCESSING_PROCEDURE_LIST_LINK
            = GET_DATACATALOGUE_LINK + "/processingprocedures/{items}/{page}";

    private final CatalogueListService catalogueListService;
    private final DataSourceListService dataSourceListService;
    private final DataSourceService dataSourceService;
    private final DatastreamsListService datastreamsListService;
    private final DatastreamService datastreamService;
    private final ProcessingProcedureListService processingProcedureListService;
    private final ProcessingProcedureService processingProcedureService;

    public ApplicationRestController(CatalogueListService catalogueListService,
                                     DataSourceListService dataSourceListService,
                                     DataSourceService dataSourceService,
                                     DatastreamsListService datastreamsListService,
                                     DatastreamService datastreamService,
                                     ProcessingProcedureListService processingProcedureListService,
                                     ProcessingProcedureService processingProcedureService) {
        this.catalogueListService = catalogueListService;
        this.dataSourceListService = dataSourceListService;
        this.dataSourceService = dataSourceService;
        this.datastreamsListService = datastreamsListService;
        this.datastreamService = datastreamService;
        this.processingProcedureListService = processingProcedureListService;
        this.processingProcedureService = processingProcedureService;
    }


    @GetMapping(GET_CATALOGUE_LIST_LINK)
    STAObjectList<Catalogue> getCatalogueList(@PathVariable int items,
                                              @PathVariable int page) {
        STAObjectList<Catalogue> catalogues = this.catalogueListService.getCatalogues();
        //page starts at 0; items and page should normally be correct
        int startIndex = items * page;
        if (startIndex > catalogues.getList().size()) startIndex = (catalogues.getList().size() / items) ;
        int endIndex = Math.min(catalogues.getList().size(), startIndex + items);
        return catalogueListService.getCatalogues().subList(startIndex, endIndex);
    }

    @GetMapping(GET_DATACATALOGUE_LINK)
    Catalogue getCatalogue(@PathVariable int catalogueId) {
        return this.catalogueListService.getCatalogue(catalogueId);
    }

    @GetMapping(GET_DATASOURCE_LINK)
    DataSource getDataSource(@PathVariable long dataSourceId,
                             @PathVariable int catalogueId) {
        Catalogue catalogue = this.getCatalogue(catalogueId);
        this.dataSourceService.setSource(new Source(catalogue.getUrl()));

        dataSourceService.load(dataSourceId);
        return dataSourceService.getData();
    }

    @GetMapping(GET_DATASOURCE_LIST_LINK)
    public STAObjectList<DataSource> getDataSourceList(@PathVariable int items,
                                                       @PathVariable long page,
                                                       @PathVariable int catalogueId) {
        Catalogue catalogue = this.getCatalogue(catalogueId);
        this.dataSourceListService.setSource(new Source(catalogue.getUrl()));

        this.dataSourceListService.load(items, this.calculateStartIndex(items, page));
        return this.dataSourceListService.getData();
    }

    @GetMapping(GET_DATASTREAM_LINK)
    public Datastream getDatastream(@PathVariable long datastreamID,
                                    @PathVariable int catalogueId,
                                    @PathVariable long dataSourceId) {
        DataSource dataSource = this.getDataSource(dataSourceId, catalogueId);
        this.datastreamService.setSource(dataSource.getAccessData());

        datastreamService.load(datastreamID);
        return datastreamService.getData();
    }

    @GetMapping(GET_DATASTREAM_LIST_LINK)
    public STAObjectList<Datastream> getDatastreamList(@PathVariable int items,
                                                       @PathVariable long page,
                                                       @PathVariable int catalogueId,
                                                       @PathVariable long dataSourceId) {
        DataSource dataSource = this.getDataSource(dataSourceId, catalogueId);
        this.datastreamsListService.setSource(dataSource.getAccessData());


        this.datastreamsListService.load(items, this.calculateStartIndex(items, page));
        return this.datastreamsListService.getData();
    }

    @GetMapping(GET_PROCESSING_PROCEDURE_LINK)
    ProcessingProcedure getProcessingProcedure(@PathVariable long processingProcedureID,
                             @PathVariable int catalogueId) {
        Catalogue catalogue = this.getCatalogue(catalogueId);
        this.processingProcedureService.setSource(new Source(catalogue.getUrl()));

        processingProcedureService.load(processingProcedureID);
        return processingProcedureService.getData();
    }

    @GetMapping(GET_PROCESSING_PROCEDURE_LIST_LINK)
    public STAObjectList<ProcessingProcedure> getProcessingProcedureList(@PathVariable int items,
                                                                @PathVariable long page,
                                                                @PathVariable int catalogueId) {
        Catalogue catalogue = this.getCatalogue(catalogueId);
        this.processingProcedureListService.setSource(new Source(catalogue.getUrl()));

        this.processingProcedureListService.load(items, this.calculateStartIndex(items, page));
        return this.processingProcedureListService.getData();
    }

    private long calculateStartIndex(int items, long page) {
        return items * page;
    }
}