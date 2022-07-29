package edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.filter.GenericFilterEncoder;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.MultiSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.Selection;

import java.util.LinkedList;
import java.util.List;

public class MultiSelectionEncoder extends SelectionEncoderTemplate {
    private static final String TOP_FORMAT = "$top=%d";
    private static final String SKIP_FORMAT = "$skip=%d";
    private static final String COUNT_FORMAT = "$count=true";

    @Override
    public String encode(Selection selection) {
        if (!(selection instanceof MultiSelection))
            throw new IllegalArgumentException("the Selection has to be of type MultiSelection");

        return super.encode(selection);
    }

    @Override
    public List<String> encodeParts(Selection selection) {
        MultiSelection multiSelection = (MultiSelection) selection;
        List<String> encodedParts = new LinkedList<>();
        encodedParts.add(COUNT_FORMAT);
        encodedParts.add(new ExpandEncoder().encode(selection.getObjectsToExpand()));
        encodedParts.add(String.format(TOP_FORMAT, multiSelection.getCount()));
        encodedParts.add(String.format(SKIP_FORMAT, multiSelection.getSkip()));
        encodedParts.add(new OrderEncoder().encode(multiSelection.getOrderBy()));
        encodedParts.add(new GenericFilterEncoder().encode(multiSelection.getFilter()));

        return encodedParts;
    }

    @Override
    protected String encodeHeader(Selection selection) {
        return new PluralObjectTypeEncoder().encode(selection.getObjectType());
    }
}