package edu.kit.VorhersagenverwaltungSTA.model.requestManager.selection;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.Datastream;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.Sensor;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.Thing;

public enum ObjectType {
    DATASTREAM(Datastream.class),
    SENSOR(Sensor.class),
    THING(Thing.class),
    LOCATION(null),
    HISTORICAL_LOCATION(null),
    OBSERVED_PROPERTY(null),
    OBSERVATION(null),
    FEATURE_OF_INTEREST(null),
    DATASOURCE(null),
    OWNER(null),
    CONTACT(null),
    LICENSE(null),
    ACCESS_INTERFACE(null),
    PROCESSING_PROCEDURE(null);

    private final Class<?> objectClass;

    ObjectType(Class<?> objectClass) {
        this.objectClass = objectClass;
    }

    public Class<?> getObjectClass() {
        return objectClass;
    }
}