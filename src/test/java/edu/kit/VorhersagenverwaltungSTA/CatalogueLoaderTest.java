package edu.kit.VorhersagenverwaltungSTA;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.catalogue.Catalogue;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.CatalogueLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class CatalogueLoaderTest {
    @Autowired
    private CatalogueLoader loader;

    @Test
    public void loadCataloguesTest() {
        this.loader.loadCatalogues();
        List<Catalogue> catalogues = this.loader.getCatalogues();

        Assertions.assertEquals(1, catalogues.size());
        Assertions.assertEquals("BML", catalogues.get(0).getName());
    }
}