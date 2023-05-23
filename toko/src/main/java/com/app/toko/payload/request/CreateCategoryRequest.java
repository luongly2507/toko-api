package com.app.toko.payload.request;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoryRequest {

  @NotBlank(message = "Tên danh mục không được trống!")
  @Length(max = 150, message = "Tên danh mục không được quá 150 kí tự!")
  private String name;

  private UUID parent;
}
