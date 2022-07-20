package edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.Thing;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;

public class ThingList extends STAObjectList<Thing> {
    public ThingList() {
        super(ObjectType.THING);
    }
}