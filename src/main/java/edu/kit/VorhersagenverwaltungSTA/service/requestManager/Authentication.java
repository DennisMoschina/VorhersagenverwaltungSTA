package edu.kit.VorhersagenverwaltungSTA.service.requestManager;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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
}