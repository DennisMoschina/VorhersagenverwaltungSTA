package edu.kit.VorhersagenverwaltungSTA;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.catalogue.Catalogue;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.STAObjectList;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.CatalogueLoader;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.EnvironmentVariables;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;


public class CatalogueLoaderTest {
    private static final String CATALOGUE_LIST_FILE = "/catalogues.json";
    private static final List<Catalogue> EXPECTED_LIST;

    static {
        Catalogue catalogue1 = new Catalogue();
        catalogue1.setId(1);
        catalogue1.setName("TestCatalogue");
        catalogue1.setUrl("https://example.org/");
        catalogue1.setDescription("This catalogue is a test");

        EXPECTED_LIST = List.of(catalogue1);
    }

    @Rule
    public final EnvironmentVariables envVariables = new EnvironmentVariables();

    private CatalogueLoader loader;

    @BeforeEach
    public void setup() {
        this.loader = new CatalogueLoader();
        String resource = Objects.requireNonNull(this.getClass().getResource(CATALOGUE_LIST_FILE)).getFile();
        this.envVariables.set("CATALOGUE_LIST", resource);
    }

    @Test
    public void loadCataloguesTest() {
        STAObjectList<Catalogue> catalogues = this.loader.getCatalogues();

        Assertions.assertEquals(EXPECTED_LIST.size(), catalogues.getList().size());
        Assertions.assertEquals(EXPECTED_LIST, catalogues.getList());
    }
}