package com.msauthentication.dto.request;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class JwtRequestDto implements Serializable {

    @NotEmpty(message = "Username cannot be empty")
    private String username;
    @NotEmpty(message = "Password cannot be empty")
    private String password;


    public String getUsername() {
        return username;
    }

    public JwtRequestDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public JwtRequestDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
