package com.app.toko.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.toko.entity.Category;
import com.app.toko.exception.BadRequestException;
import com.app.toko.exception.ConflictException;
import com.app.toko.exception.ResourceNotFoundException;
import com.app.toko.mapper.CategoryMapper;
import com.app.toko.payload.request.CreateCategoryRequest;
import com.app.toko.payload.request.UpdateCategoryRequest;
import com.app.toko.payload.response.CategoryResponse;
import com.app.toko.repository.CategoryRepository;
import com.app.toko.service.CategoryService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponse> getCategories() {
        return this.categoryRepository.findAllParent().stream().map(
                c -> {
                    CategoryResponse categoryResponse = categoryMapper.toCategoryResponse(c);
                    setChildren(categoryResponse);
                    return categoryResponse;
                }).toList();
    }

    private void setChildren(CategoryResponse categoryResponse) {
        List<CategoryResponse> children = this.categoryRepository.findAllChildren(categoryResponse.getId()).stream()
                .map(c -> categoryMapper.toCategoryResponse(c)).toList();
        if (!children.isEmpty()) {
            categoryResponse.setChildren(children);
            for (CategoryResponse child : children) {
                setChildren(child);
            }
        }
    }

    @Override
    public CategoryResponse getCategoryById(UUID id) {
        return this.categoryMapper.toCategoryResponse(this.findById(id));
    }

    @Override
    public CategoryResponse createCategory(CreateCategoryRequest createCategoryRequest) {
        Category category = categoryMapper.toCategory(createCategoryRequest);

        if (createCategoryRequest.getParent() != null
                && !categoryRepository.existsById(createCategoryRequest.getParent())) {
            throw new BadRequestException("Không tìm thấy danh mục cha.");
        }
        // Check name is unique
        if (categoryRepository.existsByName(category.getName())) {
            throw new ConflictException("Tên danh mục đã tồn tại.");
        }

        return this.categoryMapper.toCategoryResponse(
                this.categoryRepository.save(category));
    }

    @Override
    public void updateCategory(UUID id, UpdateCategoryRequest updateCategoryRequest) {
        if (updateCategoryRequest.getParent() != null
                && !categoryRepository.existsById(updateCategoryRequest.getParent())) {
            throw new BadRequestException("Không tìm thấy danh mục cha.");
        }
        Category category = this.findById(id);
        if (!updateCategoryRequest.getName().equals(category.getName())
                && this.categoryRepository.existsByName(updateCategoryRequest.getName())) {
            throw new ConflictException("Tên danh mục đã tồn tại.");
        }

        this.categoryMapper.updateCategory(category, updateCategoryRequest);
        this.categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(UUID id) {
        this.categoryRepository.deleteById(id);
    }

    private Category findById(UUID id) {
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy danh mục!"));
    }

}
