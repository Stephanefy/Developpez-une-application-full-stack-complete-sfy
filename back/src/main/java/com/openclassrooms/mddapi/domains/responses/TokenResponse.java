package com.openclassrooms.mddapi.domains.responses;

public class TokenResponse {
    private String token;

    public TokenResponse(String token) {
        this.token = token;
    }

    // Getter
    public String getToken() {
        return token;
    }

    // Setter
    public void setToken(String token) {
        this.token = token;
    }
}
