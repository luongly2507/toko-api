package com.app.toko.service;

import java.util.List;
import java.util.UUID;

import com.app.toko.payload.request.CreateCategoryRequest;
import com.app.toko.payload.request.UpdateCategoryRequest;
import com.app.toko.payload.response.CategoryResponse;

public interface CategoryService {

    public List<CategoryResponse> getCategories();

    public CategoryResponse getCategoryById(UUID id);

    public CategoryResponse createCategory(CreateCategoryRequest createCategoryRequest);

    public void updateCategory(UUID id, UpdateCategoryRequest updateCategoryRequest);

    public void deleteCategory(UUID id);
}