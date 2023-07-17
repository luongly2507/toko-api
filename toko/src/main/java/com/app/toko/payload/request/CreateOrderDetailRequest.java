package com.app.toko.payload.request;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateOrderDetailRequest {
    private int quantity;
    private double price;
    private UUID bookId;
}
