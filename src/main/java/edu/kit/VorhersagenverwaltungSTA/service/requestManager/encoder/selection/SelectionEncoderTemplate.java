package edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.Encoder;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.ListEncoder;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.Selection;

import java.util.List;
import java.util.Set;

public abstract class SelectionEncoderTemplate implements SelectionEncoder {
    private static final String SELECT_FORMAT = "$select=%s";

    @Override
    public String encode(Selection selection) {
        final List<String> selectionParts = this.encodeParts(selection);
        if (!selection.getKeys().isEmpty())
            selectionParts.add(0, this.encodeKeys(selection.getKeys()));

        return this.encodeHeader(selection) + String.join("&", selectionParts);
    }

    /**
     * Encode every part (other than the header and the keys) in the {@link Selection}.
     * @param selection the {@link Selection} for which the parts should be encoded
     * @return the encoded parts in a {@link List} of {@link String}s
     */
    protected abstract List<String> encodeParts(Selection selection);

    /**
     * This method encodes the first part of the {@link Selection}, where it says,
     * which object should be loaded.
     * @param selection the {@link Selection} for which the header should be encoded
     * @return the encoded header as a {@link String}
     */
    protected abstract String encodeHeader(Selection selection);

    private String encodeKeys(Set<String> keys) {
        return String.format(SELECT_FORMAT, new ListEncoder<>((Encoder<String>) s -> s).encode(keys));
    }
}