package edu.kit.VorhersagenverwaltungSTA;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.catalogue.Catalogue;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.STAObjectList;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.CatalogueLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class CatalogueLoaderTest {
    @Autowired
    private CatalogueLoader loader;

    @Test
    public void loadCataloguesTest() {
        STAObjectList<Catalogue> catalogues = this.loader.getCatalogues();

        Assertions.assertEquals(1, catalogues.getList().size());
        Assertions.assertEquals("BML", catalogues.getList().get(0).getName());
    }
}