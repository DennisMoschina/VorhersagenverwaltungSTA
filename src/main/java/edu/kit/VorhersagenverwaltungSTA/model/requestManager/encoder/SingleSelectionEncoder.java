package edu.kit.VorhersagenverwaltungSTA.model.requestManager.encoder;

import edu.kit.VorhersagenverwaltungSTA.model.requestManager.selection.SingleSelection;

import java.util.Set;

public class SingleSelectionEncoder implements SelectionEncoder<SingleSelection> {
    private static final String DATA_FORMAT = "%s(%d)?";
    private static final String SELECT_FORMAT = "$select=%s"; //TODO: allow list
    @Override
    public String encode(SingleSelection selection) {
        final String encodedType = new ObjectTypeEncoder().encode(selection.getObjectType());
        String encodedSelection = String.format(DATA_FORMAT,
                                                encodedType,
                                                selection.getSelectedId());
        if (!selection.getKeys().isEmpty()) {
            encodedSelection += "$" + String.format(SELECT_FORMAT, this.encodeKeys(selection.getKeys()));
        }

        encodedSelection += new ExpandEncoder().encode(selection.getObjectsToExpand());

        return encodedSelection;
    }

    private String encodeKeys(Set<String> keys) {
        return String.join(",", keys);
    }
}