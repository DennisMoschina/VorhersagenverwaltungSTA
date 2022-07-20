package edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.AccessInterface;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.Contact;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.DataSource;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.License;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.Owner;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.ProcessingProcedure;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.catalogue.Catalogue;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.Datastream;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.FeatureOfInterest;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.HistoricalLocation;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.Location;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.Observation;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.ObservedProperty;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.Sensor;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.Thing;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.CatalogueList;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.DataSourceList;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.DatastreamList;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.ProcessingProcedureList;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.ThingList;

public enum ObjectType {
    DATASTREAM(Datastream.class, DatastreamList.class),
    SENSOR(Sensor.class, null),
    THING(Thing.class, ThingList.class),
    LOCATION(Location.class, null),
    HISTORICAL_LOCATION(HistoricalLocation.class, null),
    OBSERVED_PROPERTY(ObservedProperty.class, null),
    OBSERVATION(Observation.class, null),
    FEATURE_OF_INTEREST(FeatureOfInterest.class, null),
    DATASOURCE(DataSource.class, DataSourceList.class),
    OWNER(Owner.class, null),
    CONTACT(Contact.class, null),
    LICENSE(License.class, null),
    ACCESS_INTERFACE(AccessInterface.class, null),
    PROCESSING_PROCEDURE(ProcessingProcedure.class, ProcessingProcedureList.class),
    CATALOGUE(Catalogue.class, CatalogueList.class);

    private final Class<?> objectClass;
    private final Class<?> listClass;

    ObjectType(Class<?> objectClass, Class<?> listClass) {
        this.objectClass = objectClass;
        this.listClass = listClass;
    }

    public Class<?> getObjectClass() {
        return objectClass;
    }
    public Class<?> getListClass() {
        return listClass;
    }
}