package edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.Location;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;

public class LocationList extends STAObjectList<Location> {
    public LocationList() {
        super(ObjectType.LOCATION);
    }
}