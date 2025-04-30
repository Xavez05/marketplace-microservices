package com.milicom.order_service.controller;

import com.milicom.order_service.dto.CreateOrderRequest;
import com.milicom.order_service.dto.OrderDTO;
import com.milicom.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.orders.base-path}")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private Long extractUserId(Jwt jwt) {
        return Long.parseLong(jwt.getClaimAsString("user_id"));
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(
            @AuthenticationPrincipal Jwt jwt,
            @RequestHeader("Authorization") String authorization,
            @RequestBody CreateOrderRequest request) {

        String token = authorization.replace("Bearer ", "");
        return ResponseEntity.ok(orderService.createOrder(
                Long.parseLong(jwt.getClaimAsString("user_id")),
                request,
                token
        ));
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getUserOrders(@AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(orderService.getUserOrders(extractUserId(jwt)));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@AuthenticationPrincipal Jwt jwt, @PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrderById(extractUserId(jwt), orderId));
    }
}
