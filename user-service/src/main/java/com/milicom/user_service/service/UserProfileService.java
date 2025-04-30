package com.milicom.user_service.service;


import com.milicom.user_service.dto.UserProfileDTO;
import com.milicom.user_service.dto.UserProfileRequest;
import com.milicom.user_service.dto.UserProfileResponse;

public interface UserProfileService {
    UserProfileResponse getProfile(Long userId);
    void updateProfile(Long userId, UserProfileRequest request);
    void createProfile(Long userId);
    UserProfileDTO getFullProfile(Long userId);
}