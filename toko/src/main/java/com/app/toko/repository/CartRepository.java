package com.app.toko.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.toko.entity.Cart;
import com.app.toko.entity.CartId;

public interface CartRepository extends JpaRepository<Cart, CartId> {

    List<Cart> findByUserId(UUID userId);

}
