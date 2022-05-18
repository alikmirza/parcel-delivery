package com.msdelivery.dto.response;

import java.io.Serializable;
import java.util.Set;

public class ValidateJwtTokenResponseDto implements Serializable {

    private boolean isValid;
    private String username;
    private Set<String> roles;

    public boolean getIsValid() {
        return isValid;
    }

    public ValidateJwtTokenResponseDto setIsValid(boolean valid) {
        isValid = valid;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ValidateJwtTokenResponseDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public ValidateJwtTokenResponseDto setRoles(Set<String> roles) {
        this.roles = roles;
        return this;
    }
}
