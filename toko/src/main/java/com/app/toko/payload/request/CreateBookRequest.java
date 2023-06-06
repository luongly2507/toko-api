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
public class CreateBookRequest {

    @NotBlank(message = "Tên sách không được trống!")
    private String title;
    @NotBlank(message = "Ngôn ngữ sách không được trống!")
    private String language;
    private String description;
    private String edition;
    @NotNull(message = "Giá bán không được trống!")
    private BigDecimal price;
    @NotNull(message = "Giá nhập không được trống!")
    private BigDecimal cost;
    @Min(value = 0, message = "Số lượng không được nhỏ hơn 0!")
    private int quantity;
    private LocalDate publishcationDate;
    @NotBlank(message = "Tên tác giả không được trống!")
    private String authors;
    private UUID category;
    @NotBlank(message = "Tên nhà xuất bản không được trống!")
    private String publisher;
}
