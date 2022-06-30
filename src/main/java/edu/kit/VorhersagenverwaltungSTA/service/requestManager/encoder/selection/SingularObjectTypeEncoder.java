package edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.Encoder;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;

public class SingularObjectTypeEncoder implements Encoder<ObjectType> {
    @Override
    public String encode(ObjectType objectType) {
        String encoded = "";
        switch (objectType) {
            case DATASTREAM -> encoded = "Datastream";
            case SENSOR -> encoded = "Sensor";
            case THING -> encoded = "Thing";
            case LOCATION -> encoded = "Location";
            case HISTORICAL_LOCATION -> encoded = "HistoricalLocation";
            case OBSERVED_PROPERTY -> encoded = "ObservedProperty";
            case OBSERVATION -> encoded = "Observation";
            case FEATURE_OF_INTEREST -> encoded = "FeatureOfInterest";
            case DATASOURCE -> encoded = "DataSource";
            case OWNER -> encoded = "Owner";
            case CONTACT -> encoded = "Contact";
            case LICENSE -> encoded = "License";
            case ACCESS_INTERFACE -> encoded = "AccessInterface";
            case PROCESSING_PROCEDURE -> encoded = "MLMethod";
        }
        return encoded;
    }
}