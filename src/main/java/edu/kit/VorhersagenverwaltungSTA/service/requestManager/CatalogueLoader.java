package edu.kit.VorhersagenverwaltungSTA.service.requestManager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.catalogue.Catalogue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class CatalogueLoader {
    private final Logger logger = LoggerFactory.getLogger(CatalogueLoader.class);
    private List<Catalogue> catalogues;

    public void loadCatalogues() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            this.catalogues = mapper.readValue(new File(Objects.requireNonNull(getClass()
                    .getClassLoader().getResource("catalogues.json")).getFile()), new TypeReference<>() {
            });
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<Catalogue> getCatalogues() {
        return List.copyOf(this.catalogues);
    }
}