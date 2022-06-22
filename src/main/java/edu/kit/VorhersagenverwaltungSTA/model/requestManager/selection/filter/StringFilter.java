package edu.kit.VorhersagenverwaltungSTA.model.requestManager.selection.filter;

public class StringFilter implements Filter {
    private String value;
    private StringFilterType type;

    public StringFilter(String value, StringFilterType type) {
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public StringFilterType getType() {
        return type;
    }

    public void setType(StringFilterType type) {
        this.type = type;
    }
}
