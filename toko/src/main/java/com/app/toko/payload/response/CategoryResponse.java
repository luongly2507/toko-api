package com.app.toko.payload.response;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryResponse {

  private UUID id;
  private String name;
  private UUID parent;
  private String imageSource;
}
