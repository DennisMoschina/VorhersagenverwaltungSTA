package edu.kit.VorhersagenverwaltungSTA.controller;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.DataSource;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.STAObjectList;
import edu.kit.VorhersagenverwaltungSTA.service.itemList.DataSourceListService;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.Source;
import edu.kit.VorhersagenverwaltungSTA.service.singleItem.DataSourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataSourceController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final DataSourceService singleItemService;
    private final DataSourceListService listService;

    public DataSourceController(DataSourceService singleItemService, DataSourceListService listService) {
        this.singleItemService = singleItemService;
        this.listService = listService;

        this.singleItemService.setSource(new Source("https://bml-catalog.k8s.ilt-dmz.iosb.fraunhofer.de/FROST-Server/ODATA_4.01/"));
        this.listService.setSource(new Source("https://bml-catalog.k8s.ilt-dmz.iosb.fraunhofer.de/FROST-Server/ODATA_4.01/"));
    }

    @GetMapping("/datasource/{dataSourceId}")
    DataSource getDataSource(@PathVariable long dataSourceId) {
        this.logger.info(String.format("Requested DataSource with id %d", dataSourceId));
        singleItemService.load(dataSourceId);

        return singleItemService.getData();
    }

    @GetMapping("/datasources/{items}/{page}")
    public STAObjectList<DataSource> getDatastreamList(@PathVariable int items, @PathVariable long page) {
        this.logger.info("Requested DataSources");

        long skip = items * page;

        this.listService.load(items, skip);
        return this.listService.getData();
    }
}