package edu.kit.VorhersagenverwaltungSTA.model.requestManager.encoder;

import java.util.Collection;
import java.util.List;

/**
 * This {@link Encoder} encodes a {@link Collection} of Objects to a {@link String}.
 * @param <T> the type of the objects in the {@link Collection}
 */
public class ListEncoder<T> implements Encoder<Collection<T>> {
    private final Encoder<T> encoder;

    public ListEncoder(Encoder<T> encoder) {
        this.encoder = encoder;
    }

    @Override
    public String encode(Collection<T> ts) {
        List<String> encodedList = ts.stream().map(this.encoder::encode).toList();

        return String.join(",", encodedList);
    }
}