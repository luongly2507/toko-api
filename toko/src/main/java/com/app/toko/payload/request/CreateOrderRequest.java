package com.app.toko.payload.request;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateOrderRequest {
    private LocalDateTime purchaseDate;
    private UUID contactId;
    private List<CreateOrderDetailRequest> orderDetails;
}
