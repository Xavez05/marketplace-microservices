package com.milicom.order_service.integration;

import com.milicom.order_service.dto.CartItemDTO;
import com.milicom.order_service.dto.OrderItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CartClient {

    private final RestTemplate restTemplate;

    @Value("${services.cart-service.base-url}")
    private String cartServiceBaseUrl;

    public List<CartItemDTO> getCartItems(String token) {
        String url = cartServiceBaseUrl + "/api/cart";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<CartItemDTO[]> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                CartItemDTO[].class
        );

        return Arrays.asList(response.getBody());
    }

    public void clearCart(String token) {
        String url = cartServiceBaseUrl + "/api/cart/clear";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                requestEntity,
                Void.class
        );
    }
}
