package com.milicom.cart_service.mapper;

import com.milicom.cart_service.dto.CartItemDTO;
import com.milicom.cart_service.entity.CartItem;

public class CartItemMapper {

    public static CartItemDTO toDTO(CartItem entity) {
        CartItemDTO dto = new CartItemDTO();
        dto.setId(entity.getId());
        dto.setProductId(entity.getProductId());
        dto.setQuantity(entity.getQuantity());
        return dto;
    }

    public static CartItem toEntity(CartItemDTO dto, Long userId) {
        CartItem entity = new CartItem();
        entity.setUserId(userId);
        entity.setProductId(dto.getProductId());
        entity.setQuantity(dto.getQuantity());
        return entity;
    }

    public static void updateEntityFromDTO(CartItem entity, CartItemDTO dto) {
        entity.setQuantity(dto.getQuantity());
    }
}
