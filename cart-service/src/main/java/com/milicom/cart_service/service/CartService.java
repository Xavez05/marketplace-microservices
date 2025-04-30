package com.milicom.cart_service.service;

import com.milicom.cart_service.dto.CartItemDTO;

import java.util.List;

public interface CartService {

    List<CartItemDTO> getCartItems(Long userId);

    CartItemDTO addCartItem(Long userId, CartItemDTO cartItemDTO);

    CartItemDTO updateCartItem(Long userId, Long cartItemId, CartItemDTO cartItemDTO);

    void deleteCartItem(Long userId, Long cartItemId);

    void clearCart(Long userId);
}
