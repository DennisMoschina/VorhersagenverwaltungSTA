package edu.kit.VorhersagenverwaltungSTA;

import edu.kit.VorhersagenverwaltungSTA.model.requestManager.encoder.Encoder;
import edu.kit.VorhersagenverwaltungSTA.model.requestManager.encoder.SelectionEncoder;
import edu.kit.VorhersagenverwaltungSTA.model.requestManager.encoder.SingleSelectionEncoder;
import edu.kit.VorhersagenverwaltungSTA.model.requestManager.selection.ObjectType;
import edu.kit.VorhersagenverwaltungSTA.model.requestManager.selection.Selection;
import edu.kit.VorhersagenverwaltungSTA.model.requestManager.selection.SingleSelection;
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

    private void checkEncodedResult(String expected, SingleSelection selection) {
        final SelectionEncoder encoder = new SingleSelectionEncoder();
        assertEquals(expected, encoder.encode(selection));
    }
}