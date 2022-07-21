package edu.kit.VorhersagenverwaltungSTA.service.singleItem;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.Thing;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.SingleSelection;
import org.springframework.stereotype.Service;

@Service
public class ThingService extends SingleItemService<Thing> {
    @Override
    protected SingleSelection buildSelection(long id) {
        return new SingleSelection(ObjectType.THING, id);
    }
}