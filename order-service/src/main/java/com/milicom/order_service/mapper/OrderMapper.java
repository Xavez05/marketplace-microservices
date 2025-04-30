package com.milicom.order_service.mapper;

import com.milicom.order_service.dto.OrderDTO;
import com.milicom.order_service.dto.OrderItemDTO;
import com.milicom.order_service.entity.Order;

import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setOrderNumber(order.getOrderNumber());
        dto.setShippingAddress(order.getShippingAddress());
        dto.setOrderDate(order.getOrderDate());
        dto.setItems(order.getItems().stream()
                .map(OrderItemMapper::toDTO)
                .collect(Collectors.toList())
        );
        return dto;
    }
}
