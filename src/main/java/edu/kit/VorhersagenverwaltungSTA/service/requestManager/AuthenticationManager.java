package edu.kit.VorhersagenverwaltungSTA.service.requestManager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This stores all {@link Authentication}s relative to the url in which the authentication is needed.
 * The authentications are loaded from a file specified in the
 * {@link AuthenticationManager#FROST_AUTH_ENV_VARIABLE_NAME} environment variable.
 *
 * @author Dennis Moschina
 */
@Service
public class AuthenticationManager {
    private static final String FROST_AUTH_ENV_VARIABLE_NAME = "FROST_AUTH";

    private final Map<String, Authentication> authenticationMap;

    public AuthenticationManager() {
        this.authenticationMap = new HashMap<>();
        this.loadAuthentications();
    }

    /**
     *
     * @param url the server we want to get the credentials for
     * @return the authentication or null, if the password is not required or not found
     */
    public Authentication getAuthenticationFor(String url) {
        return this.authenticationMap.get(url);
    }

    private void loadAuthentications() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            String path = System.getenv(FROST_AUTH_ENV_VARIABLE_NAME);
            File file = new File(path);
            List<JsonNode> jsonNodeList = mapper.readValue(file, new TypeReference<>() {
            });
            for (JsonNode node : jsonNodeList) {
                String url = node.get("url").asText();
                JsonNode authNode = node.get("auth");
                String authString = authNode.toString();
                Authentication authentication = mapper.readValue(authString, Authentication.class);
                this.authenticationMap.put(url, authentication);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}