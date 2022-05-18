package com.msauthentication.dto.request;


import com.msauthentication.constraints.UserRolesSubSet;
import com.msauthentication.entity.User;
import com.msauthentication.enums.UserRoles;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class CreateUserRequestDto implements Serializable {

    @Email(message = "Username should be email")
    @NotEmpty(message = "Username cannot be emty")
    private String username;
    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 10, max = 16)
    private String password;
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotEmpty(message = "Surname cannot be empty")
    private String surname;
    @UserRolesSubSet(anyOf = {UserRoles.USER, UserRoles.COURIER})
    private UserRoles role;


    public String getUsername() {
        return username;
    }

    public CreateUserRequestDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CreateUserRequestDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public CreateUserRequestDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public CreateUserRequestDto setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public UserRoles getRole() {
        return role;
    }

    public CreateUserRequestDto setRole(UserRoles role) {
        this.role = role;
        return this;
    }

    public static User mapper(CreateUserRequestDto request) {
        return new User()
                .setName(request.getName())
                .setSurname(request.getSurname())
                .setUsername(request.getUsername())
                .setPassword(request.getPassword());

    }
}
