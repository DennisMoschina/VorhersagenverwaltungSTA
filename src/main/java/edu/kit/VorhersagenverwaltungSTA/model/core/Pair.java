package edu.kit.VorhersagenverwaltungSTA.model.core;

/**
 * This class describes a pair.
 *
 * @param <S> type of the first element
 * @param <T> type of the second element
 */
public class Pair<S, T> {
    private final S first;
    private final T second;

    /**
     * Constructs a new pair
     *
     * @param first the first element
     * @param second the second element
     */
    public Pair(final S first, final T second){
        this.first = first;
        this.second = second;
    }

    /**
     * Returns the first Element
     *
     * @return the first element
     */
    public S getFirst() {
        return this.first;
    }

    /**
     * Returns the second element
     *
     * @return the second element
     */
    public T getSecond() {
        return this.second;
    }
}
