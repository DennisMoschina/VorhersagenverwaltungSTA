package edu.kit.VorhersagenverwaltungSTA.model.requestManager.encoder;

import edu.kit.VorhersagenverwaltungSTA.model.requestManager.selection.Selection;
import edu.kit.VorhersagenverwaltungSTA.model.requestManager.selection.SingleSelection;

import java.util.LinkedList;
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
    protected List<String> encodeStatements(Selection selection) {
        final List<String> encodedStatements = new LinkedList<>();

        encodedStatements.add(new ExpandEncoder().encode(selection.getObjectsToExpand()));

        return encodedStatements;
    }

    @Override
    protected String encodeHeader(Selection selection) {
        SingleSelection singleSelection = (SingleSelection) selection;
        final String encodedType = new PluralObjectTypeEncoder().encode(singleSelection.getObjectType());
        return String.format(DATA_FORMAT, encodedType, singleSelection.getSelectedId());
    }
}