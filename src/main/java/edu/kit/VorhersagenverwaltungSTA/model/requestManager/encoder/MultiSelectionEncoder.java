package edu.kit.VorhersagenverwaltungSTA.model.requestManager.encoder;

import edu.kit.VorhersagenverwaltungSTA.model.requestManager.selection.MultiSelection;
import edu.kit.VorhersagenverwaltungSTA.model.requestManager.selection.Selection;

public class MultiSelectionEncoder implements SelectionEncoder {
    @Override
    public String encode(Selection selection) {
        if (!(selection instanceof MultiSelection))
            throw new IllegalArgumentException("the Selection has to of type MultiSelection");

        //TODO: implement
        return "";
    }

}