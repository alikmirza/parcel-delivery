package com.mscourier.service;

public interface AuthService {
    boolean checkAccess(String bearerToken, String... role);
}
