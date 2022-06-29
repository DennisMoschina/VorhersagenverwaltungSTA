package edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.Datastream;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;

public class DatastreamList extends STAObjectList<Datastream> {
    public DatastreamList() {
        super(ObjectType.DATASTREAM);
    }
}