package com.mscourier.dto.request;

import java.io.Serializable;
import java.util.Set;

public class ValidateJwtTokenRequestDto implements Serializable {

    private Set<String> roles;


    public Set<String> getRoles() {
        return roles;
    }

    public ValidateJwtTokenRequestDto setRoles(Set<String> roles) {
        this.roles = roles;
        return this;
    }
}
