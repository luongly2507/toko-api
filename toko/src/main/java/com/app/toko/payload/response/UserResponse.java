package com.app.toko.payload.response;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponse {
    private UUID id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
}
