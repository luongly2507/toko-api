package com.app.toko.mapper.impl;

import com.app.toko.payload.request.UpdateUserInfoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.app.toko.entity.User;
import com.app.toko.mapper.UserMapper;
import com.app.toko.payload.response.UserResponse;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {
    public final PasswordEncoder passwordEncoder;

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

    @Override
    public void updateUserInfo(User user, UpdateUserInfoRequest updateUserInfoRequest) {
        user.setFirstname(updateUserInfoRequest.getFirstname());
        user.setLastname(updateUserInfoRequest.getLastname());
        user.setEmail(updateUserInfoRequest.getEmail());
    }

    @Override
    public void updateUserPassword(User user, String pass) {
        user.setPassword(passwordEncoder.encode(pass));
    }

}
