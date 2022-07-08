package edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.kit.VorhersagenverwaltungSTA.jackson.InstantDeserializer;
import edu.kit.VorhersagenverwaltungSTA.jackson.IntervalDeserializer;
import org.threeten.extra.Interval;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * A time object can be an {@link Instant} or an {@link Interval}.
 *
 * @author Hylke van der Schaaf, Elias Dirks
 */
public class TimeObject {

    @JsonDeserialize(using = IntervalDeserializer.class)
    private Interval valueInterval;
    @JsonDeserialize(using = InstantDeserializer.class)
    private Instant valueInstant;
    private final boolean interval;

    public TimeObject(Interval value) {
        valueInterval = value;
        interval = true;
    }

    public TimeObject(Instant value) {
        valueInstant = value;
        interval = false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TimeObject other = (TimeObject) obj;
        if (this.interval != other.interval) {
            return false;
        }
        if (!Objects.equals(this.valueInterval, other.valueInterval)) {
            return false;
        }
        return Objects.equals(this.valueInstant, other.valueInstant);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.valueInterval);
        hash = 29 * hash + Objects.hashCode(this.valueInstant);
        hash = 29 * hash + (this.interval ? 1 : 0);
        return hash;
    }

    public boolean isInterval() {
        return interval;
    }

    public Interval getAsInterval() {
        if (interval) {
            return valueInterval;
        }
        return null;
    }

    public Instant getAsDateTime() {
        if (interval) {
            return null;
        }
        return valueInstant;
    }

    @Override
    public String toString() {
        if (interval) {
            return valueInterval.toString();
        }
        return valueInstant.toString();
    }

    public static TimeObject parse(String value) {
        try {
            Instant dt = Instant.parse(value);
            return new TimeObject(dt);
        } catch (DateTimeParseException e) {
            // Not a DateTime
        }
        Interval interval = Interval.parse(value);
        return new TimeObject(interval);
    }
}
