package edu.kit.VorhersagenverwaltungSTA.model.core;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.RequestManager;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.Source;

import java.util.HashMap;
import java.util.Map;

public class RequestManagerFactory {
    private static final Map<Source, RequestManager> REQUEST_MANAGERS = new HashMap<>();

    public static RequestManager get(Source source) {
        if (!REQUEST_MANAGERS.containsKey(source))
            REQUEST_MANAGERS.put(source, new RequestManager(source));
        return REQUEST_MANAGERS.get(source);
    }
}