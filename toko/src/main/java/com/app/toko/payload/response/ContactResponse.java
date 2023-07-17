package com.app.toko.payload.response;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ContactResponse {
    private UUID id;
    private String telephone;

    private String city;

    private String district;

    private String ward;

    private String line;
}
