package com.app.toko.mapper.impl;

import com.app.toko.entity.Category;
import com.app.toko.exception.BadRequestException;
import com.app.toko.mapper.CategoryMapper;
import com.app.toko.payload.request.CreateCategoryRequest;
import com.app.toko.payload.request.UpdateCategoryRequest;
import com.app.toko.payload.response.CategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapperImpl implements CategoryMapper {

  @Override
  public CategoryResponse toCategoryResponse(Category category) {
    return CategoryResponse
      .builder()
      .id(category.getId())
      .name(category.getName())
      .parent(category.getParent())
      .imageSource(category.getDescription())
      .build();
  }

  @Override
  public Category toCategory(CategoryResponse categoryResponse) {
    return Category.builder().build();
  }

  @Override
  public Category toCategory(CreateCategoryRequest createCategoryRequest) {
    return Category
      .builder()
      .name(createCategoryRequest.getName())
      .description(createCategoryRequest.getDescription())
      .build();
  }

  @Override
  public void updateCategory(
    Category category,
    UpdateCategoryRequest updateCategoryRequest
  ) {
    if (!updateCategoryRequest.getName().isBlank()) {
      if (
        updateCategoryRequest.getName().length() >= 3 &&
        updateCategoryRequest.getName().length() <= 100
      ) {
        category.setName(updateCategoryRequest.getName());
      } else {
        throw new BadRequestException(
          "Category name must be between 3 and 100 characters"
        );
      }
    }
    if (!updateCategoryRequest.getDescription().isBlank()) {
      category.setDescription(updateCategoryRequest.getDescription());
    }
  }
}
