package edu.kit.VorhersagenverwaltungSTA.integrationTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.DataSource;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.ProcessingProcedure;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.ProcessingService;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.catalogue.Catalogue;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.Datastream;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.Thing;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.CatalogueList;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.DataSourceList;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.DatastreamList;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.LocationList;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.ObservationList;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.ProcessingProcedureList;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.ServiceList;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.ThingList;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.Source;
import org.geojson.GeoJsonObject;
import org.geojson.LngLatAlt;
import org.geojson.Point;
import org.geojson.Polygon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BackendTest {

    private static final String LIST_ARGS = "/{items}/{page}";
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
    private static final String GET_SERVICE_LIST_LINK
            = GET_DATACATALOGUE_LINK + "/services" + LIST_ARGS;
    private static final String GET_SERVICE_LINK
            = GET_DATACATALOGUE_LINK + "/service/{serviceId}";

    private static final String GET_DATASTREAM_LIST_FROM_THING_LINK = GET_THING_LINK + "/datastreams" + LIST_ARGS;
    private static final String GET_THING_OF_STREAM_LINK = GET_DATASTREAM_LINK + "/thing";
    private static final String GET_OBSERVATIONS_LINK = GET_DATASTREAM_LINK + "/observations" + LIST_ARGS;
    private static final String GET_LOCATIONS = "/locations" + LIST_ARGS;
    private static final String GET_LOCATIONS_OF_THING_LINK = GET_THING_LINK + GET_LOCATIONS;

    private static final String GET_WRITES_SOURCES_LINK = GET_SERVICE_LINK + "/writesSources" + LIST_ARGS;
    private static final String GET_READS_SOURCES_LINK = GET_SERVICE_LINK + "/readsSources" + LIST_ARGS;
    private static final String GET_WRITING_SERVICES_LINK = GET_DATASOURCE_LINK + "/writingServices" + LIST_ARGS;
    private static final String GET_READING_SERVICES_LINK = GET_DATASOURCE_LINK + "/readingServices" + LIST_ARGS;
    private static final String GET_APPLYING_SERVICES_LINK = GET_PROCESSING_PROCEDURE_LINK + "/applyingServices" + LIST_ARGS;
    private static final String GET_APPLIES_METHODS_LINK = GET_SERVICE_LINK + "/appliesMethods" + LIST_ARGS;

    private static final Catalogue EXPECTED_CATALOGUE = new Catalogue();

    private final RestTemplate restTemplate = new RestTemplate();
    @LocalServerPort
    private int port;
    private String baseURL = "http://localhost:";


    @BeforeEach
    public void setup() {
        baseURL = baseURL + port;
    }

    @Test
    public void testGetCatalogue() {
        setExpectedCatalogue();
        Catalogue catalogue = restTemplate.getForObject(baseURL + GET_DATACATALOGUE_LINK, Catalogue.class, 1);
        Assertions.assertNotNull(catalogue);
        Assertions.assertEquals(EXPECTED_CATALOGUE, catalogue);
    }

    @Test
    public void testGetCatalogueList() {
        CatalogueList catalogues
                = this.restTemplate.getForObject(baseURL + GET_CATALOGUE_LIST_LINK, CatalogueList.class, 1, 0);
        Assertions.assertNotNull(catalogues);
        Catalogue catalogue = catalogues.getList().get(0);
        Assertions.assertEquals(1, catalogues.getList().size());
        Assertions.assertEquals(EXPECTED_CATALOGUE, catalogue);
    }

    @Test
    public void testGetDataSource() {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataQuality;
        JsonNode properties;
        try {
            dataQuality = objectMapper.readTree("{\"title\": \"Erweitert\"}");
            properties = objectMapper.readTree("{}");
        } catch (JsonProcessingException e) {
            dataQuality = null;
            properties = null;
        }
        Duration recordingPeriod = Duration.parse("PT1H");
        LngLatAlt[] coordinates = {new LngLatAlt(55.45906,-21.334406),
                new LngLatAlt(-52.3238,4.84816), new LngLatAlt(-52.563793,5.034602),
                new LngLatAlt(-61.1008,14.616389), new LngLatAlt(-61.727203,15.994349),
                new LngLatAlt(-61.591724,16.257307), new LngLatAlt(-22.684239,63.931808),
                new LngLatAlt(-22.583994,64.004213), new LngLatAlt(-21.84,64.38165),
                new LngLatAlt(11.88899,78.90669), new LngLatAlt(30.42147,69.65617),
                new LngLatAlt(55.618977,-20.910585), new LngLatAlt(55.62793,-21.29433),
                new LngLatAlt(55.47895,-21.331703), new LngLatAlt(55.45906,-21.334406)};
        GeoJsonObject observedArea = new Polygon(coordinates);

        DataSource dataSource = restTemplate.getForObject(baseURL + GET_DATASOURCE_LINK, DataSource.class, 1, 2);
        Assertions.assertNotNull(dataSource);
        Assertions.assertEquals(2, dataSource.getId());
        Assertions.assertEquals("Air Quality Measurements", dataSource.getName());
        Assertions.assertEquals("Official Air Quality measurements sourced from the EEA.", dataSource.getDescription());
        Assertions.assertEquals("EEA - https://discomap.eea.europa.eu", dataSource.getSourceSystem());
        Assertions.assertEquals("Measurements", dataSource.getDataType());
        Assertions.assertEquals(dataQuality, dataSource.getDataQuality());
        Assertions.assertEquals(recordingPeriod, dataSource.getRecordingPeriod());
        /* cannot be tested properly as no values are provided
        Assertions.assertEquals(null, dataSource.getAggregationPeriod());
        Assertions.assertEquals(null, dataSource.getTransmissionPeriod());
        Assertions.assertEquals(null, dataSource.getPhenomenonTime());*/
        Assertions.assertEquals("Point", dataSource.getSpatialDistribution());
        Assertions.assertEquals(observedArea, dataSource.getObservedArea());
        Assertions.assertEquals(new Source("https://airquality-frost.k8s.ilt-dmz.iosb.fraunhofer.de/v1.1/"), dataSource.getAccessData());
        Assertions.assertEquals(properties, dataSource.getProperties());
        Assertions.assertEquals("https://bml-catalog.k8s.ilt-dmz.iosb.fraunhofer.de/FROST-Server/ODATA_4.01/DataSources(2)/Owner", dataSource.getOwnerURL());
        Assertions.assertEquals(2, dataSource.getOwner().getId());
        Assertions.assertEquals("https://bml-catalog.k8s.ilt-dmz.iosb.fraunhofer.de/FROST-Server/ODATA_4.01/DataSources(2)/Contact", dataSource.getContactURL());
        Assertions.assertEquals(2, dataSource.getContact().getId());
        Assertions.assertEquals("https://bml-catalog.k8s.ilt-dmz.iosb.fraunhofer.de/FROST-Server/ODATA_4.01/DataSources(2)/License", dataSource.getLicenseURL());
        Assertions.assertEquals(1, dataSource.getLicense().getId());
        Assertions.assertEquals("https://bml-catalog.k8s.ilt-dmz.iosb.fraunhofer.de/FROST-Server/ODATA_4.01/DataSources(2)/AccessInterface", dataSource.getAccessInterfaceURL());
        Assertions.assertEquals(1, dataSource.getAccessInterface().getId());
    }

    @Test
    public void testGetDataSourceList() {
        DataSourceList dataSources
                = this.restTemplate.getForObject(baseURL + GET_DATASOURCE_LIST_LINK, DataSourceList.class, 1, 5, 0);
        Assertions.assertNotNull(dataSources);
        Assertions.assertEquals(2, dataSources.getList().size());
        Assertions.assertEquals(2, dataSources.getList().get(1).getId());
        Assertions.assertEquals("Air Quality Measurements", dataSources.getList().get(1).getName());
        Assertions.assertEquals("Official Air Quality measurements sourced from the EEA.", dataSources.getList().get(1).getDescription());
    }

    @Test
    public void testGetDataStreamList() {
        DatastreamList datastreams
                = this.restTemplate.getForObject(baseURL + GET_DATASTREAM_LIST_LINK, DatastreamList.class, 1, 2, 3, 1);
        Assertions.assertNotNull(datastreams);
        Assertions.assertEquals(20022, datastreams.getCount());
        Assertions.assertEquals(3, datastreams.getList().size());
        //get(0) because the datastream is the first on the second page (items 3, page 1)
        Assertions.assertEquals(4, datastreams.getList().get(0).getId());
        Assertions.assertEquals(5, datastreams.getList().get(1).getId());
        Assertions.assertEquals(6, datastreams.getList().get(2).getId());
        Assertions.assertEquals("SPO.06.185.2420.1.1", datastreams.getList().get(0).getName());
        Assertions.assertEquals("SO2 as Klöch bei Bad Radkersburg", datastreams.getList().get(0).getDescription());
    }

    @Test
    public void testGetDatastream() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectMapper objectMapper2 = new ObjectMapper();
        JsonNode unitOfMeasurement;
        JsonNode properties;
        try {
            unitOfMeasurement = objectMapper.readTree("{\"name\": \"ug.m-3\",\"symbol\": \"ug.m-3\",\"definitio" +
                    "n\": \"http://dd.eionet.europa.eu/vocabulary/uom/concentration/ug.m-3\"}");
            properties = objectMapper2.readTree("{\"owner\":\"http://luft.umweltbundesamt.at\",\"localId\":" +
                    "\"SPO.06.185.2420.1.1\",\"metadata\":\"http://luft.umweltbundesamt.at/inspire/wfs?service=WFS&ver" +
                    "sion=2.0.0&request=GetFeature&typeName=aqd:AQD_SamplingPoint\",\"namespace\":\"AT.0008.20.AQ\"," +
                    "\"processType\":\"http://inspire.ec.europa.eu/codeList/ProcessTypeValue/process\",\"resultNature\":" +
                    "\"http://inspire.ec.europa.eu/codeList/ResultNatureValue/primary\",\"featureOfInterestLocalId\":" +
                    "\"SAM.06.185.2420.1.1\"}");
        } catch (JsonProcessingException e) {
            unitOfMeasurement = null;
            properties = null;
        }
        GeoJsonObject observedArea = new Point(15.956111, 46.7675);
        //Interval phenomenonTime = Interval.parse("2017-12-31T23:00:00Z/2022-08-24T18:00:00Z");

        Datastream datastream = restTemplate.getForObject(baseURL + GET_DATASTREAM_LINK, Datastream.class, 1, 2, 4);
        Assertions.assertNotNull(datastream);
        Assertions.assertEquals(4, datastream.getId());
        Assertions.assertEquals("SPO.06.185.2420.1.1", datastream.getName());
        Assertions.assertEquals("SO2 as Klöch bei Bad Radkersburg", datastream.getDescription());
        Assertions.assertEquals("http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement", datastream.getObservationType());
        Assertions.assertEquals(unitOfMeasurement, datastream.getUnitOfMeasurement());
        Assertions.assertEquals(observedArea, datastream.getObservedArea());
        //Assertions.assertEquals(phenomenonTime, datastream.getPhenomenonTime()); cannot be tested as the data changes
        //Assertions.assertNull(datastream.getResultTime()); cannot be tested properly as no data is provided
        Assertions.assertEquals(properties, datastream.getProperties());
        Assertions.assertEquals("https://airquality-frost.k8s.ilt-dmz.iosb.fraunhofer.de/v1.1/Datastreams(4)/Thing", datastream.getThingURL());
        Assertions.assertEquals(154, datastream.getThing().getId());
        Assertions.assertEquals("https://airquality-frost.k8s.ilt-dmz.iosb.fraunhofer.de/v1.1/Datastreams(4)/Sensor", datastream.getSensorURL());
        Assertions.assertEquals(505, datastream.getSensor().getId());
        Assertions.assertEquals("https://airquality-frost.k8s.ilt-dmz.iosb.fraunhofer.de/v1.1/Datastreams(4)/ObservedProperty", datastream.getObservedPropertyURL());
        Assertions.assertEquals(1, datastream.getObservedProperty().getId());
        Assertions.assertEquals("https://airquality-frost.k8s.ilt-dmz.iosb.fraunhofer.de/v1.1/Datastreams(4)/Observations", datastream.getObservationsURL());
        Assertions.assertEquals(20, datastream.getObservations().length);
        Assertions.assertEquals(2110, datastream.getObservations()[0].getId());
    }

    @Test
    public void testGetThingList() {
        ThingList things
                = this.restTemplate.getForObject(baseURL + GET_THING_LIST_LINK, ThingList.class, 1, 2, 3, 0);
        Assertions.assertNotNull(things);
        Assertions.assertEquals(4892, things.getCount());
        Assertions.assertEquals(3, things.getList().size());
        Assertions.assertEquals(1, things.getList().get(0).getId());
        Assertions.assertEquals(2, things.getList().get(1).getId());
        Assertions.assertEquals(3, things.getList().get(2).getId());
        Assertions.assertEquals("Liesing - Gewerbegebiet", things.getList().get(0).getName());
        Assertions.assertEquals("Air quality station Liesing - Gewerbegebiet", things.getList().get(0).getDescription());
    }

    @Test
    public void testGetThing() {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode properties;
        try {
            properties = objectMapper.readTree("{\"owner\":\"http://luft.umweltbundesamt.at\",\"mobile\":false," +
                    "\"localId\":\"STA.08.1919\",\"metadata\":\"http://luft.umweltbundesamt.at/inspire/wfs?service=WFS&" +
                    "version=2.0.0&request=GetFeature&typeName=aqd:AQD_Station\",\"beginTime\":\"1998-05-29\",\"namespace" +
                    "\":\"AT.0008.20.AQ\",\"countryCode\":\"AT\",\"mediaMonitored\":\"http://inspire.ec.europa.eu/codelist" +
                    "/MediaValue/air\",\"measurementRegime\":\"http://inspire.ec.europa.eu/codelist/MeasurementRegimeValue/" +
                    "continuousDataCollection\"}");
        } catch (JsonProcessingException e) {
            properties = null;
        }
        Thing thing = restTemplate.getForObject(baseURL + GET_THING_LINK, Thing.class, 1, 2, 5);
        Assertions.assertNotNull(thing);
        Assertions.assertEquals(5, thing.getId());
        Assertions.assertEquals("Feldkirch Bärenkreuzung", thing.getName());
        Assertions.assertEquals("Air quality station Feldkirch Bärenkreuzung", thing.getDescription());
        Assertions.assertEquals(properties, thing.getProperties());
        Assertions.assertEquals("https://airquality-frost.k8s.ilt-dmz.iosb.fraunhofer.de/v1.1/Things(5)/Datastreams", thing.getDatastreamsURL());
        Assertions.assertEquals(349, thing.getDatastreams()[0].getId());
        Assertions.assertEquals("https://airquality-frost.k8s.ilt-dmz.iosb.fraunhofer.de/v1.1/Things(5)/Locations", thing.getLocationsURL());
        Assertions.assertEquals(5, thing.getLocations()[0].getId());
        Assertions.assertEquals("https://airquality-frost.k8s.ilt-dmz.iosb.fraunhofer.de/v1.1/Things(5)/HistoricalLocations", thing.getHistoricalLocationsURL());
        Assertions.assertEquals(5, thing.getHistoricalLocations()[0].getId());
    }

    @Test
    public void testGetThingFromStream() {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode properties;
        try {
            properties = objectMapper.readTree("{\"owner\":\"http://luft.umweltbundesamt.at\",\"mobile\":false," +
                    "\"localId\":\"STA.08.1919\",\"metadata\":\"http://luft.umweltbundesamt.at/inspire/wfs?service=WFS&" +
                    "version=2.0.0&request=GetFeature&typeName=aqd:AQD_Station\",\"beginTime\":\"1998-05-29\",\"namespace" +
                    "\":\"AT.0008.20.AQ\",\"countryCode\":\"AT\",\"mediaMonitored\":\"http://inspire.ec.europa.eu/codelist" +
                    "/MediaValue/air\",\"measurementRegime\":\"http://inspire.ec.europa.eu/codelist/MeasurementRegimeValue/" +
                    "continuousDataCollection\"}");
        } catch (JsonProcessingException e) {
            properties = null;
        }
        Thing thing = restTemplate.getForObject(baseURL + GET_THING_OF_STREAM_LINK, Thing.class, 1, 2, 349);
        Assertions.assertNotNull(thing);
        Assertions.assertEquals(5, thing.getId());
        Assertions.assertEquals("Feldkirch Bärenkreuzung", thing.getName());
        Assertions.assertEquals("Air quality station Feldkirch Bärenkreuzung", thing.getDescription());
        Assertions.assertEquals(properties, thing.getProperties());
        Assertions.assertEquals("https://airquality-frost.k8s.ilt-dmz.iosb.fraunhofer.de/v1.1/Things(5)/Datastreams", thing.getDatastreamsURL());
        Assertions.assertEquals(349, thing.getDatastreams()[0].getId());
        Assertions.assertEquals("https://airquality-frost.k8s.ilt-dmz.iosb.fraunhofer.de/v1.1/Things(5)/Locations", thing.getLocationsURL());
        Assertions.assertEquals(5, thing.getLocations()[0].getId());
        Assertions.assertEquals("https://airquality-frost.k8s.ilt-dmz.iosb.fraunhofer.de/v1.1/Things(5)/HistoricalLocations", thing.getHistoricalLocationsURL());
        Assertions.assertEquals(5, thing.getHistoricalLocations()[0].getId());
    }

    @Test
    public void testGetProcessingProcedure() {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode properties;
        JsonNode configurations;
        try {
            properties = objectMapper.readTree("{}");
            configurations = objectMapper.readTree("{\"param1\":{\"type\":\"Number\"}}");
        } catch (JsonProcessingException e) {
            properties = null;
            configurations = null;
        }
        ProcessingProcedure processingProcedure = restTemplate.getForObject(baseURL + GET_PROCESSING_PROCEDURE_LINK, ProcessingProcedure.class, 1, 1);
        Assertions.assertNotNull(processingProcedure);
        Assertions.assertEquals(1, processingProcedure.getId());
        Assertions.assertEquals("No2", processingProcedure.getName());
        Assertions.assertEquals("Predicts NO2 Concentrations", processingProcedure.getDescription());
        Assertions.assertEquals("Zeitreihenvorhersage", processingProcedure.getCategory());
        Assertions.assertEquals("Prognose\u00A0(zeitl.)", processingProcedure.getUseCase());
        Assertions.assertEquals("Modellbildung (hauptsächlich ML)", processingProcedure.getMethod());
        Assertions.assertEquals("Luftqualitätsvorhersage", processingProcedure.getDomain());
        Assertions.assertEquals(configurations, processingProcedure.getConfiguration());
        Assertions.assertEquals(properties, processingProcedure.getProperties());
        Assertions.assertEquals("https://bml-catalog.k8s.ilt-dmz.iosb.fraunhofer.de/FROST-Server/ODATA_4.01/MLMethods(1)/Owner", processingProcedure.getOwnerURL());
        Assertions.assertEquals(1, processingProcedure.getOwner().getId());
        Assertions.assertEquals("https://bml-catalog.k8s.ilt-dmz.iosb.fraunhofer.de/FROST-Server/ODATA_4.01/MLMethods(1)/Contact", processingProcedure.getContactURL());
        Assertions.assertEquals(1, processingProcedure.getContact().getId());
        Assertions.assertEquals("https://bml-catalog.k8s.ilt-dmz.iosb.fraunhofer.de/FROST-Server/ODATA_4.01/MLMethods(1)/License", processingProcedure.getLicenseURL());
        Assertions.assertEquals(1, processingProcedure.getLicense().getId());
    }

    @Test
    public void testGetProcessingProcedureList() {
        ProcessingProcedureList processingProcedures
                = restTemplate.getForObject(baseURL + GET_PROCESSING_PROCEDURE_LIST_LINK, ProcessingProcedureList.class, 1, 5, 0);
        Assertions.assertNotNull(processingProcedures);
        Assertions.assertEquals(1, processingProcedures.getList().size());
        Assertions.assertEquals(1, processingProcedures.getList().get(0).getId());
        Assertions.assertEquals("No2", processingProcedures.getList().get(0).getName());
        Assertions.assertEquals("Predicts NO2 Concentrations", processingProcedures.getList().get(0).getDescription());
    }

    @Test
    public void testGetService() {
        ProcessingService service = restTemplate.getForObject(baseURL + GET_SERVICE_LINK, ProcessingService.class, 1, 1);
        Assertions.assertNotNull(service);
        Assertions.assertEquals(1, service.getId());
        Assertions.assertEquals("No2Predictor", service.getName());
        Assertions.assertEquals("Runs the NO2 Prediction", service.getDescription());
        Assertions.assertEquals("https://gitlab-ext.iosb.fraunhofer.de/BauhausMobilityLab/deployments-nt-ag/no2-vorhersage-deployment", service.getImplementationURL());
        Assertions.assertEquals(1, service.getWritesSource()[0].getId());
        Assertions.assertEquals(1, service.getReadsSources()[0].getId());
        Assertions.assertEquals(1, service.getAppliesMethods()[0].getId());
    }

    @Test
    public void testGetServiceList() {
        ServiceList services = restTemplate.getForObject(baseURL + GET_SERVICE_LIST_LINK, ServiceList.class, 1, 5, 0);
        Assertions.assertNotNull(services);
        Assertions.assertEquals(1, services.getList().size());
        Assertions.assertEquals(1, services.getList().get(0).getId());
        Assertions.assertEquals("No2Predictor", services.getList().get(0).getName());
        Assertions.assertEquals("Runs the NO2 Prediction", services.getList().get(0).getDescription());
    }

    @Test
    public void testGetDatastreamListFromThing() {
        DatastreamList datastreams = restTemplate.getForObject(baseURL + GET_DATASTREAM_LIST_FROM_THING_LINK, DatastreamList.class, 1, 2, 5, 5, 0);
        Assertions.assertNotNull(datastreams);
        Assertions.assertEquals(3, datastreams.getCount());
        Assertions.assertEquals(349, datastreams.getList().get(0).getId());
        Assertions.assertEquals(350, datastreams.getList().get(1).getId());
        Assertions.assertEquals(410, datastreams.getList().get(2).getId());
    }

    @Test
    public void testGetObservationList() {
        ObservationList observations = restTemplate.getForObject(baseURL + GET_OBSERVATIONS_LINK, ObservationList.class, 1, 2, 5, 5, 0);
        Assertions.assertNotNull(observations);
        //Assertions.assertEquals(40729, observations.getCount());  cannot be tested properly as the data changes
        Assertions.assertEquals(5, observations.getList().size());
        Assertions.assertEquals(12110, observations.getList().get(0).getId());
        Assertions.assertEquals(12111, observations.getList().get(1).getId());
        Assertions.assertEquals(12112, observations.getList().get(2).getId());
        Assertions.assertEquals(12113, observations.getList().get(3).getId());
        Assertions.assertEquals(12114, observations.getList().get(4).getId());
        Assertions.assertEquals(53.24, observations.getList().get(0).getResult());
    }

    @Test
    public void testGetLocationList() {
        //GeoJsonObject location = new Point(9.597696, 47.239445);
        LocationList locations = restTemplate.getForObject(baseURL + GET_LOCATIONS_OF_THING_LINK, LocationList.class, 1, 2, 5, 5, 0);
        Assertions.assertNotNull(locations);
        Assertions.assertEquals(1, locations.getCount());
        Assertions.assertEquals(1, locations.getList().size());
        Assertions.assertEquals(5, locations.getList().get(0).getId());
        Assertions.assertEquals("Feldkirch Bärenkreuzung", locations.getList().get(0).getName());
        Assertions.assertEquals("Location of air quality station Feldkirch Bärenkreuzung", locations.getList().get(0).getDescription());
        //Assertions.assertEquals(location, locations.getList().get(0).getLocation().getGeoJsonObject());
    }

    @Test
    public void testGetWritesSourcesList() {
        DataSourceList writeSources
                = this.restTemplate.getForObject(baseURL + GET_WRITES_SOURCES_LINK, DataSourceList.class, 1, 1, 5, 0);
        Assertions.assertNotNull(writeSources);
        Assertions.assertEquals(1, writeSources.getCount());
        Assertions.assertEquals(1, writeSources.getList().size());
        Assertions.assertEquals(1, writeSources.getList().get(0).getId());
        Assertions.assertEquals("Air Quality Measurements", writeSources.getList().get(0).getName());
        Assertions.assertEquals("Air Quality measurements made by measurement stations.", writeSources.getList().get(0).getDescription());
    }

    @Test
    public void testGetReadsSourcesList() {
        DataSourceList readsSources
                = this.restTemplate.getForObject(baseURL + GET_READS_SOURCES_LINK, DataSourceList.class, 1, 1, 5, 0);
        Assertions.assertNotNull(readsSources);
        Assertions.assertEquals(2, readsSources.getCount());
        Assertions.assertEquals(2, readsSources.getList().size());
        Assertions.assertEquals(1, readsSources.getList().get(0).getId());
        Assertions.assertEquals(2, readsSources.getList().get(1).getId());
        Assertions.assertEquals("Air Quality Measurements", readsSources.getList().get(0).getName());
        Assertions.assertEquals("Air Quality measurements made by measurement stations.", readsSources.getList().get(0).getDescription());
    }

    @Test
    public void testGetWritingServicesList() {
        ServiceList services = restTemplate.getForObject(baseURL + GET_WRITING_SERVICES_LINK, ServiceList.class, 1, 1, 5, 0);
        Assertions.assertNotNull(services);
        Assertions.assertEquals(1, services.getList().size());
        Assertions.assertEquals(1, services.getList().get(0).getId());
        Assertions.assertEquals("No2Predictor", services.getList().get(0).getName());
        Assertions.assertEquals("Runs the NO2 Prediction", services.getList().get(0).getDescription());
    }

    @Test
    public void testGetReadingServicesList() {
        ServiceList services = restTemplate.getForObject(baseURL + GET_READING_SERVICES_LINK, ServiceList.class, 1, 1, 5, 0);
        Assertions.assertNotNull(services);
        Assertions.assertEquals(1, services.getList().size());
        Assertions.assertEquals(1, services.getList().get(0).getId());
        Assertions.assertEquals("No2Predictor", services.getList().get(0).getName());
        Assertions.assertEquals("Runs the NO2 Prediction", services.getList().get(0).getDescription());
    }

    @Test
    public void testGetAppliesMethodsList() {
        ProcessingProcedureList appliesMethods = restTemplate.getForObject(baseURL + GET_APPLIES_METHODS_LINK, ProcessingProcedureList.class, 1, 1, 5, 0);
        Assertions.assertNotNull(appliesMethods);
        Assertions.assertEquals(1, appliesMethods.getList().size());
        Assertions.assertEquals(1, appliesMethods.getList().get(0).getId());
        Assertions.assertEquals("No2", appliesMethods.getList().get(0).getName());
        Assertions.assertEquals("Predicts NO2 Concentrations", appliesMethods.getList().get(0).getDescription());
    }

    @Test
    public void testGetApplyingServicesList() {
        ServiceList appliesMethods = restTemplate.getForObject(baseURL + GET_APPLYING_SERVICES_LINK, ServiceList.class, 1, 1, 5, 0);
        Assertions.assertNotNull(appliesMethods);
        Assertions.assertEquals(1, appliesMethods.getList().size());
        Assertions.assertEquals(1, appliesMethods.getList().get(0).getId());
        Assertions.assertEquals("No2Predictor", appliesMethods.getList().get(0).getName());
        Assertions.assertEquals("Runs the NO2 Prediction", appliesMethods.getList().get(0).getDescription());
    }

    private void setExpectedCatalogue() {
        EXPECTED_CATALOGUE.setId(1);
        EXPECTED_CATALOGUE.setName("BML");
        EXPECTED_CATALOGUE.setDescription("Test");
        EXPECTED_CATALOGUE.setUrl("https://bml-catalog.k8s.ilt-dmz.iosb.fraunhofer.de/FROST-Server/ODATA_4.01/");
    }
}