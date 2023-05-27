package com.app.toko.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.app.toko.config.ApiEndpoint;
import com.app.toko.payload.request.CreateCategoryRequest;
import com.app.toko.payload.request.UpdateCategoryRequest;
import com.app.toko.payload.response.CategoryResponse;
import com.app.toko.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(ApiEndpoint.CATEGORIES)
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @GetMapping
  public ResponseEntity<List<CategoryResponse>> getAllCategories() {
    return ResponseEntity.ok(this.categoryService.getCategories());
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable UUID id) {
    return ResponseEntity.ok(this.categoryService.getCategoryById(id));
  }

  @PostMapping
  public ResponseEntity<CategoryResponse> createCategory(
      @RequestBody @Valid CreateCategoryRequest createCategoryRequest) {
    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(
            categoryService.createCategory(createCategoryRequest).getId())
        .toUri();
    return ResponseEntity.created(location).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<CategoryResponse> updateCategory(@PathVariable UUID id,
      @RequestBody @Valid UpdateCategoryRequest updateCategoryRequest) {
    this.categoryService.updateCategory(id, updateCategoryRequest);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<CategoryResponse> deleteEmployee(
      @PathVariable UUID id) {
    categoryService.deleteCategory(id);
    return ResponseEntity.noContent().build();
  }
}
