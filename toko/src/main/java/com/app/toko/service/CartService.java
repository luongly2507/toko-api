package com.app.toko.service;

import java.util.List;
import java.util.UUID;

import com.app.toko.payload.request.UpdateCartItemRequest;
import com.app.toko.payload.response.CartResponse;

public interface CartService {

    List<CartResponse> getAllCartItemsByUserId(UUID userId);

    void updateCartItem(UUID userId, UpdateCartItemRequest updateCartItemRequest);

    void deleteCartItem(UUID userId, UUID bookId);
}
