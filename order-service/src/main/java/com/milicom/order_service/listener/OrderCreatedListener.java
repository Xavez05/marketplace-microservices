package com.milicom.order_service.listener;

import com.milicom.order_service.dto.CartItemDTO;
import com.milicom.order_service.entity.Order;
import com.milicom.order_service.entity.OrderItem;
import com.milicom.order_service.event.OrderCreatedEvent;
import com.milicom.order_service.integration.CartClient;
import com.milicom.order_service.mapper.OrderItemMapper;
import com.milicom.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderCreatedListener {

    private final CartClient cartClient;
    private final OrderRepository orderRepository;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleOrderCreated(OrderCreatedEvent event) {
        log.info("Order created, now fetching cart items for userId={}", event.getUserId());


        String token = event.getToken();;

        List<CartItemDTO> cartItems = cartClient.getCartItems(token);

        Order order = orderRepository.findById(event.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        List<OrderItem> orderItems = cartItems.stream()
                .map(dto -> OrderItemMapper.fromCartItemDTO(dto, order))
                .collect(Collectors.toList());

        order.setItems(orderItems);
        orderRepository.save(order);

        cartClient.clearCart(token);

        log.info("Order items added and cart cleared for userId={}", event.getUserId());
    }
}
