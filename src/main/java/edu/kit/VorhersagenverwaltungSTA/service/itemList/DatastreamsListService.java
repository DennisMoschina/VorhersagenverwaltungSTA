package edu.kit.VorhersagenverwaltungSTA.service.itemList;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.Datastream;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.MultiSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class DatastreamsListService extends ItemListService<Datastream> {
    private static final Set<String> KEYS = Set.of("name, description");
    @Override
    protected MultiSelection buildSelection() {
        return new MultiSelection(KEYS, null, ObjectType.DATASTREAM);
    }
}