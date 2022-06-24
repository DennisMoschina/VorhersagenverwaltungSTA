package edu.kit.VorhersagenverwaltungSTA.controller;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.Datastream;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class DatastreamController {
    private Logger logger;

    @Autowired
    DatastreamController(Logger logger) {
        this.logger = logger;
    }

    @GetMapping("/datastream/{id}")
    public Datastream getDatastream(@PathVariable long id) {
        this.logger.info(String.format("Requested Datastream with id %d", id));
        //TODO: implement
        return null;
    }

    @GetMapping("/datastreams/{id}/{items}/{page}")
    public List<Datastream> getDatastreamList(@PathVariable long id, @PathVariable long items, @PathVariable long page) {
        this.logger.info("Requested Datastreams");
        //TODO: implement
        return null;
    }
}