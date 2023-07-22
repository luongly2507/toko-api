package com.app.toko.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateContactRequest {

    private String telephone;

    private String city;

    private String district;

    private String ward;

    private String line;
}
