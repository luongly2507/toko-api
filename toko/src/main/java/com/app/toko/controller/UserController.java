package com.app.toko.controller;

import java.util.UUID;

import com.app.toko.entity.User;
import com.app.toko.payload.request.UpdateUserInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.app.toko.payload.response.UserResponse;
import com.app.toko.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    //@PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserDetail(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('user:delete')")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasAuthority('user:update')")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUserInfo(@PathVariable UUID id,@RequestBody UpdateUserInfoRequest userInfoRequest)
    {
        userService.updateUserInfo(id , userInfoRequest);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/phone/{phone}")
    public ResponseEntity<UserResponse> updateUserPassword(@PathVariable String phone , @RequestParam String password)
    {
        userService.updateUserPassword(phone , password);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("")
    public ResponseEntity<UserResponse> isExistUserByPhone(@RequestParam String phone)
    {
        userService.getUserByPhone(phone);
        return ResponseEntity.noContent().build();
    }
}
