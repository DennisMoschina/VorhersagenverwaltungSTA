package edu.kit.VorhersagenverwaltungSTA;

import edu.kit.VorhersagenverwaltungSTA.model.requestManager.encoder.Encoder;
import edu.kit.VorhersagenverwaltungSTA.model.requestManager.encoder.SelectionEncoder;
import edu.kit.VorhersagenverwaltungSTA.model.requestManager.encoder.SingleSelectionEncoder;
import edu.kit.VorhersagenverwaltungSTA.model.requestManager.selection.ObjectType;
import edu.kit.VorhersagenverwaltungSTA.model.requestManager.selection.SingleSelection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

        final SelectionEncoder<SingleSelection> encoder = new SingleSelectionEncoder();

        assertEquals(expectedResult, encoder.encode(selection));
    }
}