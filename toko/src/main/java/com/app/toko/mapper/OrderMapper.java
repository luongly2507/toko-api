package com.app.toko.mapper;

import com.app.toko.entity.Order;
import com.app.toko.entity.OrderDetail;
import com.app.toko.payload.request.CreateOrderDetailRequest;
import com.app.toko.payload.request.CreateOrderRequest;
import com.app.toko.payload.response.OrderResponse;

public interface OrderMapper {
    public OrderResponse toOrderResponse(Order order);

    public Order toOrder(CreateOrderRequest createOrderRequest);

    public OrderDetail toOrderDetails(CreateOrderDetailRequest request);
}
