package com.app.toko.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.toko.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByPhone(String phone);
}
