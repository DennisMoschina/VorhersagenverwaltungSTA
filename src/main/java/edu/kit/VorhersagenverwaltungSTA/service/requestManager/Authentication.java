package edu.kit.VorhersagenverwaltungSTA.service.requestManager;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * This class holds a username and a password.
 *
 * @author Dennis Moschina
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Authentication {
    @JsonProperty("user")
    private String user;
    @JsonProperty("password")
    private String password;

    public Authentication(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public Authentication() {
        this("", "");
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Authentication that)) return false;
        return Objects.equals(getUser(), that.getUser()) && Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getPassword());
    }
}