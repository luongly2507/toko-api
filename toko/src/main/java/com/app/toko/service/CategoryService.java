package com.app.toko.service;

import com.app.toko.payload.request.CreateCategoryRequest;
import com.app.toko.payload.request.UpdateCategoryRequest;
import com.app.toko.payload.response.CategoryResponse;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
  public CategoryResponse createCategory(
    CreateCategoryRequest createCategoryRequest
  );

  public void deleteCategory(UUID id);

  public void updateCategory(
    UUID id,
    UpdateCategoryRequest updateCategoryRequest
  );

  public CategoryResponse getCategoryById(UUID id);

  public Page<CategoryResponse> getCategories(Pageable pageable);

  public List<CategoryResponse> getCategoryParent();
}
