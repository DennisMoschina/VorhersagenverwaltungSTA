package edu.kit.VorhersagenverwaltungSTA.model.requestManager.encoder;

import edu.kit.VorhersagenverwaltungSTA.model.requestManager.selection.ObjectType;

public class ObjectTypeEncoder implements Encoder<ObjectType> {
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
            case DATASOURCE -> encoded = "Datasource";
            case OWNER -> encoded = "Owner";
            case CONTACT -> encoded = "Contact";
            case LICENSE -> encoded = "License";
            case ACCESS_INTERFACE -> encoded = "AccessInterface";
            case PROCESSING_PROCEDURE -> encoded = "MLMethod";
        }
        return encoded;
    }
}