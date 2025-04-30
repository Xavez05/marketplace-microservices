package com.milicom.user_service.service.impl;

import com.milicom.user_service.dto.UserDTO;
import com.milicom.user_service.dto.UserProfileDTO;
import com.milicom.user_service.dto.UserProfileRequest;
import com.milicom.user_service.dto.UserProfileResponse;
import com.milicom.user_service.entity.UserProfile;
import com.milicom.user_service.event.FetchUserEvent;
import com.milicom.user_service.repository.UserProfileRepository;
import com.milicom.user_service.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public UserProfileResponse getProfile(Long userId) {
        UserProfile profile = repository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        return new UserProfileResponse(profile.getAddress(), profile.getPhoneNumber());
    }

    @Override
    public void updateProfile(Long userId, UserProfileRequest request) {
        UserProfile profile = repository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        profile.setAddress(request.getAddress());
        profile.setPhoneNumber(request.getPhoneNumber());
        profile.setUpdatedAt(java.time.LocalDateTime.now());
        repository.save(profile);
    }
    @Override
    public void createProfile(Long userId) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(userId);
        userProfile.setAddress("Dirección pendiente");
        userProfile.setPhoneNumber(null); // Puedes dejar null al principio

        repository.save(userProfile);
    }



    @Override
    public UserProfileDTO getFullProfile(Long userId) {
        FetchUserEvent fetchEvent = new FetchUserEvent(this, userId);
        eventPublisher.publishEvent(fetchEvent);

        UserDTO user = fetchEvent.getUser();
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }

        UserProfile profile = repository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil no encontrado"));

        return new UserProfileDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getDateOfBirth(),
                profile.getAddress(),
                profile.getPhoneNumber()
        );
    }

}
