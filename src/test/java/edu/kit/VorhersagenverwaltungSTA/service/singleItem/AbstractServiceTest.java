package edu.kit.VorhersagenverwaltungSTA.service.singleItem;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.SingleSelection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

public abstract class AbstractServiceTest {
    protected final ObjectType type;
    protected SingleItemService<?> service;
    protected final Set<String> keys;

    public AbstractServiceTest(ObjectType type) {
        this.type = type;
        this.keys = Set.of("id");
    }

    @BeforeEach
    public void setup() {
        assign();
    }

    protected abstract void assign();

    @Test
    public void test() {
        final int id = 1;
        SingleSelection result = this.service.buildSelection(id);
        Assertions.assertEquals(id, result.getSelectedId());
        Assertions.assertEquals(type, result.getObjectType());
        Assertions.assertEquals(keys, result.getKeys());
    }
}