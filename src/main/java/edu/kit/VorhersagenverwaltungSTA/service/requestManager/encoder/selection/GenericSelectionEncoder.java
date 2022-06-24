package edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.Selection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.SingleSelection;

public class GenericSelectionEncoder implements SelectionEncoder {

    @Override
    public String encode(Selection selection) {
        SelectionEncoder encoder;
        if (selection.getClass().equals(SingleSelection.class)) {
            encoder = new SingleSelectionEncoder();
        } else {
            encoder = new MultiSelectionEncoder();
        }
        return encoder.encode(selection);
    }
}