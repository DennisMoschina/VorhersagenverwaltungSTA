package edu.kit.VorhersagenverwaltungSTA;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection.SelectionEncoderTemplate;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection.SingleSelectionEncoder;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.SingleSelection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingleSelectionTest {


    @BeforeAll
    public static void setUpClass() {

    }

    @BeforeEach
    public void setUp() {

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
        final String expectedResult = "Datastreams(1)?$select=name,description";

        this.checkEncodedResult(expectedResult, selection);
    }

    @Test
    public void expandTestWithThingTest() {
        final SingleSelection selection = new SingleSelection(ObjectType.DATASTREAM, 1);
        selection.addObjectToExpand(new SingleSelection(ObjectType.THING));
        final String expected = "Datastreams(1)?$expand=Thing";

        this.checkEncodedResult(expected, selection);
    }

    private void checkEncodedResult(String expected, SingleSelection selection) {
        final SelectionEncoderTemplate encoder = new SingleSelectionEncoder();
        assertEquals(expected, encoder.encode(selection));
    }
}