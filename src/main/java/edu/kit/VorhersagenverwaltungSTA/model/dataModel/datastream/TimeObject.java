package edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TimeObject {

    private static final String INTERVAL_SEPARATOR = "/";
    @JsonDeserialize(using = IntervalDeserializer.class)
    private Interval valueInterval;
    @JsonDeserialize(using = InstantDeserializer.class)
    private Instant valueInstant;
    @JsonIgnore
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

    @JsonIgnoreProperties({"empty","unboundedStart","unboundedEnd"})
    public Interval getTimeInterval() {
        if (interval) {
            return valueInterval;
        }
        return null;
    }

    public Instant getDateTime() {
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
            if (value.contains(INTERVAL_SEPARATOR)) {
                Interval interval = Interval.parse(value);
                return new TimeObject(interval);
            } else {
                Instant dt = Instant.parse(value);
                return new TimeObject(dt);
            }
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
