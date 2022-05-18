package com.msauthentication.controller;

import com.msauthentication.dto.CommonResponseDto;
import com.msauthentication.dto.request.AccessValidationRequestDto;
import com.msauthentication.dto.request.CreateUserRequestDto;
import com.msauthentication.dto.request.JwtRequestDto;
import com.msauthentication.service.impl.AuthenticationServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
@Api(value = "Authentication Services")
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;

    public AuthenticationController(AuthenticationServiceImpl authenticationService) {
        this.authenticationService = authenticationService;
    }

    @ApiOperation(value = "Service for signing up user")
    @PostMapping("/sign-up")
    @PreAuthorize("@authenticationServiceImpl.validateRoleIfCourier(hasRole('ADMIN'), #request.role)")
    public ResponseEntity<CommonResponseDto<?>> signUp(@RequestBody CreateUserRequestDto request) {
        authenticationService.signUp(request);
        return ResponseEntity.ok(new CommonResponseDto<>());
    }

    @ApiOperation(value = "Service for signing in user")
    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody @Valid JwtRequestDto requestDto) {
        return ResponseEntity.ok(authenticationService.signIn(requestDto));
    }

    @ApiOperation(value = "Service for validation auth token")
    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String bearerToken,
                                           @RequestBody @Valid AccessValidationRequestDto requestDto) {
        return ResponseEntity.ok(authenticationService.validateJwtToken(requestDto, bearerToken));
    }
}
