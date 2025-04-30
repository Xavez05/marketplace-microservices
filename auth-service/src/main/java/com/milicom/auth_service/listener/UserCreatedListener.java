package com.milicom.auth_service.listener;

import com.milicom.auth_service.event.UserCreatedEvent;
import com.milicom.auth_service.integration.UserProfileIntegrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.event.TransactionPhase;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserCreatedListener {

    private final UserProfileIntegrationService userProfileIntegrationService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleUserCreated(UserCreatedEvent event) {
        log.info("User created, now creating profile for userId={}", event.getUserId());
        userProfileIntegrationService.createUserProfile(event.getUserId());
    }
}
