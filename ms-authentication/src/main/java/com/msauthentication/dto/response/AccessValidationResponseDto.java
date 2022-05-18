package com.msauthentication.dto.response;

import com.msauthentication.enums.UserRoles;

import java.io.Serializable;
import java.util.Set;

public class AccessValidationResponseDto implements Serializable {

    private boolean hasAccess;
    private String username;
    private Set<UserRoles> roles;




    public boolean isHasAccess() {
        return hasAccess;
    }

    public AccessValidationResponseDto setHasAccess(boolean hasAccess) {
        this.hasAccess = hasAccess;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public AccessValidationResponseDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public Set<UserRoles> getRoles() {
        return roles;
    }

    public AccessValidationResponseDto setRoles(Set<UserRoles> roles) {
        this.roles = roles;
        return this;
    }
}
