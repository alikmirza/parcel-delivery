package com.msauthentication.dto.request;

import com.msauthentication.enums.UserRoles;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

public class AccessValidationRequestDto implements Serializable {

    @NotNull(message = "Roles cannot be null")
    private Set<UserRoles> roles;

    public Set<UserRoles> getRoles() {
        return roles;
    }

    public AccessValidationRequestDto setRoles(Set<UserRoles> roles) {
        this.roles = roles;
        return this;
    }
}
