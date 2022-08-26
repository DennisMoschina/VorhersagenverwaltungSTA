package edu.kit.VorhersagenverwaltungSTA.service;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.DataSource;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.Entity;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.ProcessingProcedure;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.ProcessingService;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.Datastream;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.Thing;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.DataSourceList;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.DatastreamList;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.LocationList;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.ObservationList;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.ProcessingProcedureList;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.ServiceList;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.ThingList;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.RequestManager;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.Source;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.MultiSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectAssociatedSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.Selection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.SingleSelection;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

public abstract class AbstractServiceTest {
    protected final ObjectType type;
    protected RequestManager requestManagerMock;

    protected AbstractService service;

    protected AbstractServiceTest(ObjectType type) {
        this.type = type;
    }

    @BeforeEach
    public void setup() {
        assign();

        service.setSource(new Source("https://example.org"));
        this.requestManagerMock = mock(RequestManager.class);
        doAnswer(invocation -> this.requestManagerMockMethod(invocation.getArgument(0)))
                .when(this.requestManagerMock).request(any());

        this.service.requestManager = this.requestManagerMock;
    }

    protected abstract void assign();

    private Object requestManagerMockMethod(Selection selection) {
        if (selection instanceof SingleSelection singleSelection)
            return this.requestSingleItemManagerMockMethod(singleSelection);
        if (selection instanceof MultiSelection multiSelection)
            return this.requestItemListManagerMockMethod(multiSelection);
        if (selection instanceof ObjectAssociatedSelection associatedSelection)
            return this.requestAssociatedObjectMockMethod(associatedSelection);
        return null;
    }

    private Object requestAssociatedObjectMockMethod(ObjectAssociatedSelection selection) {
        Object result = null;
        if (selection.getSelection() instanceof SingleSelection)
            switch (selection.getObjectType()) {
                case THING -> result = new Thing();
                case DATASOURCE -> result = new DataSource();
                case DATASTREAM -> result = new Datastream();
                case PROCESSING_PROCEDURE -> result = new ProcessingProcedure();
                case SERVICE -> result = new ProcessingService();
            }
        else if (selection.getSelection() instanceof MultiSelection)
            switch (selection.getObjectType()) {
                case THING -> result = new ThingList();
                case DATASOURCE -> result = new DataSourceList();
                case DATASTREAM -> result = new DatastreamList();
                case PROCESSING_PROCEDURE -> result = new ProcessingProcedureList();
                case SERVICE -> result = new ServiceList();
                case OBSERVATION -> result = new ObservationList();
                case LOCATION -> result = new LocationList();
            }
        return result;
    }

    private Object requestItemListManagerMockMethod(MultiSelection selection) {
        Object result = null;
        switch (selection.getObjectType()) {
            case THING -> result = new ThingList();
            case DATASOURCE -> result = new DataSourceList();
            case DATASTREAM -> result = new DatastreamList();
            case PROCESSING_PROCEDURE -> result = new ProcessingProcedureList();
            case SERVICE -> result = new ServiceList();
            case OBSERVATION -> result = new ObservationList();
            case LOCATION -> result = new LocationList();
        }
        return result;
    }

    private Entity requestSingleItemManagerMockMethod(SingleSelection selection) {
        Entity result = null;
        final long id = selection.getSelectedId();
        switch (selection.getObjectType()) {
            case THING -> result = new Thing();
            case DATASOURCE -> result = new DataSource();
            case DATASTREAM -> result = new Datastream();
            case PROCESSING_PROCEDURE -> result = new ProcessingProcedure();
            case SERVICE -> result = new ProcessingService();
        }
        assert result != null;
        result.setId(id);
        return result;
    }
}