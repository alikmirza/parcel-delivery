package com.msdelivery.service;

public interface AuthService {
    boolean checkAccess(String bearerToken, String... role);
}
