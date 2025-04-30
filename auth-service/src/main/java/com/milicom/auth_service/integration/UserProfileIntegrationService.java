package com.milicom.auth_service.integration;


import com.milicom.auth_service.dto.CreateProfileRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UserProfileIntegrationService {

    private final RestTemplate restTemplate;

    @Value("${auth.jwt.system-token}")
    private String systemToken;

    private static final String USER_SERVICE_URL = "http://user-service:8082/api/users/profile";

    public void createUserProfile(Long userId) {
        CreateProfileRequest request = new CreateProfileRequest(userId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + systemToken);

        System.out.println("[DEBUG] Authorization header enviado: Bearer " + systemToken);

        HttpEntity<CreateProfileRequest> entity = new HttpEntity<>(request, headers);

        restTemplate.exchange(USER_SERVICE_URL, HttpMethod.POST, entity, Void.class);
    }
}
