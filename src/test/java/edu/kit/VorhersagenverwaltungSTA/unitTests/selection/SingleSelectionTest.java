package edu.kit.VorhersagenverwaltungSTA.unitTests.selection;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection.SingleSelectionEncoder;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.MultiSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.SingleSelection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SingleSelectionTest {

    private SingleSelectionEncoder encoder;

    @BeforeEach
    public void setup() {
        this.encoder = new SingleSelectionEncoder();
    }

    @Test
    public void simpleSelectionTest() {
        final SingleSelection selection = new SingleSelection(ObjectType.DATASTREAM, 1);
        final String expectedResult = "Datastreams(1)?";

        this.checkEncodedResult(expectedResult, selection);
    }

    @Test
    public void selectKeysTest() {
        Set<String> keys = Set.of("name", "description");
        final SingleSelection selection = new SingleSelection(keys, ObjectType.DATASTREAM, 1);
        //TODO: make sure, that the order of keys is not important
        final String expectedResult = "Datastreams(1)?$select=name,description,id";

        this.checkEncodedResult(expectedResult, selection);
    }

    @Test
    public void expandTestWithThingTest() {
        final SingleSelection selection = new SingleSelection(ObjectType.DATASTREAM, 1);
        selection.addObjectToExpand(new SingleSelection(ObjectType.THING));
        final String expected = "Datastreams(1)?$expand=Thing";

        this.checkEncodedResult(expected, selection);
    }

    @Test
    public void wrongSelectionTest() {
        final MultiSelection selection = new MultiSelection(ObjectType.DATASTREAM);
        assertThrows(IllegalArgumentException.class, () -> encoder.encode(selection));
    }

    private void checkEncodedResult(String expected, SingleSelection selection) {
        assertEquals(expected, encoder.encode(selection));
    }
}