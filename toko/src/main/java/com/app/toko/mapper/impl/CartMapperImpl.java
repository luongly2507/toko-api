package com.app.toko.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.toko.entity.Cart;
import com.app.toko.mapper.BookMapper;
import com.app.toko.mapper.CartMapper;
import com.app.toko.payload.request.UpdateCartItemRequest;
import com.app.toko.payload.response.CartResponse;

@Component
public class CartMapperImpl implements CartMapper {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public CartResponse toCartResponse(Cart cart) {
        return CartResponse.builder()
                .quantity(cart.getQuantity())
                .bookResponse(bookMapper.toBookResponse(cart.getBook()))
                .build();
    }

    @Override
    public void updateCart(Cart cart, UpdateCartItemRequest request) {
        cart.setQuantity(request.getQuantity());
    }

}
