package com.app.toko.mapper.impl;

import org.springframework.stereotype.Component;

import com.app.toko.entity.Category;
import com.app.toko.mapper.CategoryMapper;
import com.app.toko.payload.request.CreateCategoryRequest;
import com.app.toko.payload.request.UpdateCategoryRequest;
import com.app.toko.payload.response.CategoryResponse;

@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse
                .builder()
                .id(category.getId())
                .name(category.getName())
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
                .parent(createCategoryRequest.getParent())
                .build();
    }

    @Override
    public void updateCategory(Category category, UpdateCategoryRequest updateCategoryRequest) {
        category.setName(updateCategoryRequest.getName());
        category.setParent(updateCategoryRequest.getParent());
    }
}
