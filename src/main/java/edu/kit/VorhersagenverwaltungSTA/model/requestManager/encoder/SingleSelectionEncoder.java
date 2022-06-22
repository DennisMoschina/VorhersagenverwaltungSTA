package edu.kit.VorhersagenverwaltungSTA.model.requestManager.encoder;

import edu.kit.VorhersagenverwaltungSTA.model.requestManager.selection.Selection;
import edu.kit.VorhersagenverwaltungSTA.model.requestManager.selection.SingleSelection;

import java.util.Set;

public class SingleSelectionEncoder implements SelectionEncoder {
    private static final String DATA_FORMAT = "%s(%d)?";
    private static final String SELECT_FORMAT = "$select=%s"; //TODO: allow list
    @Override
    public String encode(Selection selection) {
        if (!(selection instanceof final SingleSelection singleSelection))
            throw new IllegalArgumentException("the Selection has to of type SingleSelection");

        final String encodedType = new ObjectTypeEncoder().encode(singleSelection.getObjectType());
        String encodedSelection = String.format(DATA_FORMAT,
                                                encodedType,
                                                singleSelection.getSelectedId());
        if (!singleSelection.getKeys().isEmpty()) {
            encodedSelection += String.format(SELECT_FORMAT, this.encodeKeys(singleSelection.getKeys()));
        }

        encodedSelection += new ExpandEncoder().encode(singleSelection.getObjectsToExpand());

        return encodedSelection;
    }

    private String encodeKeys(Set<String> keys) {
        return String.join(",", keys);
    }
}