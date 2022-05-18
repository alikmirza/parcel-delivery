package com.msdelivery.service.impl;

import com.msdelivery.config.ApplicationConfig;
import com.msdelivery.dto.request.ValidateJwtTokenRequestDto;
import com.msdelivery.dto.response.ValidateJwtTokenResponseDto;
import com.msdelivery.security.AuthUser;
import com.msdelivery.service.AuthService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

import static java.util.Objects.nonNull;

@Service
public class AuthServiceImpl implements AuthService {

    private final RestTemplate restTemplate;
    private final AuthUser authUser;

    public AuthServiceImpl(RestTemplate restTemplate, AuthUser authUser) {
        this.restTemplate = restTemplate;
        this.authUser = authUser;
    }

    @Override
    public boolean checkAccess(String bearerToken, String... role) {
        ValidateJwtTokenRequestDto validateJwtTokenRequestDto = new ValidateJwtTokenRequestDto().setRoles(Set.of(role));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", bearerToken);
        HttpEntity<ValidateJwtTokenRequestDto> entity = new HttpEntity<>(validateJwtTokenRequestDto, headers);
        ValidateJwtTokenResponseDto jwtTokenResponseDto = restTemplate.exchange(
                ApplicationConfig.getMsAuthValidateUrl(),
                HttpMethod.POST,
                entity,
                ValidateJwtTokenResponseDto.class).getBody();
        if (nonNull(jwtTokenResponseDto) && jwtTokenResponseDto.getIsValid()) {
            authUser.setUsername(jwtTokenResponseDto.getUsername());
            authUser.setRoles(jwtTokenResponseDto.getRoles());
            return true;
        }
        return false;
    }
}
