package com.app.toko.mapper.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.toko.entity.Order;
import com.app.toko.entity.OrderDetail;
import com.app.toko.mapper.BookMapper;
import com.app.toko.mapper.ContactMapper;
import com.app.toko.mapper.OrderMapper;
import com.app.toko.payload.request.CreateOrderDetailRequest;
import com.app.toko.payload.request.CreateOrderRequest;
import com.app.toko.payload.response.OrderDetailResponse;
import com.app.toko.payload.response.OrderResponse;
import com.app.toko.repository.BookRepository;
import com.app.toko.repository.ContactRepository;
import com.app.toko.repository.OrderDetailRepository;

@Component
public class OrderMapperImpl implements OrderMapper {
        @Autowired
        private ContactRepository contactRepository;
        @Autowired
        private BookRepository bookRepository;
        @Autowired
        private ContactMapper contactMapper;

        @Autowired
        private OrderDetailRepository orderDetailRepository;
        @Autowired
        private BookMapper bookMapper;

        @Override
        public Order toOrder(CreateOrderRequest createOrderRequest) {
                return Order.builder()
                                .purchaseDate(createOrderRequest.getPurchaseDate())
                                .contact(contactRepository.findById(createOrderRequest.getContactId()).orElseThrow())
                                .totalPrice(createOrderRequest.getOrderDetails().stream()
                                                .mapToDouble(orderDetail -> orderDetail.getPrice()
                                                                * orderDetail.getQuantity())
                                                .sum())
                                .build();
        }

        @Override
        public OrderResponse toOrderResponse(Order order) {
                return OrderResponse.builder()
                                .contact(contactMapper.toContactResponse(order.getContact()))
                                .id(order.getId())
                                .orderDetails(orderDetailRepository.findByOrderId(order.getId()).stream()
                                                .map(orderDetail -> this.toOrderDetailResponse(orderDetail))
                                                .collect(Collectors.toSet()))
                                .purchaseDate(order.getPurchaseDate())
                                .totalPrice(order.getTotalPrice())
                                .build();
        }

        private OrderDetailResponse toOrderDetailResponse(OrderDetail orderDetail) {
                return OrderDetailResponse.builder()
                                .book(bookMapper.toBookResponse(orderDetail.getBook()))
                                .price(orderDetail.getPrice())
                                .quantity(orderDetail.getQuantity())
                                .build();
        }

        @Override
        public OrderDetail toOrderDetails(CreateOrderDetailRequest request) {
                return OrderDetail.builder()
                                .book(bookRepository.findById(request.getBookId()).orElseThrow())
                                .price(request.getPrice())
                                .quantity(request.getQuantity())
                                .build();
        }
}
