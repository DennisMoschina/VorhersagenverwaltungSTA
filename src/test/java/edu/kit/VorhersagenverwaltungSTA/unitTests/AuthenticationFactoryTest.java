package edu.kit.VorhersagenverwaltungSTA.unitTests;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.Authentication;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.AuthenticationFactory;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.EnvironmentVariables;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Objects;

public class AuthenticationFactoryTest {
    private static final String AUTHENTICATIONS_FILE = "/auth.json";
    private static final String AUTH_ENV_NAME = "FROST_AUTH";
    private static final Map<String, Authentication> EXPECTED
            = Map.of("https://example.com", new Authentication("user", "*zav&4kc"));

    private AuthenticationFactory authFactory;


    @Rule
    public final EnvironmentVariables environmentVariables = new EnvironmentVariables();

    @BeforeEach
    public void setup() {
        this.environmentVariables.set(AUTH_ENV_NAME,
                Objects.requireNonNull(this.getClass().getResource(AUTHENTICATIONS_FILE)).getFile());
        this.authFactory = new AuthenticationFactory();
    }

    @Test
    public void testAuthentication() {
        Authentication auth = authFactory.getAuthenticationFor("https://example.org");
        Assertions.assertEquals(EXPECTED.get("https://example.com"), auth);
    }
}