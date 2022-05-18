package com.msauthentication.service.impl;

import com.msauthentication.dto.request.AccessValidationRequestDto;
import com.msauthentication.dto.request.CreateUserRequestDto;
import com.msauthentication.dto.request.JwtRequestDto;
import com.msauthentication.dto.response.AccessValidationResponseDto;
import com.msauthentication.dto.response.JwtResponseDto;
import com.msauthentication.entity.Role;
import com.msauthentication.entity.User;
import com.msauthentication.enums.UserRoles;
import com.msauthentication.exception.CommonAPIException;
import com.msauthentication.security.JwtTokenProvider;
import com.msauthentication.service.AuthenticationService;
import com.msauthentication.service.RoleService;
import com.msauthentication.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.msauthentication.exception.CommonAPIException.throwOnExistence;
import static com.msauthentication.security.JwtTokenProvider.BEARER;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public AuthenticationServiceImpl(UserService userService,
                                     AuthenticationManager authenticationManager,
                                     JwtTokenProvider jwtTokenProvider,
                                     PasswordEncoder passwordEncoder,
                                     RoleService roleService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }


    @Override
    public void signUp(CreateUserRequestDto request) {
        throwOnExistence(
                userService.validateUsername(request.getUsername()), request
        );
        Role role = roleService.findByName(request.getRole().name());
        if (Objects.isNull(role)) throw new CommonAPIException(
                HttpStatus.BAD_REQUEST, String.format("Role %s does not exists", request.getRole())
        );
        User user = User.builder()
                .setCreatedAt(LocalDateTime.now())
                .setName(request.getName())
                .setSurname(request.getSurname())
                .setUsername(request.getUsername())
                .setPassword(passwordEncoder.encode(request.getPassword()))
                .setRoles(Collections.singleton(role))
                .build();
        userService.create(user);
    }

    @Override
    public JwtResponseDto signIn(JwtRequestDto request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return new JwtResponseDto(token);
    }

    @Override
    public boolean validateRoleIfCourier(boolean isAdmin, UserRoles role) {
        return (isAdmin && role.equals(UserRoles.COURIER)) || role.equals(UserRoles.USER);
    }

    @Override
    public AccessValidationResponseDto validateJwtToken(AccessValidationRequestDto requestDto, String bearer) {
        Set<String> roles = requestDto.getRoles().stream()
                .map(Enum::name)
                .collect(Collectors.toSet());
        if (bearer.startsWith(BEARER) && jwtTokenProvider.validateToken(bearer.substring(7))) {
            String username = jwtTokenProvider.getUsernameFromJWT(bearer.substring(7));

            Set<Role> userRoles = userService.getByUsername(username)
                    .map(User::getRoles)
                    .orElseGet(Collections::emptySet);

            Set<UserRoles> validRoles = userRoles.stream()
                    .filter(role -> roles.contains(role.getName()))
                    .map(role -> UserRoles.valueOf(role.getName()))
                    .collect(Collectors.toSet());

            if (!validRoles.isEmpty()) {
                return new AccessValidationResponseDto()
                        .setHasAccess(true)
                        .setUsername(username)
                        .setRoles(validRoles);
            }
        }
        return new AccessValidationResponseDto().setHasAccess(false);
    }
}
