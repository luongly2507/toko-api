package com.app.toko.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDetailId {
    @Column(name = "book_id")
    private UUID bookId;

    @Column(name = "order_id")
    private UUID orderId;
}
