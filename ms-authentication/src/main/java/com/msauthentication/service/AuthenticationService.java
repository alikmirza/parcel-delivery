package com.msauthentication.service;

import com.msauthentication.dto.request.AccessValidationRequestDto;
import com.msauthentication.dto.request.CreateUserRequestDto;
import com.msauthentication.dto.request.JwtRequestDto;
import com.msauthentication.dto.response.AccessValidationResponseDto;
import com.msauthentication.dto.response.JwtResponseDto;
import com.msauthentication.enums.UserRoles;


public interface AuthenticationService {

    void signUp(CreateUserRequestDto request);
    JwtResponseDto signIn(JwtRequestDto request);
    boolean validateRoleIfCourier(boolean isAdmin, UserRoles role);
    AccessValidationResponseDto validateJwtToken(AccessValidationRequestDto requestDto, String bearer);

}
