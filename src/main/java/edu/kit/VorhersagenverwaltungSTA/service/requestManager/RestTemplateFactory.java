package edu.kit.VorhersagenverwaltungSTA.service.requestManager;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

/**
 * This class is a factory for the {@link RestTemplate}. It adds an authentication if necessary.
 *
 * @author Dennis Moschina
 */
public class RestTemplateFactory {
    private static final RestTemplateBuilder TEMPLATE_BUILDER = new RestTemplateBuilder();

    private static final AuthenticationFactory AUTH_MANAGER = new AuthenticationFactory();

    /**
     * Get a {@link RestTemplate} with the correct authentication, if necessary.
     *
     * @param source the server for which the {@link RestTemplate} should have an authentication
     * @return the {@link RestTemplate}
     */
    public static RestTemplate getRestTemplate(Source source) {
        Authentication authentication = AUTH_MANAGER.getAuthenticationFor(source.url());
        if (authentication == null) return TEMPLATE_BUILDER.build();
        return TEMPLATE_BUILDER.basicAuthentication(authentication.getUser(), authentication.getPassword()).build();
    }
}