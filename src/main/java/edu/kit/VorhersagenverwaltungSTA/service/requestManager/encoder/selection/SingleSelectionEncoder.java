package edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.Selection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.SingleSelection;

import java.util.ArrayList;
import java.util.List;

public class SingleSelectionEncoder extends SelectionEncoderTemplate {
    private static final String DATA_FORMAT = "%s(%d)?";
    @Override
    public String encode(Selection selection) {
        if (!(selection instanceof SingleSelection))
            throw new IllegalArgumentException("the Selection has to be of type SingleSelection");

        return super.encode(selection);
    }

    @Override
    public List<String> encodeParts(Selection selection) {
        if (selection.getObjectsToExpand().isEmpty()) return new ArrayList<>();
        return List.of(new ExpandEncoder().encode(selection.getObjectsToExpand()));
    }

    @Override
    protected String encodeHeader(Selection selection) {
        SingleSelection singleSelection = (SingleSelection) selection;
        final String encodedType = new PluralObjectTypeEncoder().encode(singleSelection.getObjectType());
        return String.format(DATA_FORMAT, encodedType, singleSelection.getSelectedId());
    }
}