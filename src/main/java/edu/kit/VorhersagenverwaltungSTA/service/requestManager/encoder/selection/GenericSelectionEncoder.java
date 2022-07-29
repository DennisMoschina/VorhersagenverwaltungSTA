package edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.MultiSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectAssociatedSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.Selection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.SingleSelection;

public class GenericSelectionEncoder implements SelectionEncoder {

    @Override
    public String encode(Selection selection) {
        SelectionEncoder encoder;
        if (selection.getClass().equals(SingleSelection.class)) {
            encoder = new SingleSelectionEncoder();
        } else if (selection.getClass().equals(MultiSelection.class)) {
            encoder = new MultiSelectionEncoder();
        } else if (selection.getClass().equals(ObjectAssociatedSelection.class)) {
            encoder = new ObjectAssociatedSelectionEncoder();
        } else {
            throw new IllegalStateException("type of Selection is unknown");
        }
        return encoder.encode(selection);
    }
}