package edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.filter;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.filter.Filter;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.filter.FrostRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericFilterEncoder implements FilterEncoder {
    private static final String FILTER_FORMAT = "$filter=";
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericFilterEncoder.class);

    @Override
    public String encode(Filter filter) {
        if (filter == null) return null;

        FilterEncoder encoder;
        if (filter.getClass().equals(FrostRequestFilter.class)) {
            encoder = filter1 -> FILTER_FORMAT + ((FrostRequestFilter) filter1).getFilterString();
        } else {
            LOGGER.error("unknown filter type");
            return null;
        }
        return encoder.encode(filter);
    }
}