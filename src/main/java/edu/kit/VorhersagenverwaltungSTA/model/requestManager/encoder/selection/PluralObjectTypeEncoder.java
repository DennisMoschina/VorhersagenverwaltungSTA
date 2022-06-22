package edu.kit.VorhersagenverwaltungSTA.model.requestManager.encoder.selection;

import edu.kit.VorhersagenverwaltungSTA.model.requestManager.encoder.Encoder;
import edu.kit.VorhersagenverwaltungSTA.model.requestManager.selection.ObjectType;

public class PluralObjectTypeEncoder implements Encoder<ObjectType> {
    @Override
    public String encode(ObjectType objectType) {
        String encoded = "";
        switch (objectType) {
            case DATASTREAM -> encoded = "Datastreams";
            case SENSOR -> encoded = "Sensors";
            case THING -> encoded = "Things";
            case LOCATION -> encoded = "Locations";
            case HISTORICAL_LOCATION -> encoded = "HistoricalLocations";
            case OBSERVED_PROPERTY -> encoded = "ObservedProperties";
            case OBSERVATION -> encoded = "Observations";
            case FEATURE_OF_INTEREST -> encoded = "FeaturesOfInterest";
            case DATASOURCE -> encoded = "Datasources";
            case OWNER -> encoded = "Owners";
            case CONTACT -> encoded = "Contacts";
            case LICENSE -> encoded = "Licenses";
            case ACCESS_INTERFACE -> encoded = "AccessInterfaces";
            case PROCESSING_PROCEDURE -> encoded = "MLMethods";
        }
        return encoded;
    }
}