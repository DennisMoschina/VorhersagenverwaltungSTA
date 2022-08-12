package edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.Encoder;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.ListEncoder;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.MultiSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.RelationSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.Selection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.SingleSelection;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ExpandEncoder implements Encoder<Set<Selection>> {
    private static final String EXPAND_FORMAT = "$expand=%s";

    @Override
    public String encode(Set<Selection> selections) {
        if (selections == null || selections.isEmpty()) return null;

        List<String> encodedSelections = selections.stream()
                .map(selection -> new SingleExpandEncoder().encode(selection)).filter(Objects::nonNull).toList();

        return String.format(EXPAND_FORMAT, String.join(",", encodedSelections));
    }

    private static class SingleExpandEncoder implements SelectionEncoder {
        private static final String EXPAND_ITEM_FORMAT = "%s(%s)";

        @Override
        public String encode(Selection selection) {
            SelectionEncoderTemplate encoder;
            Encoder<ObjectType> headerEncoder;
            Selection selectionToEncode = selection;

            if (selection.getClass().equals(MultiSelection.class)) {
                encoder = new MultiSelectionEncoder();
                headerEncoder = new PluralObjectTypeEncoder();
            } else if (selection.getClass().equals(SingleSelection.class)) {
                encoder = new SingleSelectionEncoder();
                headerEncoder = new SingularObjectTypeEncoder();
            } else if (selection.getClass().equals(RelationSelection.class)) {
                RelationSelection relationSelection = (RelationSelection) selection;
                if (relationSelection.getRelation().isAsList()) {
                    encoder = new MultiSelectionEncoder();
                    headerEncoder = new PluralObjectTypeEncoder();
                } else {
                    encoder = new SingleSelectionEncoder();
                    headerEncoder = new SingularObjectTypeEncoder();
                }
                if (relationSelection.getRelation().getName() != null)
                    headerEncoder = objectType -> relationSelection.getRelation().getName();
                selectionToEncode = relationSelection.getSelection();
            } else throw new IllegalStateException("Unknown Selection type");

            final List<String> encodedParts = encoder.encodeParts(selectionToEncode);

            final String encodedHeader = headerEncoder.encode(selection.getObjectType());

            if (encodedParts == null || encodedParts.isEmpty()) return encodedHeader;

            return String.format(EXPAND_ITEM_FORMAT, encodedHeader,
                    new ListEncoder<>((Encoder<String>) s -> s, ";").encode(encodedParts));
        }
    }
}