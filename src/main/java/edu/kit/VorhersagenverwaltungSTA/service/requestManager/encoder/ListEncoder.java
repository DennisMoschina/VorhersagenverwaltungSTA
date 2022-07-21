package edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * This {@link Encoder} encodes a {@link Collection} of Objects to a {@link String}.
 * @param <T> the type of the objects in the {@link Collection}
 */
public class ListEncoder<T> implements Encoder<Collection<T>> {
    private final Encoder<T> encoder;
    private final String delimiter;

    public ListEncoder(Encoder<T> encoder, String delimiter) {
        this.encoder = encoder;
        this.delimiter = delimiter;
    }

    @Override
    public String encode(Collection<T> ts) {
        List<String> encodedList = ts.stream().map(this.encoder::encode).filter(Objects::nonNull).toList();

        return String.join(delimiter, encodedList);
    }
}