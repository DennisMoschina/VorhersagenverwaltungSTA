package edu.kit.VorhersagenverwaltungSTA.model.requestManager.encoder;

import edu.kit.VorhersagenverwaltungSTA.model.requestManager.selection.MultiSelection;
import edu.kit.VorhersagenverwaltungSTA.model.requestManager.selection.Selection;

import java.util.List;

public class MultiSelectionEncoder extends SelectionEncoderTemplate {
    @Override
    public String encode(Selection selection) {
        if (!(selection instanceof MultiSelection))
            throw new IllegalArgumentException("the Selection has to be of type MultiSelection");

        //TODO: implement
        return "";
    }

    @Override
    protected List<String> encodeStatements(Selection selection) {
        return null;
    }

    @Override
    protected String encodeHeader(Selection selection) {
        return null;
    }
}