package edu.kit.VorhersagenverwaltungSTA.controller;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.DataSource;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.ProcessingProcedure;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.catalogue.Catalogue;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.Datastream;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.Observation;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.Thing;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.STAObjectList;
import edu.kit.VorhersagenverwaltungSTA.service.itemList.CatalogueListService;
import edu.kit.VorhersagenverwaltungSTA.service.itemList.DataSourceListService;
import edu.kit.VorhersagenverwaltungSTA.service.itemList.DatastreamsListService;
import edu.kit.VorhersagenverwaltungSTA.service.itemList.ObservationListService;
import edu.kit.VorhersagenverwaltungSTA.service.itemList.ProcessingProcedureListService;
import edu.kit.VorhersagenverwaltungSTA.service.itemList.ThingListService;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.Source;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.filter.FrostRequestFilter;
import edu.kit.VorhersagenverwaltungSTA.service.singleItem.DataSourceService;
import edu.kit.VorhersagenverwaltungSTA.service.singleItem.DatastreamService;
import edu.kit.VorhersagenverwaltungSTA.service.singleItem.ProcessingProcedureService;
import edu.kit.VorhersagenverwaltungSTA.service.singleItem.ThingService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:4200")
public class ApplicationRestController {
    private static final String LIST_ARGS = "/{items}/{page}";
    private static final String FILTER_ARG = "/{filter}";

    private static final String GET_DATACATALOGUE_LINK = "/catalogue/{catalogueId}";
    private static final String GET_CATALOGUE_LIST_LINK = "/catalogues" + LIST_ARGS;
    private static final String GET_DATASOURCE_LINK = GET_DATACATALOGUE_LINK + "/datasource/{dataSourceId}";
    private static final String GET_DATASOURCE_LIST_LINK = GET_DATACATALOGUE_LINK + "/datasources" + LIST_ARGS;
    private static final String GET_DATASTREAM_LINK = GET_DATASOURCE_LINK + "/datastream/{datastreamID}";
    private static final String GET_DATASTREAM_LIST_LINK = GET_DATASOURCE_LINK + "/datastreams" + LIST_ARGS;
    private static final String GET_THING_LIST_LINK = GET_DATASOURCE_LINK + "/things" + LIST_ARGS;
    private static final String GET_THING_LINK = GET_DATASOURCE_LINK + "/thing/{thingId}";
    private static final String GET_PROCESSING_PROCEDURE_LINK
            = GET_DATACATALOGUE_LINK + "/processingprocedure/{processingProcedureID}";
    private static final String GET_PROCESSING_PROCEDURE_LIST_LINK
            = GET_DATACATALOGUE_LINK + "/processingprocedures" + LIST_ARGS;

    private static final String GET_DATASTREAM_LIST_FROM_THING_LINK = GET_THING_LINK + "/datastreams" + LIST_ARGS;
    private static final String GET_THING_OF_STREAM_LINK = GET_DATASTREAM_LINK + "/thing";
    private static final String GET_OBSERVATIONS_LINK = GET_DATASTREAM_LINK + "/observations" + LIST_ARGS;

    private final CatalogueListService catalogueListService;
    private final DataSourceListService dataSourceListService;
    private final DataSourceService dataSourceService;
    private final DatastreamsListService datastreamsListService;
    private final DatastreamService datastreamService;
    private final ThingListService thingListService;
    private final ThingService thingService;
    private final ProcessingProcedureListService processingProcedureListService;
    private final ProcessingProcedureService processingProcedureService;
    private final ObservationListService observationListService;

    public ApplicationRestController(CatalogueListService catalogueListService,
                                     DataSourceListService dataSourceListService,
                                     DataSourceService dataSourceService,
                                     DatastreamsListService datastreamsListService,
                                     DatastreamService datastreamService,
                                     ThingListService thingListService,
                                     ThingService thingService,
                                     ProcessingProcedureListService processingProcedureListService,
                                     ProcessingProcedureService processingProcedureService, ObservationListService observationListService) {
        this.catalogueListService = catalogueListService;
        this.dataSourceListService = dataSourceListService;
        this.dataSourceService = dataSourceService;
        this.datastreamsListService = datastreamsListService;
        this.datastreamService = datastreamService;
        this.thingListService = thingListService;
        this.thingService = thingService;
        this.processingProcedureListService = processingProcedureListService;
        this.processingProcedureService = processingProcedureService;
        this.observationListService = observationListService;
    }

    /**
     * Returns a {@link STAObjectList} of type {@link Catalogue} with length as specified in items
     * and starting at the specified page. For example 5 items starting at page 1
     * (starts at zero) will be 6 to 10.
     *
     * @param items amount of catalogues that should be loaded
     * @param page the page to start from
     * @return {@link STAObjectList} of type {@link Catalogue}
     */
    @GetMapping(GET_CATALOGUE_LIST_LINK)
    public STAObjectList<Catalogue> getCatalogueList(@PathVariable int items,
                                              @PathVariable int page) {
        STAObjectList<Catalogue> catalogues = this.catalogueListService.getCatalogues();
        //page starts at 0; items and page should normally be correct
        int startIndex = items * page;
        if (startIndex > catalogues.getList().size()) startIndex = (catalogues.getList().size() / items) ;
        int endIndex = Math.min(catalogues.getList().size(), startIndex + items);
        return catalogueListService.getCatalogues().subList(startIndex, endIndex);
    }

    /**
     * Returns the {@link Catalogue} with the specified id.
     *
     * @param catalogueId the id of the catalogue
     * @return {@link Catalogue} with the specified id
     */
    @GetMapping(GET_DATACATALOGUE_LINK)
    public Catalogue getCatalogue(@PathVariable int catalogueId) {
        return this.catalogueListService.getCatalogue(catalogueId);
    }

    /**
     * Returns the {@link DataSource} with the specified id from the respective catalogue.
     *
     * @param dataSourceId the id of the datasource
     * @param catalogueId the id of the catalogue containing the datasource
     * @return the specified {@link DataSource}
     */
    @GetMapping(GET_DATASOURCE_LINK)
    public DataSource getDataSource(@PathVariable long dataSourceId,
                             @PathVariable int catalogueId) {
        Catalogue catalogue = this.getCatalogue(catalogueId);
        this.dataSourceService.setSource(new Source(catalogue.getUrl()));

        dataSourceService.load(dataSourceId);
        return dataSourceService.getData();
    }

    /**
     * Returns a {@link STAObjectList} of type {@link DataSource} with length as specified in items
     * and starting at the specified page. For example 5 items starting at page 1
     * (starts at zero) will be 6 to 10.
     *
     * @param items amount of datasources that should be loaded
     * @param page the page to start from
     * @param catalogueId the id of the catalogue containing the datasources
     * @param filter optional filter that filters the list of datasources
     * @return {@link STAObjectList} of type {@link DataSource}
     */
    @GetMapping(value = {GET_DATASOURCE_LIST_LINK, GET_DATASOURCE_LIST_LINK + FILTER_ARG})
    public STAObjectList<DataSource> getDataSourceList(@PathVariable int items,
                                                       @PathVariable long page,
                                                       @PathVariable int catalogueId,
                                                       @PathVariable Optional<String> filter) {
        Catalogue catalogue = this.getCatalogue(catalogueId);
        this.dataSourceListService.setSource(new Source(catalogue.getUrl()));
        this.dataSourceListService.removeFilter();
        filter.ifPresent(s -> this.dataSourceListService.addFilter(new FrostRequestFilter(s)));
        this.dataSourceListService.load(items, this.calculateStartIndex(items, page));
        return this.dataSourceListService.getData();
    }

    /**
     * Returns the {@link Datastream} with the specified id from the respective {@link DataSource}
     * of a {@link Catalogue}.
     *
     * @param datastreamID the id of the datastream
     * @param catalogueId the id of the catalogue containing the datasource
     * @param dataSourceId the id of the datasource containing the datastream
     * @return the specified the {@link Datastream}
     */
    @GetMapping(GET_DATASTREAM_LINK)
    public Datastream getDatastream(@PathVariable int catalogueId,
                                    @PathVariable long dataSourceId,
                                    @PathVariable long datastreamID) {
        DataSource dataSource = this.getDataSource(dataSourceId, catalogueId);
        this.datastreamService.setSource(dataSource.getAccessData());

        datastreamService.load(datastreamID);
        return datastreamService.getData();
    }

    /**
     * Returns a {@link STAObjectList} of type {@link Datastream} with length as specified in items
     * and starting at the specified page. For example 5 items starting at page 1
     * (starts at zero) will be 6 to 10.
     *
     * @param items amount of datastreams that should be loaded
     * @param page the page to start from
     * @param catalogueId the id of the catalogue containing the datasource
     * @param dataSourceId the id of the datasource containing the datastreams
     * @param filter optional filter that filters the list of datastreams
     * @return {@link STAObjectList} of type {@link Datastream}
     */
    @GetMapping({GET_DATASTREAM_LIST_LINK, GET_DATASTREAM_LIST_LINK + FILTER_ARG})
    public STAObjectList<Datastream> getDatastreamList(@PathVariable int items,
                                                       @PathVariable long page,
                                                       @PathVariable int catalogueId,
                                                       @PathVariable long dataSourceId,
                                                       @PathVariable Optional<String> filter) {
        DataSource dataSource = this.getDataSource(dataSourceId, catalogueId);
        this.datastreamsListService.setSource(dataSource.getAccessData());

        this.datastreamsListService.removeFilter();
        filter.ifPresent(s -> this.datastreamsListService.addFilter(new FrostRequestFilter(s)));
        this.datastreamsListService.load(items, this.calculateStartIndex(items, page));
        return this.datastreamsListService.getData();
    }

    /**
     * Returns a {@link STAObjectList} of type {@link Thing} with length as specified in items
     * and starting at the specified page. For example 5 items starting at page 1
     * (starts at zero) will be 6 to 10.
     *
     * @param items amount of things that should be loaded
     * @param page the page to start from
     * @param catalogueId the id of the catalogue containing the datasource
     * @param dataSourceId the id of the datasource containing the datastreams
     * @param filter optional filter that filters the list of things
     * @return {@link STAObjectList} of type {@link Thing}
     */
    @GetMapping({GET_THING_LIST_LINK, GET_THING_LIST_LINK + FILTER_ARG})
    public STAObjectList<Thing> getThingList(@PathVariable int items,
                                             @PathVariable long page,
                                             @PathVariable int catalogueId,
                                             @PathVariable long dataSourceId,
                                             @PathVariable Optional<String> filter) {
        DataSource dataSource = this.getDataSource(dataSourceId, catalogueId);
        this.thingListService.setSource(dataSource.getAccessData());

        this.thingListService.removeFilter();
        filter.ifPresent(s -> this.thingListService.addFilter(new FrostRequestFilter(s)));
        this.thingListService.load(items, this.calculateStartIndex(items, page));
        return this.thingListService.getData();
    }

    /**
     * Returns the {@link Thing} with the specified id from the respective {@link DataSource}
     * of a {@link Catalogue}.
     *
     * @param thingId the id of the thing
     * @param catalogueId the id of the catalogue containing the datasource
     * @param dataSourceId the id of the datasource containing the thing
     * @return the specified {@link Thing}
     */
    @GetMapping(GET_THING_LINK)
    public Thing getThing(@PathVariable long thingId,
                         @PathVariable int catalogueId,
                         @PathVariable long dataSourceId) {
        DataSource dataSource = this.getDataSource(dataSourceId, catalogueId);
        this.thingService.setSource(dataSource.getAccessData());

        thingService.load(thingId);
        return thingService.getData();
    }

    @GetMapping(GET_THING_OF_STREAM_LINK)
    public Thing getThing(@PathVariable int catalogueId,
                          @PathVariable long dataSourceId,
                          @PathVariable long datastreamID) {
        DataSource dataSource = this.getDataSource(dataSourceId, catalogueId);
        this.thingService.setSource(dataSource.getAccessData());

        thingService.getFromAssociatedObject(ObjectType.DATASTREAM, datastreamID);
        return thingService.getData();
    }

    /**
     * Returns the {@link ProcessingProcedure} with the specified id from the respective catalogue.
     *
     * @param processingProcedureID the id of the processing procedure
     * @param catalogueId the id of the catalogue containing the processing procedure
     * @return the specified {@link ProcessingProcedure}
     */
    @GetMapping(GET_PROCESSING_PROCEDURE_LINK)
    public ProcessingProcedure getProcessingProcedure(@PathVariable long processingProcedureID,
                             @PathVariable int catalogueId) {
        Catalogue catalogue = this.getCatalogue(catalogueId);
        this.processingProcedureService.setSource(new Source(catalogue.getUrl()));

        processingProcedureService.load(processingProcedureID);
        return processingProcedureService.getData();
    }

    /**
     * Returns a {@link STAObjectList} of type {@link ProcessingProcedure} with length as specified in items
     * and starting at the specified page. For example 5 items starting at page 1
     * (starts at zero) will be 6 to 10.
     *
     * @param items amount of processing procedures that should be loaded
     * @param page the page to start from
     * @param catalogueId the id of the catalogue containing the processing procedures
     * @param filter optional filter that filters the list of processing procedures
     * @return {@link STAObjectList} of type {@link ProcessingProcedure}
     */
    @GetMapping({GET_PROCESSING_PROCEDURE_LIST_LINK, GET_PROCESSING_PROCEDURE_LIST_LINK + FILTER_ARG})
    public STAObjectList<ProcessingProcedure> getProcessingProcedureList(@PathVariable int items,
                                                                         @PathVariable long page,
                                                                         @PathVariable int catalogueId,
                                                                         @PathVariable Optional<String> filter) {
        Catalogue catalogue = this.getCatalogue(catalogueId);
        this.processingProcedureListService.setSource(new Source(catalogue.getUrl()));

        this.processingProcedureListService.removeFilter();
        filter.ifPresent(s -> this.processingProcedureListService.addFilter(new FrostRequestFilter(s)));
        this.processingProcedureListService.load(items, this.calculateStartIndex(items, page));
        return this.processingProcedureListService.getData();
    }

    @GetMapping({GET_DATASTREAM_LIST_FROM_THING_LINK, GET_DATASTREAM_LIST_FROM_THING_LINK + FILTER_ARG})
    public STAObjectList<Datastream> getDatastreamList(@PathVariable int catalogueId,
                                                       @PathVariable long dataSourceId,
                                                       @PathVariable long thingId,
                                                       @PathVariable int items,
                                                       @PathVariable long page,
                                                       @PathVariable Optional<String> filter) {
        DataSource dataSource = this.getDataSource(dataSourceId, catalogueId);
        this.datastreamsListService.setSource(dataSource.getAccessData());

        this.datastreamsListService.removeFilter();
        filter.ifPresent(s -> this.datastreamsListService.addFilter(new FrostRequestFilter(s)));
        this.datastreamsListService.getFromAssociatedObject(ObjectType.THING, thingId, items, page);
        return this.datastreamsListService.getData();
    }

    @GetMapping({GET_OBSERVATIONS_LINK, GET_OBSERVATIONS_LINK + FILTER_ARG})
    public STAObjectList<Observation> getObservationList(@PathVariable int catalogueId,
                                                         @PathVariable long dataSourceId,
                                                         @PathVariable long datastreamID,
                                                         @PathVariable int items,
                                                         @PathVariable long page,
                                                         @PathVariable Optional<String> filter) {
        DataSource dataSource = this.getDataSource(dataSourceId, catalogueId);
        this.observationListService.setSource(dataSource.getAccessData());

        this.observationListService.removeFilter();
        filter.ifPresent(s -> this.observationListService.addFilter(new FrostRequestFilter(s)));
        this.observationListService.getFromAssociatedObject(ObjectType.DATASTREAM, datastreamID, items, page);
        return this.observationListService.getData();
    }

    private long calculateStartIndex(int items, long page) {
        return items * page;
    }
}