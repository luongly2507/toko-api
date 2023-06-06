package com.app.toko.payload.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UpdateBookRequest {
    @NotBlank(message = "Tên sách không được trống!")
    private String title;
    private String subTitle;
    @NotBlank(message = "Ngôn ngữ sách không được trống!")
    private String language;
    private String description;
    private String edition;
    @NotNull
    private BigDecimal price;
    @NotNull
    private BigDecimal cost;
    @Min(0)
    private int quantity;
    private LocalDate publishcationDate;
    @NotBlank(message = "Tên tác giả không được trống!")
    private String authors;
    private UUID category;
    @NotBlank(message = "Tên nhà xuất bản không được trống!")
    private String publisher;
}
