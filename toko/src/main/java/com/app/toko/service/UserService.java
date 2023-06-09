package com.app.toko.service;

import java.util.UUID;

import com.app.toko.payload.response.UserResponse;

public interface UserService {
    public UserResponse getUserById(UUID id);

    public void deleteUser(UUID id);
}
