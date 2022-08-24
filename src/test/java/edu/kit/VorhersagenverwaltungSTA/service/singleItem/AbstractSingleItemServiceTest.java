package edu.kit.VorhersagenverwaltungSTA.service.singleItem;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.Entity;
import edu.kit.VorhersagenverwaltungSTA.service.AbstractServiceTest;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.SingleSelection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public abstract class AbstractSingleItemServiceTest extends AbstractServiceTest {
    protected final Set<String> keys;

    public AbstractSingleItemServiceTest(ObjectType type) {
        super(type);
        this.keys = Set.of("id");
    }

    @Test
    public void testBuildSelection() {
        final int id = 1;
        SingleSelection result = ((SingleItemService<?>) this.service).buildSelection(id);
        Assertions.assertEquals(id, result.getSelectedId());
        Assertions.assertEquals(type, result.getObjectType());
        Assertions.assertEquals(keys, result.getKeys());
    }

    @Test
    public void testLoad() {
        final int id = 1;
        ((SingleItemService<?>) this.service).load(id);
        Entity result = (Entity) this.service.getData();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(id, result.getId());
    }

    @Test
    public void testLoadAssociated() {
        ((SingleItemService<?>) this.service).getFromAssociatedObject(ObjectType.DATASOURCE, 1);
        Entity result = (Entity) this.service.getData();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(type.getObjectClass(), result.getClass());
    }
}