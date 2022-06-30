package edu.kit.VorhersagenverwaltungSTA.controller;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.datastream.Datastream;
import edu.kit.VorhersagenverwaltungSTA.model.dataModel.lists.STAObjectList;
import edu.kit.VorhersagenverwaltungSTA.service.itemList.DatastreamsListService;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.Source;
import edu.kit.VorhersagenverwaltungSTA.service.singleItem.DatastreamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:4200")
//@RequestMapping("/test")
class DatastreamController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final DatastreamService singleItemService;
    private final DatastreamsListService listService;

    @Autowired
    DatastreamController(DatastreamService singleItemService, DatastreamsListService listService) {
        this.singleItemService = singleItemService;
        this.listService = listService;
        //TODO: use real source
        this.singleItemService.setSource(new Source("https://airquality-frost.k8s.ilt-dmz.iosb.fraunhofer.de/v1.1/"));
        this.listService.setSource(new Source("https://airquality-frost.k8s.ilt-dmz.iosb.fraunhofer.de/v1.1/"));
    }

    @GetMapping("/datastream/{datastreamID}")
    public Datastream getDatastream(@PathVariable long datastreamID) {
        this.logger.info(String.format("Requested Datastream with id %d", datastreamID));
        singleItemService.load(datastreamID);

        return singleItemService.getData();
    }

    @GetMapping("/datastreams/{items}/{page}")
    public STAObjectList<Datastream> getDatastreamList(@PathVariable int items, @PathVariable long page) {
        this.logger.info("Requested Datastreams");

        long skip = items * page;

        this.listService.load(items, skip);
        return this.listService.getData();
    }
}