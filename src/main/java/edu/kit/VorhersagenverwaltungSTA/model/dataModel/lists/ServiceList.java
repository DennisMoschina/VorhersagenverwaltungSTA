package edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.ProcessingService;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;

public class ServiceList extends STAObjectList<ProcessingService> {
    public ServiceList() {
        super(ObjectType.THING);
    }
}