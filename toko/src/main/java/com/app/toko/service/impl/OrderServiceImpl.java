package com.app.toko.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.toko.entity.Order;
import com.app.toko.entity.OrderDetail;
import com.app.toko.entity.OrderDetailId;
import com.app.toko.mapper.OrderMapper;
import com.app.toko.payload.request.CreateOrderRequest;
import com.app.toko.payload.response.OrderResponse;
import com.app.toko.repository.OrderDetailRepository;
import com.app.toko.repository.OrderRepository;
import com.app.toko.repository.UserRepository;
import com.app.toko.service.OrderService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<OrderResponse> getAllOrdersByUserId(UUID userId) {
        return orderRepository.findByUserId(userId).stream().map(order -> orderMapper.toOrderResponse(order)).toList();
    }

    @Override
    public OrderResponse createOrder(UUID userId, CreateOrderRequest createOrderRequest) {
        Order order = orderMapper.toOrder(createOrderRequest);
        order.setUser(userRepository.findById(userId).orElseThrow());
        Order saveOrder = orderRepository.save(order);
        List<OrderDetail> orderDetails = createOrderRequest.getOrderDetails().stream().map(
                orderDetailRequest -> {
                    OrderDetail orderDetail = orderMapper.toOrderDetails(orderDetailRequest);
                    orderDetail.setId(OrderDetailId.builder().bookId(orderDetailRequest.getBookId())
                            .orderId(saveOrder.getId()).build());
                    return orderDetail;
                }).toList();
        orderDetailRepository.saveAll(orderDetails);
        saveOrder.setOrderDetails(orderDetails.stream().collect(Collectors.toSet()));

        return orderMapper.toOrderResponse(orderRepository.save(saveOrder));
    }

}
