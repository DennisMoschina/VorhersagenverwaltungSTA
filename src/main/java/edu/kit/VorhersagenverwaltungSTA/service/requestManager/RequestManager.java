package edu.kit.VorhersagenverwaltungSTA.service.requestManager;

import edu.kit.VorhersagenverwaltungSTA.model.core.CacheProxyObjectContainer;
import edu.kit.VorhersagenverwaltungSTA.model.core.ObjectContainer;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection.GenericSelectionEncoder;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.Selection;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.SingleSelection;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class RequestManager {
    private final RestTemplate restTemplate;
    private final Source source;

    private final Map<Class<?>, ObjectContainer<Long, Object>> containerMap;
    private ObjectContainer<Long, Object> container;

    private Object result;
    private Class<?> resultType;

    public RequestManager(Source source) {
        this.source = source;
        this.restTemplate = new RestTemplate();
        this.containerMap = new HashMap<>();
    }

    public void request(Selection selection) {
        //TODO: add shouldLoad back in
        /*
        if (!this.shouldLoad(selection)) {
            this.setResult(this.containers.get(selection.getObjectType().getObjectClass())
                    .get(selection.getSelectedId()));
            return;
        }
        */
        String encodedSelection = new GenericSelectionEncoder().encode(selection);
        String request = this.source.url() + encodedSelection;

        this.resultType = selection.getObjectTypeClass();

        //Object loadedObject = this.restTemplate.getForObject(request, this.resultType);
        this.result = this.restTemplate.getForObject(request, this.resultType);
    }

    public Class<?> getResultType() {
        return resultType;
    }

    public Object getResult() {
        return result;
    }

    private void setResult(Object result) {
        this.result = result;
    }

    private void setResultType(Class<?> resultType) {
        this.resultType = resultType;
        if (!containerMap.containsKey(resultType)) {
            this.containerMap.put(resultType, new CacheProxyObjectContainer<>());
        }
        this.container = this.containerMap.get(resultType);
    }

    /**
     * Check, if the Object in the selection is already in the container and if all selected attributes are loaded.
     * @param selection The selection to check.
     * @return True, if the Object in the selection is already in the container and if all selected attributes are
     * loaded.
     */
    private boolean shouldLoad(SingleSelection selection) {
        /*
        ObjectContainer<?> container = this.containers.get(selection.getObjectType().getObjectClass());
        if (container == null) return true;
        Object object = container.get(selection.getSelectedId());
        if (object == null) return true;
        */
        //TODO: check if all selected attributes are loaded
        return false;
    }
}