package com.milicom.order_service.service;

import com.milicom.order_service.dto.CreateOrderRequest;
import com.milicom.order_service.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO createOrder(Long userId, CreateOrderRequest request, String token);
    List<OrderDTO> getUserOrders(Long userId);
    OrderDTO getOrderById(Long userId, Long orderId);
}