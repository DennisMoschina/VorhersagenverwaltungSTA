package edu.kit.VorhersagenverwaltungSTA.unitTests;

import com.fasterxml.jackson.databind.node.TextNode;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.Contact;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.DataSource;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.Entity;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.Datastream;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.TimeObject;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.Source;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.entitiyCompletenessChecker.EntityCompletenessChecker;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.entitiyCompletenessChecker.EntityCompletenessCheckerImplementation;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.SingleSelection;
import org.geojson.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.threeten.extra.Interval;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;

public class CompletenessCheckerTest {
    private EntityCompletenessChecker<Entity> checker;

    @BeforeEach
    public void setup() {
        this.checker = new EntityCompletenessCheckerImplementation();
    }

    @Test
    public void testBasicDatastreamIncomplete() {
        final int id = 7;
        SingleSelection selection = new SingleSelection(Set.of("name", "description"), ObjectType.DATASTREAM, id);
        DataSource dataSource = new DataSource();
        this.setupDataSourceWithId(dataSource);

        Assertions.assertFalse(this.checker.isComplete(selection, dataSource));
    }

    @Test
    public void testBasicDatastreamComplete() {
        final int id = 7;
        SingleSelection selection = new SingleSelection(Set.of("name", "description"), ObjectType.DATASTREAM, id);
        Datastream datastream = new Datastream();
        this.setupDatastreamWithNameAndDescription(datastream);

        Assertions.assertTrue(this.checker.isComplete(selection, datastream));
    }

    @Test
    public void testDatastreamDefaultKeysIncomplete() {
        final int id = 7;
        SingleSelection selection = new SingleSelection(ObjectType.DATASTREAM, id);
        Datastream datastream = new Datastream();
        this.setupDatastreamWithId(datastream);

        Assertions.assertFalse(this.checker.isComplete(selection, datastream));
    }

    @Test
    public void testDatastreamDefaultKeysComplete() {
        final int id = 7;
        SingleSelection selection = new SingleSelection(ObjectType.DATASTREAM, id);
        Datastream datastream = new Datastream();
        this.setupFullDatastreamWithoutExpand(datastream);

        Assertions.assertTrue(this.checker.isComplete(selection, datastream));
    }

    @Test
    public void testBasicDataSourceIncomplete() {
        final int id = 7;
        SingleSelection selection = new SingleSelection(Set.of("name", "description"), ObjectType.DATASOURCE, id);
        DataSource dataSource = new DataSource();
        this.setupDataSourceWithId(dataSource);

        Assertions.assertFalse(this.checker.isComplete(selection, dataSource));
    }

    @Test
    public void testBasicDataSourceComplete() {
        final int id = 7;
        SingleSelection selection = new SingleSelection(Set.of("name", "description"), ObjectType.DATASOURCE, id);
        DataSource dataSource = new DataSource();
        this.setupDataSourceWithNameAndDescription(dataSource);

        Assertions.assertTrue(this.checker.isComplete(selection, dataSource));
    }

    @Test
    public void testExpandDataSourceIncomplete() {
        final int id = 7;
        SingleSelection selection = new SingleSelection(Set.of("name", "description"), ObjectType.DATASOURCE, id);
        DataSource dataSource = new DataSource();
        this.setupDataSourceWithNameAndDescription(dataSource);
        selection.addObjectToExpand(new SingleSelection(ObjectType.CONTACT));

        Assertions.assertFalse(this.checker.isComplete(selection, dataSource));
    }

    @Test
    public void testExpandDataSourceComplete() {
        final int id = 7;
        SingleSelection selection = new SingleSelection(Set.of("name", "description"), ObjectType.DATASOURCE, id);
        DataSource dataSource = new DataSource();
        this.setupDataSourceWithNameAndDescription(dataSource);
        selection.addObjectToExpand(new SingleSelection(ObjectType.CONTACT));
        dataSource.setContact(new Contact());

        Assertions.assertTrue(this.checker.isComplete(selection, dataSource));
    }

    @Test
    public void testDataSourceDefaultKeysIncomplete() {
        final int id = 7;
        SingleSelection selection = new SingleSelection(ObjectType.DATASOURCE, id);
        DataSource dataSource = new DataSource();
        this.setupDataSourceWithId(dataSource);

        Assertions.assertFalse(this.checker.isComplete(selection, dataSource));
    }

    @Test
    public void testDataSourceDefaultKeysComplete() {
        final int id = 7;
        SingleSelection selection = new SingleSelection(ObjectType.DATASOURCE, id);
        DataSource dataSource = new DataSource();
        this.setupFullDataSourceWithoutExpand(dataSource);

        Assertions.assertTrue(this.checker.isComplete(selection, dataSource));
    }

    @Test
    public void testDatastreamWithWrongId() {
        final int id = 8;
        SingleSelection selection = new SingleSelection(ObjectType.DATASTREAM, id);
        Datastream datastream = new Datastream();
        this.setupDatastreamWithId(datastream);

        Assertions.assertFalse(this.checker.isComplete(selection, datastream));
    }

    private void setupFullDataSourceWithoutExpand(DataSource dataSource) {
        this.setupDataSourceWithNameAndDescription(dataSource);
        dataSource.setSourceSystem("TestSystem");
        dataSource.setDataType("TestType");
        dataSource.setDataQuality(new TextNode("TestNode"));
        dataSource.setSpatialDistribution("testSpatialDistribution");
        dataSource.setAccessData(new Source(""));
        dataSource.setProperties(new TextNode("TestNode"));
        dataSource.setTransmissionPeriod(Duration.ofDays(1));
        dataSource.setRecordingPeriod(Duration.ofDays(1));
        dataSource.setAggregationPeriod(Duration.ofDays(1));
        dataSource.setPhenomenonTime(new TimeObject(Instant.now()));
        dataSource.setObservedArea(new Feature());

        dataSource.setOwnerURL("");
        dataSource.setContactURL("");
        dataSource.setLicenseURL("");
        dataSource.setAccessInterfaceURL("");
        dataSource.setReadingServicesURL("");
        dataSource.setWritingServicesURL("");
    }

    private void setupDataSourceWithId(DataSource dataSource) {
        dataSource.setId(7);
    }

    private void setupDataSourceWithNameAndDescription(DataSource dataSource) {
        this.setupDataSourceWithId(dataSource);
        dataSource.setName("TestName");
        dataSource.setDescription("TestDescription");
    }

    private void setupDatastreamWithId(Datastream datastream) {
        datastream.setId(7);
    }

    private void setupDatastreamWithNameAndDescription(Datastream datastream) {
        this.setupDatastreamWithId(datastream);
        datastream.setName("TestName");
        datastream.setDescription("TestDescription");
    }

    private void setupFullDatastreamWithoutExpand(Datastream datastream) {
        this.setupDatastreamWithNameAndDescription(datastream);

        datastream.setObservationType("testType");
        datastream.setUnitOfMeasurement(new TextNode(("TestNode")));
        datastream.setObservedArea(new Feature());
        datastream.setPhenomenonTime(Interval.endingAt(Instant.now()));
        datastream.setResultTime(Interval.endingAt(Instant.now()));
        datastream.setProperties(new TextNode("TestNode"));

        datastream.setObservationsURL("");
        datastream.setSensorURL("");
        datastream.setThingURL("");
        datastream.setObservedPropertyURL("");
    }

}