package com.mscourier.service.impl;

import com.mscourier.config.ApplicationConfig;
import com.mscourier.dto.request.SignUpRequestDto;
import com.mscourier.service.AuthenticationService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final RestTemplate restTemplate;

    public AuthenticationServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void signUpCourier(SignUpRequestDto request, String bearerToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", bearerToken);
        HttpEntity<SignUpRequestDto> entity = new HttpEntity<>(request, headers);
        restTemplate.exchange(ApplicationConfig.getMsAuthSignUpUrl(), HttpMethod.POST, entity, String.class);

    }
}
