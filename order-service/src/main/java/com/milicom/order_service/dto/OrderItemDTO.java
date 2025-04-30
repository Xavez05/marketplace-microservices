package com.milicom.order_service.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long productId;
    private Integer quantity;
}
