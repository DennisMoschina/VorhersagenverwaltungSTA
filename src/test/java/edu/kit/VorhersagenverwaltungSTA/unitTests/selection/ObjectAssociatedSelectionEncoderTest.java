package edu.kit.VorhersagenverwaltungSTA.unitTests.selection;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection.ObjectAssociatedSelectionEncoder;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection.SelectionEncoder;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.MultiSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectAssociatedSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.Selection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.SingleSelection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ObjectAssociatedSelectionEncoderTest {
    private SelectionEncoder encoder;

    @BeforeEach
    public void setup() {
        this.encoder = new ObjectAssociatedSelectionEncoder();
    }

    @Test
    public void testWithSingleSelection() {
        final Selection selection = new SingleSelection(ObjectType.DATASTREAM, 1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.encoder.encode(selection));
    }

    @Test
    public void testWithMultiSelection() {
        final Selection selection = new MultiSelection(ObjectType.DATASTREAM);
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.encoder.encode(selection));
    }

    @Test
    public void testWithNamedRelationByNameFromServiceToMLMethod() {
        final String expected = "Services(1)/AppliesMethods?$count=true&$top=10&$skip=0";

        final SingleSelection source = new SingleSelection(ObjectType.SERVICE, 1);
        final MultiSelection destinationSelection = new MultiSelection(ObjectType.PROCESSING_PROCEDURE);
        destinationSelection.setCount(10);
        destinationSelection.setSkip(0);
        final Selection selection = new ObjectAssociatedSelection(source, destinationSelection, "appliesMethods");

        Assertions.assertEquals(expected, this.encoder.encode(selection));
    }
}