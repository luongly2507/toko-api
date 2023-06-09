package com.app.toko.mapper.impl;

import org.springframework.stereotype.Component;

import com.app.toko.entity.User;
import com.app.toko.mapper.UserMapper;
import com.app.toko.payload.response.UserResponse;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .email(user.getEmail())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .id(user.getId())
                .phone(user.getPhone())
                .build();
    }

}
