package com.app.toko.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.toko.entity.OrderDetail;
import com.app.toko.entity.OrderDetailId;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {

    Optional<OrderDetail> findByOrderId(UUID id);

}
