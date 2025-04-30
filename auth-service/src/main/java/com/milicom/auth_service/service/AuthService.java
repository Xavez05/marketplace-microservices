package com.milicom.auth_service.service;


import com.milicom.auth_service.dto.AuthRequest;
import com.milicom.auth_service.dto.AuthResponse;
import com.milicom.auth_service.dto.RegisterRequest;

public interface AuthService {
    void register(RegisterRequest request);
    AuthResponse login(AuthRequest request);
}