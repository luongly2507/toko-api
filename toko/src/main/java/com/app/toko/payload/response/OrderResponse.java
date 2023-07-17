package com.app.toko.payload.response;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class OrderResponse {
    private UUID id;
    private LocalDateTime purchaseDate;
    private Double totalPrice;
    private ContactResponse contact;
    private Set<OrderDetailResponse> orderDetails;
}
