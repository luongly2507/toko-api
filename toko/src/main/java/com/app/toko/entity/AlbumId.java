package com.app.toko.entity;

import java.io.Serializable;
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
public class AlbumId implements Serializable {
    @Column(name = "book_id")
    private UUID bookId;

    @Column(name = "image_source", nullable = false)
    private String imageSource;
}
