package edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.Observation;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;

public class ObservationList extends STAObjectList<Observation> {
    public ObservationList() {
        super(ObjectType.OBSERVATION);
    }
}