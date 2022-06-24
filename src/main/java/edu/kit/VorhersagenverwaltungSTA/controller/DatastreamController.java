package edu.kit.VorhersagenverwaltungSTA.controller;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.Datastream;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.Source;
import edu.kit.VorhersagenverwaltungSTA.service.singleItem.DatastreamService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class DatastreamController {
    private final Logger logger;
    private final DatastreamService singleItemService;

    @Autowired
    DatastreamController(Logger logger, DatastreamService singleItemService) {
        this.logger = logger;
        this.singleItemService = singleItemService;
        //TODO: use real source
        this.singleItemService.setSource(new Source("https://airquality-frost.k8s.ilt-dmz.iosb.fraunhofer.de/v1.1/"));
    }

    @GetMapping("/datastream/{id}")
    public Datastream getDatastream(@PathVariable long id) {
        this.logger.info(String.format("Requested Datastream with id %d", id));
        singleItemService.load(id);

        return singleItemService.getData();
    }

    @GetMapping("/datastreams/{id}/{items}/{page}")
    public List<Datastream> getDatastreamList(@PathVariable long id, @PathVariable long items, @PathVariable long page) {
        this.logger.info("Requested Datastreams");
        //TODO: implement
        return null;
    }
}