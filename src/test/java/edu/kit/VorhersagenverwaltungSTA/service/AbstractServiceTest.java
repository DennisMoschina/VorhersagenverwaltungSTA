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

    protected AbstractService<?> service;

    protected Object result;


    protected AbstractServiceTest(ObjectType type) {
        this.type = type;
    }

    @BeforeEach
    public void setup() {
        assign();

        service.setSource(new Source("https://example.org"));
        this.requestManagerMock = mock(RequestManager.class);
        doAnswer(invocation -> {
            this.requestManagerMockMethod(invocation.getArgument(0));
            return null;
        }).when(this.requestManagerMock).request(any());
        doAnswer(invocation -> this.result).when(this.requestManagerMock).getResult();

        this.service.requestManager = this.requestManagerMock;
    }

    protected abstract void assign();

    private void requestManagerMockMethod(Selection selection) {
        if (selection instanceof SingleSelection singleSelection)
            this.requestSingleItemManagerMockMethod(singleSelection);
        if (selection instanceof MultiSelection multiSelection)
            this.requestItemListManagerMockMethod(multiSelection);
        if (selection instanceof ObjectAssociatedSelection associatedSelection)
            this.requestAssociatedObjectMockMethod(associatedSelection);
    }

    private void requestAssociatedObjectMockMethod(ObjectAssociatedSelection selection) {
        if (selection.getSelection() instanceof SingleSelection)
            switch (selection.getObjectType()) {
                case THING -> this.result = new Thing();
                case DATASOURCE -> this.result = new DataSource();
                case DATASTREAM -> this.result = new Datastream();
                case PROCESSING_PROCEDURE -> this.result = new ProcessingProcedure();
                case SERVICE -> this.result = new ProcessingService();
            }
        else if (selection.getSelection() instanceof MultiSelection)
            switch (selection.getObjectType()) {
                case THING -> this.result = new ThingList();
                case DATASOURCE -> this.result = new DataSourceList();
                case DATASTREAM -> this.result = new DatastreamList();
                case PROCESSING_PROCEDURE -> this.result = new ProcessingProcedureList();
                case SERVICE -> this.result = new ServiceList();
                case OBSERVATION -> this.result = new ObservationList();
                case LOCATION -> this.result = new LocationList();
            }
    }

    private void requestItemListManagerMockMethod(MultiSelection selection) {
        switch (selection.getObjectType()) {
            case THING -> this.result = new ThingList();
            case DATASOURCE -> this.result = new DataSourceList();
            case DATASTREAM -> this.result = new DatastreamList();
            case PROCESSING_PROCEDURE -> this.result = new ProcessingProcedureList();
            case SERVICE -> this.result = new ServiceList();
            case OBSERVATION -> this.result = new ObservationList();
            case LOCATION -> this.result = new LocationList();
        }
    }

    private void requestSingleItemManagerMockMethod(SingleSelection selection) {
        final long id = selection.getSelectedId();
        switch (selection.getObjectType()) {
            case THING -> this.result = new Thing();
            case DATASOURCE -> this.result = new DataSource();
            case DATASTREAM -> this.result = new Datastream();
            case PROCESSING_PROCEDURE -> this.result = new ProcessingProcedure();
            case SERVICE -> this.result = new ProcessingService();
        }
        ((Entity) this.result).setId(id);
    }
}