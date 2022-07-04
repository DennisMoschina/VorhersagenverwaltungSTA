package edu.kit.VorhersagenverwaltungSTA.service.requestManager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.kit.VorhersagenverwaltungSTA.model.core.CacheProxyObjectContainer;
import edu.kit.VorhersagenverwaltungSTA.model.core.ObjectContainer;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.catalogue.Catalogue;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.STAObjectList;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.ObjectType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class CatalogueLoader {
    private static final String CATALOGUES_ENV_VARIABLE_NAME = "CATALOGUE_LIST";
    private final Logger logger = LoggerFactory.getLogger(CatalogueLoader.class);

    private final ObjectContainer<Long, Catalogue> catalogueContainer = new CacheProxyObjectContainer<>();

    public STAObjectList<Catalogue> getCatalogues() {
        STAObjectList<Catalogue> catalogues = this.loadCatalogueList();
        for (Catalogue catalogue : catalogues.getList()) this.catalogueContainer.add(catalogue.getId(), catalogue);

        return catalogues;
    }

    public Catalogue getCatalogue(long id) {
        Catalogue catalogue = this.catalogueContainer.get(id);
        if (catalogue != null) return catalogue;

        catalogue = this.loadCatalogueList().getList().stream()
                .filter(c -> c.getId() == id).findFirst().orElse(null);

        logger.info(String.format("reloaded catalogues to get catalogue with id %d", id));

        this.catalogueContainer.add(id, catalogue);
        return catalogue;
    }

    private STAObjectList<Catalogue> loadCatalogueList() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            File file = new File(System.getenv(CATALOGUES_ENV_VARIABLE_NAME));
            List<Catalogue> catalogueList = mapper.readValue(file, new TypeReference<>() {
            });
            return new STAObjectList<>(ObjectType.CATALOGUE, catalogueList);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}