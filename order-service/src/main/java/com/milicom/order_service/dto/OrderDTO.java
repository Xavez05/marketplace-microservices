package com.milicom.order_service.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private String orderNumber;
    private String shippingAddress;
    private LocalDateTime orderDate;
    private List<OrderItemDTO> items;
}
