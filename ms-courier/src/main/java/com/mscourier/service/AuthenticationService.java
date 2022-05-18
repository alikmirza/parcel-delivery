package com.mscourier.service;

import com.mscourier.dto.request.SignUpRequestDto;

public interface AuthenticationService {
    void signUpCourier(SignUpRequestDto request, String bearerToken);
}
