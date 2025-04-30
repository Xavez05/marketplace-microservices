package com.milicom.user_service.controller;

import com.milicom.user_service.dto.CreateProfileRequest;
import com.milicom.user_service.dto.UserProfileDTO;
import com.milicom.user_service.dto.UserProfileRequest;
import com.milicom.user_service.dto.UserProfileResponse;
import com.milicom.user_service.service.UserProfileService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/users/profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;
    @Value("${auth.system-token}")
    private String systemToken;

    @PostMapping
    public void createUserProfile(@RequestBody CreateProfileRequest request, HttpServletRequest servletRequest) {
        String authorizationHeader = servletRequest.getHeader("Authorization");
        System.out.println("[DEBUG] Authorization header enviado: Bearer " + authorizationHeader);
        if (authorizationHeader == null || !authorizationHeader.equals("Bearer " + systemToken)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Unauthorized access");
        }

        userProfileService.createProfile(request.getUserId());
    }

    @GetMapping("/{userId}")
    public UserProfileDTO getProfile(@PathVariable Long userId) {
        return userProfileService.getFullProfile(userId);
    }

    @PutMapping("/{userId}")
    public void updateProfile(@PathVariable Long userId, @RequestBody UserProfileRequest request) {
        userProfileService.updateProfile(userId, request);
    }
}
