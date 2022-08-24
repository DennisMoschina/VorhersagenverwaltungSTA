package edu.kit.VorhersagenverwaltungSTA.service.itemList;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.catalogue.Catalogue;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.STAObjectList;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.CatalogueLoader;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.EnvironmentVariables;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CatalogueListServiceTest {
    private static final String CATALOGUE_LIST_FILE = "/catalogues.json";

    private CatalogueListService service;

    private List<Catalogue> expectedList;

    @Rule
    public final EnvironmentVariables envVariables = new EnvironmentVariables();


    @BeforeEach
    public void setup() {
        CatalogueLoader loader = new CatalogueLoader();
        String resource = Objects.requireNonNull(this.getClass().getResource(CATALOGUE_LIST_FILE)).getFile();
        this.envVariables.set("CATALOGUE_LIST", resource);

        this.setExpectedList();
        this.service = new CatalogueListService(loader);
    }

    @Test
    public void getCatalogueListTest() {
        STAObjectList<Catalogue> result = service.getCatalogues();

        Assertions.assertEquals(expectedList, result.getList());
    }

    @Test
    public void getCatalogueWithCorrectIdTest() {
        final int catalogueId = 1;
        Catalogue result = service.getCatalogue(catalogueId);
        Catalogue expected = this.expectedList.stream()
                .filter(c -> c.getId() == catalogueId).findFirst().orElseThrow();

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getCatalogueWithWrongIdTest() {
        final int catalogueId = 0;
        Catalogue result = service.getCatalogue(catalogueId);

        Assertions.assertNull(result);
    }

    private void setExpectedList() {
        Catalogue catalogue1 = new Catalogue();
        catalogue1.setId(1);
        catalogue1.setName("TestCatalogue");
        catalogue1.setUrl("https://example.org/");
        catalogue1.setDescription("This catalogue is a test");

        expectedList = new ArrayList<>();
        expectedList.add(catalogue1);
        for (int i = 2; i <= 6; i++) {
            Catalogue newCatalogue = new Catalogue();
            newCatalogue.setId(i);
            newCatalogue.setName(String.format("TestCatalogue%d", i));
            newCatalogue.setUrl(String.format("https://example%d.org/", i));
            newCatalogue.setDescription(String.format("This catalogue%d is a test", i));

            expectedList.add(newCatalogue);
        }
    }
}