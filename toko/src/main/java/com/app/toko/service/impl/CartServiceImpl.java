package com.app.toko.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.toko.entity.Cart;
import com.app.toko.entity.CartId;
import com.app.toko.mapper.CartMapper;
import com.app.toko.payload.request.UpdateCartItemRequest;
import com.app.toko.payload.response.CartResponse;
import com.app.toko.repository.CartRepository;
import com.app.toko.service.CartService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartMapper cartMapper;

    @Override
    public void updateCartItem(UUID userId, UpdateCartItemRequest updateCartItemRequest) {
        CartId cartId = CartId
                .builder()
                .bookId(updateCartItemRequest.getBookId())
                .userId(userId)
                .build();
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if (cartOptional.isEmpty()) {
            Cart cart = Cart.builder().id(cartId).quantity(updateCartItemRequest.getQuantity()).build();
            cartRepository.save(cart);
        } else {
            Cart cart = cartOptional.get();
            cartMapper.updateCart(cart, updateCartItemRequest);
            if (cart.getQuantity() <= 0) {
                cartRepository.delete(cart);
            } else {
                if (cart.getQuantity() > cart.getBook().getQuantity()) {
                    cart.setQuantity(cart.getBook().getQuantity());
                }
                cartRepository.save(cart);
            }
        }

    }

    @Override
    public void deleteCartItem(UUID userId, UUID bookId) {
        cartRepository.deleteById(CartId
                .builder()
                .bookId(bookId)
                .userId(userId)
                .build());
    }

    @Override
    public List<CartResponse> getAllCartItemsByUserId(UUID userId) {
        return cartRepository.findByUserId(userId).stream().map(
                cart -> cartMapper.toCartResponse(cart)).toList();
    }

}
