package com.msauthentication.dto.response;

import java.io.Serializable;

public class JwtResponseDto implements Serializable {

    private String jwtToken;

    public JwtResponseDto(String jwtToken) {
        this.jwtToken = jwtToken;
    }


    public String getJwtToken() {
        return jwtToken;
    }

    public JwtResponseDto setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
        return this;
    }

}
