package com.app.toko.service.impl;

import com.app.toko.entity.Category;
import com.app.toko.exception.ConflictException;
import com.app.toko.exception.ResourceNotFoundException;
import com.app.toko.mapper.CategoryMapper;
import com.app.toko.payload.request.CreateCategoryRequest;
import com.app.toko.payload.request.UpdateCategoryRequest;
import com.app.toko.payload.response.CategoryResponse;
import com.app.toko.repository.CategoryRepository;
import com.app.toko.service.CategoryService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private CategoryMapper categoryMapper;

  @Override
  public CategoryResponse getCategoryById(UUID id) {
    return this.categoryMapper.toCategoryResponse(this.findById(id));
  }

  @Override
  public Page<CategoryResponse> getCategories(Pageable pageable) {
    return this.categoryRepository.findAll(pageable)
      .map(category -> categoryMapper.toCategoryResponse(category));
  }

  @Override
  public CategoryResponse createCategory(
    CreateCategoryRequest createCategoryRequest
  ) {
    if (this.categoryRepository.existsByName(createCategoryRequest.getName())) {
      throw new ConflictException("Category name already exists.");
    }
    return this.categoryMapper.toCategoryResponse(
        this.categoryRepository.save(
            this.categoryMapper.toCategory(createCategoryRequest)
          )
      );
  }

  @Override
  public void deleteCategory(UUID id) {
    this.categoryRepository.deleteById(id);
  }

  @Override
  public void updateCategory(
    UUID id,
    UpdateCategoryRequest updateCategoryRequest
  ) {
    Category category = this.findById(id);
    if (
      !updateCategoryRequest.getName().equals(category.getName()) &&
      this.categoryRepository.existsByName(updateCategoryRequest.getName())
    ) {
      throw new ConflictException("Category name already exists.");
    }
    this.categoryMapper.updateCategory(category, updateCategoryRequest);
    this.categoryRepository.save(category);
  }

  private Category findById(UUID id) {
    return this.categoryRepository.findById(id)
      .orElseThrow(() ->
        new ResourceNotFoundException("Category not found for id.")
      );
  }

  @Override
  public List<CategoryResponse> getCategoryParent() {
    return this.categoryRepository.findAllParent()
      .stream()
      .map(category -> categoryMapper.toCategoryResponse(category))
      .toList();
  }
}
