package com.app.toko.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Album")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Album {

    @EmbeddedId
    private AlbumId id;

    @ManyToOne
    @MapsId("book_id")
    @JoinColumn(name = "book_id")
    private Book book;
 
    @Column(columnDefinition = "BOOLEAN NOT NULL DEFAULT FALSE")
    private boolean isPresentation;
   
}
