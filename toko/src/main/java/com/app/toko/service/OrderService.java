package com.app.toko.service;

import java.util.List;
import java.util.UUID;

import com.app.toko.payload.request.CreateOrderRequest;
import com.app.toko.payload.response.OrderResponse;

public interface OrderService {
    public List<OrderResponse> getAllOrdersByUserId(UUID userId);

    public OrderResponse createOrder(UUID userId, CreateOrderRequest createOrderRequest);

}
