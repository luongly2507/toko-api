package com.app.toko.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.toko.exception.ResourceNotFoundException;
import com.app.toko.mapper.UserMapper;
import com.app.toko.payload.response.UserResponse;
import com.app.toko.repository.UserRepository;
import com.app.toko.service.UserService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserResponse getUserById(UUID id) {
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found !")));
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<UserResponse> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(
                user -> userMapper.toUserResponse(user));
    }

}
