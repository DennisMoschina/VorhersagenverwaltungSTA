package edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.filter;

/**
 * This class holds a single {@link String}, which resembles a filter the FROST-server can understand.
 *
 * @author Dennis Moschina
 */
public class FrostRequestFilter implements Filter {
    private String filterString;

    public FrostRequestFilter(String filterString) {
        this.filterString = filterString;
    }

    public String getFilterString() {
        return filterString;
    }

    public void setFilterString(String filterString) {
        this.filterString = filterString;
    }
}