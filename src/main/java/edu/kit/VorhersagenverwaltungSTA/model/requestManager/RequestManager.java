package edu.kit.VorhersagenverwaltungSTA.model.requestManager;

import edu.kit.VorhersagenverwaltungSTA.model.dataModel.Datastream;
import edu.kit.VorhersagenverwaltungSTA.model.requestManager.encoder.SingleSelectionEncoder;
import edu.kit.VorhersagenverwaltungSTA.model.requestManager.selection.Selection;
import edu.kit.VorhersagenverwaltungSTA.model.requestManager.selection.SingleSelection;
import org.springframework.web.client.RestTemplate;

public class RequestManager {
    private final RestTemplate restTemplate;
    private final Source source;

    private Object result;
    private Class<?> resultType;

    public RequestManager(Source source, RestTemplate restTemplate) {
        this.source = source;
        this.restTemplate = restTemplate;
    }

    public void request(SingleSelection selection) { //TODO: implement for every selection
        String encodedSelection = new SingleSelectionEncoder().encode(selection);
        String request = this.source.url() + encodedSelection;

        this.resultType = selection.getObjectType().getObjectClass();

        this.result = this.restTemplate.getForObject(request, this.resultType);
    }

    public Class<?> getResultType() {
        return resultType;
    }

    public Object getResult() {
        return result;
    }
}