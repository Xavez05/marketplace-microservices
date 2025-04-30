package com.milicom.order_service.mapper;

import com.milicom.order_service.dto.CartItemDTO;
import com.milicom.order_service.dto.OrderItemDTO;
import com.milicom.order_service.entity.Order;
import com.milicom.order_service.entity.OrderItem;

public class OrderItemMapper {

    public static OrderItemDTO toDTO(OrderItem entity) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setProductId(entity.getProductId());
        dto.setQuantity(entity.getQuantity());
        return dto;
    }

    public static OrderItem toEntity(OrderItemDTO dto) {
        OrderItem entity = new OrderItem();
        entity.setProductId(dto.getProductId());
        entity.setQuantity(dto.getQuantity());
        return entity;
    }

    public static OrderItem fromCartItemDTO(CartItemDTO dto, Order order) {
        OrderItem item = new OrderItem();
        item.setProductId(dto.getProductId());
        item.setQuantity(dto.getQuantity());
        item.setOrder(order);
        return item;
    }
}
