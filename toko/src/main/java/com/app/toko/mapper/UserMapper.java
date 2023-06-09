package com.app.toko.mapper;

import com.app.toko.entity.User;
import com.app.toko.payload.response.UserResponse;

public interface UserMapper {
    public UserResponse toUserResponse(User User);

}