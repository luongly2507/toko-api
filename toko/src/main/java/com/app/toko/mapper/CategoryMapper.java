package com.app.toko.mapper;

import com.app.toko.entity.Category;
import com.app.toko.payload.request.CreateCategoryRequest;
import com.app.toko.payload.request.UpdateCategoryRequest;
import com.app.toko.payload.response.CategoryResponse;

public interface CategoryMapper {
  public CategoryResponse toCategoryResponse(Category category);

  public Category toCategory(CategoryResponse categoryResponse);

  public Category toCategory(CreateCategoryRequest createCategoryRequest);

  public void updateCategory(
    Category category,
    UpdateCategoryRequest updateCategoryRequest
  );
}
