package com.app.toko.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoryRequest {

  @NotBlank(message = "Category Name not blank.")
  @Length(max = 150, message = "Category Name cannot exceed 150 characters.")
  private String name;

  @NotBlank(message = "Category description not blank.")
  private String description;
}
