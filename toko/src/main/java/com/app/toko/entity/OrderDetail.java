package com.app.toko.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Order_Detail")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDetail {
    @EmbeddedId
    private OrderDetailId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("book_id")
    @JoinColumn(name = "book_id", nullable = false)
    @JsonIgnore
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("order_id")
    @JoinColumn(name = "order_id", nullable = false)
    @JsonIgnore
    private Order order;
    private int quantity;

    private double price;
}
