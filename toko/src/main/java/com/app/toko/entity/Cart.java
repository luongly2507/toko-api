package com.app.toko.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "Cart")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Cart {
    @EmbeddedId
    private CartId id;
    private int quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("book_id")
    @JoinColumn(name = "book_id", nullable = false)
    @JsonIgnore
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("user_id")
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;
}
