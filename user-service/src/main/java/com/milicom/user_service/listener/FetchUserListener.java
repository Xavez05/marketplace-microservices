package com.milicom.user_service.listener;

import com.milicom.user_service.dto.UserDTO;
import com.milicom.user_service.event.FetchUserEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class FetchUserListener {

    private final RestTemplate restTemplate;

    @Value("${auth.system-token}")
    private String systemToken;

    private static final String USER_URL = "http://auth-service:8081/api/users/";

    @EventListener
    public void onUserFetch(FetchUserEvent event) {
        String url = USER_URL + event.getUserId();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + systemToken);
        System.out.println("[DEBUG] Authorization header enviado: Bearer " + systemToken);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<UserDTO> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    UserDTO.class
            );

            event.setUser(response.getBody());
            log.info("User fetched for id={}", event.getUserId());
        } catch (Exception e) {
            log.error("Failed to fetch user {} from auth-service", event.getUserId(), e);
        }
    }
}
