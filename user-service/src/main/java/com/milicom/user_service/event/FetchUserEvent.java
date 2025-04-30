package com.milicom.user_service.event;

import com.milicom.user_service.dto.UserDTO;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class FetchUserEvent extends ApplicationEvent {
    private final Long userId;
    private UserDTO user;

    public FetchUserEvent(Object source, Long userId) {
        super(source);
        this.userId = userId;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}

