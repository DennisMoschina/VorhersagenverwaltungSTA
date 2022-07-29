package edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectAssociatedSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.Selection;

public class ObjectAssociatedSelectionEncoder implements SelectionEncoder {
    private static final String SELECTION_FORMAT = "%s/%s";

    @Override
    public String encode(Selection selection) {
        if (!(selection instanceof ObjectAssociatedSelection associatedSelection))
            throw new IllegalArgumentException("selection must be of type ObjectAssociatedSelection");

        return String.format(SELECTION_FORMAT,
                new SingleSelectionEncoder().encodeHeader(associatedSelection.getSourceObjectSelection()),
                new GenericSelectionEncoder().encode(associatedSelection.getSelection()));
    }
}