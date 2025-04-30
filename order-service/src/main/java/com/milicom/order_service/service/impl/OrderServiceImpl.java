package com.milicom.order_service.service.impl;

import com.milicom.order_service.dto.CreateOrderRequest;
import com.milicom.order_service.dto.OrderDTO;
import com.milicom.order_service.entity.Order;
import com.milicom.order_service.event.OrderCreatedEvent;
import com.milicom.order_service.mapper.OrderMapper;
import com.milicom.order_service.repository.OrderRepository;
import com.milicom.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public OrderDTO createOrder(Long userId, CreateOrderRequest request, String token) {
        Order order = new Order();
        order.setUserId(userId);
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setShippingAddress(request.getShippingAddress());
        order.setOrderDate(LocalDateTime.now());
        order.setItems(List.of());

        Order savedOrder = orderRepository.save(order);

        eventPublisher.publishEvent(new OrderCreatedEvent(this, savedOrder.getId(), userId, token));

        return OrderMapper.toDTO(savedOrder);
    }

    @Override
    public List<OrderDTO> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId)
                .stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(Long userId, Long orderId) {
        Order order = orderRepository.findById(orderId)
                .filter(o -> o.getUserId().equals(userId))
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return OrderMapper.toDTO(order);
    }
}
