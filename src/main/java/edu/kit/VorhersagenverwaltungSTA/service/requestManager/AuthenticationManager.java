package edu.kit.VorhersagenverwaltungSTA.service.requestManager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthenticationManager {
    private Map<String, Authentication> authenticationMap;

    public AuthenticationManager() {
        this.authenticationMap = new HashMap<>();
        this.loadAuthentications();
    }

    public Authentication getAuthenticationFor(String url) {
        return this.authenticationMap.get(url);
    }

    private void loadAuthentications() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            URL resource = this.getClass().getClassLoader().getResource("auth.json");
            assert resource != null;
            File file = new File(resource.getFile());
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