package com.milicom.order_service.dto;

import lombok.Data;

@Data
public class CartItemDTO {
    private Long productId;
    private Integer quantity;
}
