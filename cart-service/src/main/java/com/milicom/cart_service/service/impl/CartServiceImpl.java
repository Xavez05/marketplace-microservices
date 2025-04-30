package com.milicom.cart_service.service.impl;

import com.milicom.cart_service.dto.CartItemDTO;
import com.milicom.cart_service.entity.CartItem;
import com.milicom.cart_service.mapper.CartItemMapper;
import com.milicom.cart_service.repository.CartItemRepository;
import com.milicom.cart_service.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartItemRepository cartItemRepository;

    @Override
    public List<CartItemDTO> getCartItems(Long userId) {
        return cartItemRepository.findByUserId(userId)
                .stream()
                .map(CartItemMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CartItemDTO addCartItem(Long userId, CartItemDTO cartItemDTO) {
        CartItem entity = CartItemMapper.toEntity(cartItemDTO, userId);
        CartItem saved = cartItemRepository.save(entity);
        return CartItemMapper.toDTO(saved);
    }

    @Override
    public CartItemDTO updateCartItem(Long userId, Long cartItemId, CartItemDTO cartItemDTO) {
        CartItem entity = cartItemRepository.findById(cartItemId)
                .filter(item -> item.getUserId().equals(userId))
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        CartItemMapper.updateEntityFromDTO(entity, cartItemDTO);
        CartItem updated = cartItemRepository.save(entity);
        return CartItemMapper.toDTO(updated);
    }

    @Override
    public void deleteCartItem(Long userId, Long cartItemId) {
        CartItem entity = cartItemRepository.findById(cartItemId)
                .filter(item -> item.getUserId().equals(userId))
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        cartItemRepository.delete(entity);
    }

    @Override
    public void clearCart(Long userId) {
        cartItemRepository.deleteByUserId(userId);
    }
}
