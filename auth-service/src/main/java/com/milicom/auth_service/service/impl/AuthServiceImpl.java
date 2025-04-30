package com.milicom.auth_service.service.impl;

import com.milicom.auth_service.dto.AuthRequest;
import com.milicom.auth_service.dto.AuthResponse;
import com.milicom.auth_service.dto.RegisterRequest;
import com.milicom.auth_service.entity.User;
import com.milicom.auth_service.event.UserCreatedEvent;
import com.milicom.auth_service.integration.UserProfileIntegrationService;
import com.milicom.auth_service.repository.UserRepository;
import com.milicom.auth_service.service.AuthService;
import com.milicom.auth_service.util.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserProfileIntegrationService userProfileIntegrationService;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public void register(RegisterRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setDateOfBirth(request.getDateOfBirth());
        userRepository.save(user);
        eventPublisher.publishEvent(new UserCreatedEvent(this, user.getId()));
    }

    @Override
    @Transactional
    public AuthResponse login(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtUtil.generateToken(user);

        return new AuthResponse(token);
    }
}
