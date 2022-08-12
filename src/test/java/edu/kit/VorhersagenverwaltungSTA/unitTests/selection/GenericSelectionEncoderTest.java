package edu.kit.VorhersagenverwaltungSTA.unitTests.selection;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection.GenericSelectionEncoder;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection.SelectionEncoder;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.MultiSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectAssociatedSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.Selection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.SingleSelection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

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

    @Test
    public void simpleObjectAssociatedSelectionDatastreamThingTest() {
        final String expected = "Datastreams(1)/Thing?";
        final SingleSelection source = new SingleSelection(ObjectType.DATASTREAM, 1);
        final Selection destination = new SingleSelection(ObjectType.THING, -1);
        final Selection selection = new ObjectAssociatedSelection(source, destination);
        final String result = this.encoder.encode(selection);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testUnknownSelection() {
        final Selection selection = new Selection(new HashSet<>(), new HashSet<>(), ObjectType.DATASTREAM) { };

        Assertions.assertThrows(IllegalArgumentException.class, () -> this.encoder.encode(selection));
    }
}