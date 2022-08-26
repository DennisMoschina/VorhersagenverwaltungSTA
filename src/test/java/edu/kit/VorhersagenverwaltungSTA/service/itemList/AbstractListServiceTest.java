package edu.kit.VorhersagenverwaltungSTA.service.itemList;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.STAObjectList;
import edu.kit.VorhersagenverwaltungSTA.service.AbstractServiceTest;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection.PrimitiveDefaultKeysFactory;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.MultiSelection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractListServiceTest extends AbstractServiceTest {
    protected final Set<String> keys;

    public AbstractListServiceTest(ObjectType type) {
        super(type);
        this.keys = new HashSet<>(new PrimitiveDefaultKeysFactory().getDefaultKeys(type));
        this.keys.add("id");
    }

    @Test
    public void testBuildSelection() {
        final int count = 10;
        final int startIndex = 0;
        MultiSelection result = ((ItemListService<?>) service).buildSelection(count, startIndex);

        Assertions.assertEquals(type, result.getObjectType());
        Assertions.assertEquals(count, result.getCount());
        Assertions.assertEquals(startIndex, result.getSkip());
        Assertions.assertEquals(keys, result.getKeys());
    }

    @Test
    public void testLoad() {
        STAObjectList<?> result = ((ItemListService<?>) this.service).load(5, 0);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(this.type, result.getType());
    }

    @Test
    public void testLoadAssociated() {
        STAObjectList<?> result
                = ((ItemListService<?>) this.service).getFromAssociatedObject(ObjectType.DATASOURCE, 1, 5, 0);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(type.getListClass(), result.getClass());
    }
}