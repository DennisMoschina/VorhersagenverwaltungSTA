package edu.kit.VorhersagenverwaltungSTA.service.singleItem;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.Datastream;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.SingleSelection;
import org.springframework.stereotype.Service;

@Service
public class DatastreamService extends SingleItemService<Datastream> {
    @Override
    protected SingleSelection buildSelection(long id) {
        return new SingleSelection(ObjectType.DATASTREAM, id);
    }
}