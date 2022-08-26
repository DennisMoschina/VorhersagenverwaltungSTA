package edu.kit.VorhersagenverwaltungSTA.service;

import edu.kit.VorhersagenverwaltungSTA.model.core.RequestManagerFactory;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.RequestManager;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.Source;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractService {
    private Source source;

    protected RequestManager requestManager;

    public void setSource(Source source) {
        this.source = source;
        this.requestManager = RequestManagerFactory.get(source);
    }

    public Source getSource() {
        return this.source;
    }
}