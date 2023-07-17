package com.app.toko.mapper;

import com.app.toko.entity.Cart;
import com.app.toko.payload.request.UpdateCartItemRequest;
import com.app.toko.payload.response.CartResponse;

public interface CartMapper {
    public CartResponse toCartResponse(Cart cart);

    public void updateCart(Cart cart, UpdateCartItemRequest request);
}
