package com.app.toko.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUserInfoRequest {
    private String firstname;
    private String lastname;
    private String email;

}
