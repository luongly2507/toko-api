package com.app.toko.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.toko.payload.response.UserResponse;

public interface UserService {
    public UserResponse getUserById(UUID id);

    public void deleteUser(UUID id);

    public Page<UserResponse> getAllUsers(Pageable pageable);
}
