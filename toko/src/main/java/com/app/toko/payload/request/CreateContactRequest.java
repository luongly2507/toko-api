package com.app.toko.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateContactRequest {

    private String telephone;

    private String receiver;

    private String city;

    private String district;

    private String ward;

    private String line;

    private Boolean isDefault;
}
