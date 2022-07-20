package edu.kit.VorhersagenverwaltungSTA.unitTests;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection.GenericSelectionEncoder;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection.SelectionEncoder;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.MultiSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.Selection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.SingleSelection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GenericSelectionEncoderTest {
    private SelectionEncoder encoder;

    @BeforeEach
    public void setup() {
        this.encoder = new GenericSelectionEncoder();
    }

    @Test
    public void simpleSingleSelectionDatastreamTest() {
        final String expected = "Datastreams(1)?";
        final Selection selection = new SingleSelection(ObjectType.DATASTREAM, 1);
        final String result = this.encoder.encode(selection);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void simpleMultiSelectionDatastreamTest() {
        final String expected = "Datastreams?$count=true&$top=20&$skip=0";
        final Selection selection = new MultiSelection(ObjectType.DATASTREAM);
        final String result = this.encoder.encode(selection);

        Assertions.assertEquals(expected, result);
    }
}