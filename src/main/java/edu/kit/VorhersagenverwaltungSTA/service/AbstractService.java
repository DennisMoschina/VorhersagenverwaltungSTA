package edu.kit.VorhersagenverwaltungSTA.service;

import edu.kit.VorhersagenverwaltungSTA.model.core.RequestManagerFactory;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.RequestManager;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.Source;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractService<T> {
    private T data;
    private Source source;

    protected RequestManager requestManager;

    public T getData() {
        return this.data;
    }

    public void setSource(Source source) {
        this.source = source;
        this.requestManager = RequestManagerFactory.get(source);
    }

    public Source getSource() {
        return this.source;
    }

    protected void setData(T data) {
        this.data = data;
    }
}