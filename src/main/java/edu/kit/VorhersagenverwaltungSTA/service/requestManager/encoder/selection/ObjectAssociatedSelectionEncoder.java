package edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectAssociatedSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.Selection;

import java.util.Objects;
import java.util.stream.Collectors;

public class ObjectAssociatedSelectionEncoder implements SelectionEncoder {
    private static final String SELECTION_FORMAT = "%s/%s";
    private static final String ASSOCIATED_FORMAT = "%s?%s";

    @Override
    public String encode(Selection selection) {
        if (!(selection instanceof ObjectAssociatedSelection associatedSelection))
            throw new IllegalArgumentException("selection must be of type ObjectAssociatedSelection");

        String encodedSource = new SingleSelectionEncoder().encodeHeader(associatedSelection.getSourceObjectSelection());

        ObjectType.Relation relation = associatedSelection.getRelation();

        if (relation == null) {
            return String.format(SELECTION_FORMAT,
                    encodedSource,
                    new GenericSelectionEncoder().encode(associatedSelection.getSelection()));
        }

        SelectionEncoderTemplate encoder
                = relation.isAsList() ? new MultiSelectionEncoder() : new SingleSelectionEncoder();

        return String.format(SELECTION_FORMAT,
                encodedSource,
                String.format(ASSOCIATED_FORMAT,
                        relation.getName(),
                        encoder.encodeParts(associatedSelection.getSelection()).stream().filter(Objects::nonNull)
                                .collect(Collectors.joining("&"))));
    }
}