package edu.kit.VorhersagenverwaltungSTA.service.requestManager;

import java.util.Objects;

/**
 *
 * @param url the link to the source
 */
public record Source(String url) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Source source)) return false;
        return url.equals(source.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}