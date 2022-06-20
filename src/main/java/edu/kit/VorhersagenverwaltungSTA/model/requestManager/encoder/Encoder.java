package edu.kit.VorhersagenverwaltungSTA.model.requestManager.encoder;

public interface Encoder<T> {
    String encode(T t);
}