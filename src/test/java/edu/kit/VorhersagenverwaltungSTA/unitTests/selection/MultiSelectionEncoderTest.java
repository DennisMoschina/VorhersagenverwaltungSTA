package edu.kit.VorhersagenverwaltungSTA.unitTests.selection;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection.MultiSelectionEncoder;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection.SelectionEncoder;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.MultiSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.Selection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.SingleSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.filter.Filter;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.filter.FrostRequestFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiSelectionEncoderTest {
    private SelectionEncoder encoder;

    @BeforeEach
    public void setup() {
        this.encoder = new MultiSelectionEncoder();
    }

    @Test
    public void testWithWrongSelection() {
        final Selection selection = new SingleSelection(ObjectType.DATASTREAM, 1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.encoder.encode(selection));
    }

    @Test
    public void filteredDatastreamsTest() {
        final String filterString = "startswith(description,'test')";
        final String expected = "Datastreams?$count=true&$top=20&$skip=0&$filter=" + filterString;
        final MultiSelection selection = new MultiSelection(ObjectType.DATASTREAM);
        selection.setSkip(0);
        selection.setCount(20);
        final Filter filter = new FrostRequestFilter(filterString);
        selection.setFilter(filter);

        Assertions.assertEquals(expected, this.encoder.encode(selection));
    }
}