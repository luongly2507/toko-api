package com.app.toko.payload.response;

import java.math.BigDecimal;
import java.time.LocalDate;
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
public class BookResponse {
    private UUID id;
    private String title;
    private String language;
    private String description;
    private String edition;
    private BigDecimal price;
    private int quantity;
    private LocalDate publishcationDate;
    private String authors;
    private CategoryResponse category;
    private String publisher;
    private Set<AlbumResponse> albums;
}
