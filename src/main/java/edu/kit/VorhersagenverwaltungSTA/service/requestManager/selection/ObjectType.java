package edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.*;

public enum ObjectType {
    DATASTREAM(Datastream.class),
    SENSOR(Sensor.class),
    THING(Thing.class),
    LOCATION(Location.class),
    HISTORICAL_LOCATION(HistoricalLocation.class),
    OBSERVED_PROPERTY(ObservedProperty.class),
    OBSERVATION(Observation.class),
    FEATURE_OF_INTEREST(FeatureOfInterest.class),
    DATASOURCE(DataSource.class),
    OWNER(Owner.class),
    CONTACT(Contact.class),
    LICENSE(License.class),
    ACCESS_INTERFACE(AccessInterface.class),
    PROCESSING_PROCEDURE(ProcessingProcedure.class);

    private final Class<?> objectClass;

    ObjectType(Class<?> objectClass) {
        this.objectClass = objectClass;
    }

    public Class<?> getObjectClass() {
        return objectClass;
    }
}