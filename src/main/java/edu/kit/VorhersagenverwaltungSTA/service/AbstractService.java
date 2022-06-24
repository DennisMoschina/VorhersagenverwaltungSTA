package edu.kit.VorhersagenverwaltungSTA.service;

import edu.kit.VorhersagenverwaltungSTA.model.core.ObjectContainer;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.RequestManager;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public abstract class AbstractService<T> {
    private T data;
    private Source source;
    @Autowired
    private RestTemplate restTemplate; //TODO: find better approach

    private final ObjectContainer<Source, RequestManager> requestManagerContainer = new ObjectContainer<>();
    protected RequestManager requestManager;

    public T getData() {
        return this.data;
    }

    public void setSource(Source source) {
        this.source = source;
        if (!this.requestManagerContainer.contains(source)) {
            RequestManager newRequestManager = new RequestManager(source, this.restTemplate);
            this.requestManagerContainer.add(source, newRequestManager);
        }
        this.requestManager = this.requestManagerContainer.get(source);
    }

    public Source getSource() {
        return this.source;
    }

    protected void setData(T data) {
        this.data = data;
    }
}