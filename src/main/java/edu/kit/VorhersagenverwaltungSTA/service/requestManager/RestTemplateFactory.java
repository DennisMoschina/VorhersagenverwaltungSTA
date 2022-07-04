package edu.kit.VorhersagenverwaltungSTA.service.requestManager;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

public class RestTemplateFactory {
    private static final RestTemplateBuilder TEMPLATE_BUILDER = new RestTemplateBuilder();

    private static final AuthenticationManager AUTH_MANAGER = new AuthenticationManager();

    public static RestTemplate getRestTemplate(Source source) {
        Authentication authentication = AUTH_MANAGER.getAuthenticationFor(source.url());
        if (authentication == null) return TEMPLATE_BUILDER.build();
        return TEMPLATE_BUILDER.basicAuthentication(authentication.getUser(), authentication.getPassword()).build();
    }
}