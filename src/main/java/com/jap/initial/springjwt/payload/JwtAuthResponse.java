/* by JAPBuilder */
package com.jap.initial.springjwt.payload;

public class JwtAuthResponse {
    private String token;
    private String tokenType = "Bearer";

    public JwtAuthResponse() {
    }

    public JwtAuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
    public String getTokenType() {
        return tokenType;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
