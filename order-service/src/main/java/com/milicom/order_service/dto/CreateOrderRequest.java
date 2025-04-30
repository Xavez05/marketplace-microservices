package com.milicom.order_service.dto;

import lombok.Data;

@Data
public class CreateOrderRequest {
    private String shippingAddress;
}
