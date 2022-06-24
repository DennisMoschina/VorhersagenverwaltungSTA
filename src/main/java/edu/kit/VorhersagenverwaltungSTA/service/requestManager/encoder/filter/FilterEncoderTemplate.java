package edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.filter;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.filter.Filter;

import java.util.List;

public abstract class FilterEncoderTemplate implements FilterEncoder {
    @Override
    public String encode(Filter filter) {
        List<String> encodedParts = this.encodeParts();

        return null;
    }

    protected abstract List<String> encodeParts();
}