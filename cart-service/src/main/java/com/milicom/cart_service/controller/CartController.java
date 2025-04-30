package com.milicom.cart_service.controller;

import com.milicom.cart_service.dto.CartItemDTO;
import com.milicom.cart_service.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.cart.base-path}")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    private Long extractUserId(Jwt jwt) {
        return Long.parseLong(jwt.getClaimAsString("user_id"));
    }

    @GetMapping
    public ResponseEntity<List<CartItemDTO>> getCartItems(@AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(cartService.getCartItems(extractUserId(jwt)));
    }

    @PostMapping
    public ResponseEntity<CartItemDTO> addCartItem(@AuthenticationPrincipal Jwt jwt, @RequestBody CartItemDTO cartItemDTO) {
        return ResponseEntity.ok(cartService.addCartItem(extractUserId(jwt), cartItemDTO));
    }

    @PutMapping("/{cartItemId}")
    public ResponseEntity<CartItemDTO> updateCartItem(@AuthenticationPrincipal Jwt jwt, @PathVariable Long cartItemId, @RequestBody CartItemDTO cartItemDTO) {
        return ResponseEntity.ok(cartService.updateCartItem(extractUserId(jwt), cartItemId, cartItemDTO));
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Void> deleteCartItem(@AuthenticationPrincipal Jwt jwt, @PathVariable Long cartItemId) {
        cartService.deleteCartItem(extractUserId(jwt), cartItemId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCart(@AuthenticationPrincipal Jwt jwt) {
        cartService.clearCart(extractUserId(jwt));
        return ResponseEntity.noContent().build();
    }
}
