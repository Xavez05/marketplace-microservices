package com.milicom.order_service.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OrderCreatedEvent extends ApplicationEvent {

    private final Long orderId;
    private final Long userId;
    private final String token;

    public OrderCreatedEvent(Object source, Long orderId, Long userId, String token) {
        super(source);
        this.orderId = orderId;
        this.userId = userId;
        this.token = token;
    }
}