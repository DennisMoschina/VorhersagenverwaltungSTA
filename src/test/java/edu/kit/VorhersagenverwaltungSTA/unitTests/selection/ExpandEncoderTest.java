package edu.kit.VorhersagenverwaltungSTA.unitTests.selection;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.Encoder;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection.ExpandEncoder;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.MultiSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.Relation;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.RelationSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.Selection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.SingleSelection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class ExpandEncoderTest {
    private static final String EXPAND_FORMAT = "$expand=%s";
    private Encoder<Set<Selection>> encoder;

    @BeforeEach
    public void setup() {
        this.encoder = new ExpandEncoder();
    }

    @Test
    public void expandDatastreamListTest() {
        final String expected = String.format(EXPAND_FORMAT, "Datastreams($count=true;$top=20;$skip=0)");

        final MultiSelection datastreamListSelection = new MultiSelection(ObjectType.DATASTREAM);
        datastreamListSelection.setCount(20);
        datastreamListSelection.setSkip(0);
        final Set<Selection> expand = Set.of(datastreamListSelection);

        Assertions.assertEquals(expected, this.encoder.encode(expand));
    }

    @Test
    public void expandNamedListRelationFromServiceToMLMethodsTest() {
        final String expected = String.format(EXPAND_FORMAT, "AppliesMethods($count=true;$top=20;$skip=0)");

        final MultiSelection mlMethodListSelection = new MultiSelection(ObjectType.PROCESSING_PROCEDURE);
        mlMethodListSelection.setSkip(0);
        mlMethodListSelection.setCount(20);

        final Relation relation = ObjectType.SERVICE.getRelations()
                .stream().filter(r -> r.getName().equalsIgnoreCase("appliesMethods"))
                .findFirst().orElseThrow();

        final Selection relationSelection = new RelationSelection(mlMethodListSelection, relation);
        final Set<Selection> expand = Set.of(relationSelection);

        Assertions.assertEquals(expected, this.encoder.encode(expand));
    }

    @Test
    public void expandSingleRelationFromDatastreamToThingTest() {
        final String expected = String.format(EXPAND_FORMAT, "Thing");

        final Relation relation = ObjectType.DATASTREAM.getRelations()
                .stream().filter(r -> r.getObjectType().equals(ObjectType.THING)).findFirst().orElseThrow();
        final Selection thingSelection = new SingleSelection(ObjectType.THING, 1);
        final Selection selection = new RelationSelection(thingSelection, relation);
        final Set<Selection> expand = Set.of(selection);

        Assertions.assertEquals(expected, this.encoder.encode(expand));
    }
}