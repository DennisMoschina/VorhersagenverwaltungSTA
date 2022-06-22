package edu.kit.VorhersagenverwaltungSTA.model.requestManager.encoder;

/**
 * This interfaces describes an {@code Encoder}, which encodes an object to a {@code String}.
 * @param <T> the type of the Object that should be encoded
 */
public interface Encoder<T> {
    /**
     * Encode the object to a {@code String}.
     * @param t the object that should be encoded
     * @return the {@code String} representing the object
     */
    String encode(T t);
}