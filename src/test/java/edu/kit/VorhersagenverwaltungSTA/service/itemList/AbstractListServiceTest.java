package edu.kit.VorhersagenverwaltungSTA.service.itemList;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection.PrimitiveDefaultKeysFactory;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.MultiSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractListServiceTest {
    protected final ObjectType type;
    protected final Set<String> keys;
    protected ItemListService<?> service;

    public AbstractListServiceTest(ObjectType type) {
        this.type = type;
        this.keys = new HashSet<>(new PrimitiveDefaultKeysFactory().getDefaultKeys(type));
        this.keys.add("id");
    }

    @BeforeEach
    public void setup() {
        assign();
    }

    protected abstract void assign();

    @Test
    public void testBuildSelection() {
        final int count = 10;
        final int startIndex = 0;
        MultiSelection result = service.buildSelection(count, startIndex);

        Assertions.assertEquals(type, result.getObjectType());
        Assertions.assertEquals(count, result.getCount());
        Assertions.assertEquals(startIndex, result.getSkip());
        Assertions.assertEquals(keys, result.getKeys());
    }
}