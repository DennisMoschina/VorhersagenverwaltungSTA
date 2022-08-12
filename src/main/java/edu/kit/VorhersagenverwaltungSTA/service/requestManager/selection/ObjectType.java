package edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.AccessInterface;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.Contact;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.DataSource;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.Entity;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.License;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.Owner;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.ProcessingProcedure;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.ProcessingService;
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
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.LocationList;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.ObservationList;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.ProcessingProcedureList;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.STAObjectList;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.ServiceList;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.ThingList;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection.PluralObjectTypeEncoder;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection.SingularObjectTypeEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ObjectType {
    DATASTREAM(Datastream.class, DatastreamList.class),
    SENSOR(Sensor.class, null),
    THING(Thing.class, ThingList.class),
    LOCATION(Location.class, LocationList.class),
    HISTORICAL_LOCATION(HistoricalLocation.class, null),
    OBSERVED_PROPERTY(ObservedProperty.class, null),
    OBSERVATION(Observation.class, ObservationList.class),
    FEATURE_OF_INTEREST(FeatureOfInterest.class, null),
    DATASOURCE(DataSource.class, DataSourceList.class),
    OWNER(Owner.class, null),
    CONTACT(Contact.class, null),
    LICENSE(License.class, null),
    ACCESS_INTERFACE(AccessInterface.class, null),
    PROCESSING_PROCEDURE(ProcessingProcedure.class, ProcessingProcedureList.class),
    CATALOGUE(Catalogue.class, CatalogueList.class),
    SERVICE(ProcessingService.class, ServiceList.class);

    private final Class<? extends Entity> objectClass;
    private final Class<? extends STAObjectList<? extends Entity>> listClass;

    private final List<Relation> relations = new ArrayList<>();

    static {
        DATASTREAM.addRelations(
                new Relation(SENSOR),
                new Relation(OBSERVED_PROPERTY),
                new Relation(true, OBSERVATION),
                new Relation(THING)
        );
        SENSOR.addRelations(new Relation(true, DATASTREAM));
        OBSERVED_PROPERTY.addRelations(new Relation(true, DATASTREAM));

        SERVICE.addRelations(
                new Relation(true, DATASOURCE, "WritesSources"),
                new Relation(true, DATASOURCE, "ReadsSources"),
                new Relation(true, PROCESSING_PROCEDURE, "AppliesMethods")
        );

        PROCESSING_PROCEDURE.addRelations(
                new Relation(OWNER),
                new Relation(CONTACT),
                new Relation(LICENSE),
                new Relation(true, SERVICE, "ApplyingServices")
        );

        DATASOURCE.addRelations(
                new Relation(OWNER),
                new Relation(CONTACT),
                new Relation(LICENSE),
                new Relation(true, SERVICE, "WritingServices"),
                new Relation(true, SERVICE, "ReadingServices"),
                new Relation(ACCESS_INTERFACE)
        );

        THING.addRelations(
                new Relation(true, LOCATION),
                new Relation(true, DATASTREAM),
                new Relation(true, HISTORICAL_LOCATION)
        );
    }

    ObjectType(Class<? extends Entity> objectClass, Class<? extends STAObjectList<? extends Entity>> listClass) {
        this.objectClass = objectClass;
        this.listClass = listClass;
    }

    public Class<?> getObjectClass() {
        return objectClass;
    }
    public Class<?> getListClass() {
        return listClass;
    }
    public List<Relation> getRelations() {
        return relations;
    }

    private void addRelations(Relation... relations) {
        this.relations.addAll(Arrays.asList(relations));
    }

    public static class Relation {
        private final boolean asList;
        private final ObjectType objectType;
        private final String name;

        public Relation(ObjectType objectType) {
            this(false, objectType);
        }

        public Relation(boolean asList, ObjectType objectType) {
            this(asList,
                    objectType,
                    (asList ? new PluralObjectTypeEncoder() : new SingularObjectTypeEncoder()).encode(objectType));
        }

        public Relation(ObjectType objectType, String name) {
            this(false, objectType, name);
        }

        public Relation(boolean asList, ObjectType objectType, String name) {
            this.asList = asList;
            this.objectType = objectType;
            this.name = name;
        }

        public boolean isAsList() {
            return asList;
        }

        public ObjectType getObjectType() {
            return objectType;
        }

        public String getName() {
            return name;
        }
    }
}