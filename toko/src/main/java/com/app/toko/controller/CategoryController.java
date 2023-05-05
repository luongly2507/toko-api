package com.app.toko.controller;

import com.app.toko.payload.request.CreateCategoryRequest;
import com.app.toko.payload.request.UpdateCategoryRequest;
import com.app.toko.payload.response.CategoryResponse;
import com.app.toko.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/categories")
@Tag(name = "Category API", description = "API for managing categories.")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @Operation(
    description = "Get All Categories",
    summary = "Get All Categories",
    responses = {
      @ApiResponse(
        content = @Content(
          array = @ArraySchema(schema = @Schema(implementation = Page.class))
        ),
        responseCode = "200"
      ),
    }
  )
  @ApiResponses(
    value = { @ApiResponse(responseCode = "200", description = "OK") }
  )
  @GetMapping
  public ResponseEntity<Page<CategoryResponse>> getAllCategories(
    Pageable pageable
  ) {
    return ResponseEntity.ok(this.categoryService.getCategories(pageable));
  }

  @Operation(
    description = "Get All Category PaParents",
    summary = "Get All Categories",
    responses = {
      @ApiResponse(
        content = @Content(
          array = @ArraySchema(schema = @Schema(implementation = Page.class))
        ),
        responseCode = "200"
      ),
    }
  )
  @ApiResponses(
    value = { @ApiResponse(responseCode = "200", description = "OK") }
  )
  @GetMapping("/parent")
  public ResponseEntity<List<CategoryResponse>> getAllCategoryParents() {
    return ResponseEntity.ok(this.categoryService.getCategoryParent());
  }

  @Operation(
    description = "Get Category by ID",
    summary = "Get All Category by ID",
    responses = {
      @ApiResponse(
        content = @Content(
          array = @ArraySchema(
            schema = @Schema(implementation = CategoryResponse.class)
          )
        ),
        responseCode = "200"
      ),
      @ApiResponse(
        content = @Content(
          array = @ArraySchema(schema = @Schema(implementation = String.class))
        ),
        responseCode = "404"
      ),
    }
  )
  @ApiResponses(
    value = {
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "404", description = "Not Found"),
    }
  )
  @GetMapping("/{id}")
  public ResponseEntity<CategoryResponse> getCategoryById(
    @PathVariable UUID id
  ) {
    return ResponseEntity.ok(this.categoryService.getCategoryById(id));
  }

  @Operation(
    description = "Create a new Category",
    summary = "Create a new Category",
    responses = {
      @ApiResponse(responseCode = "201", content = @Content),
      @ApiResponse(
        responseCode = "409",
        content = @Content(schema = @Schema(implementation = String.class))
      ),
    }
  )
  @ApiResponses(
    value = {
      @ApiResponse(responseCode = "201", description = "Created"),
      @ApiResponse(responseCode = "409", description = "Conflict"),
    }
  )
  @PostMapping
  public ResponseEntity<CategoryResponse> createCategory(
    @Valid @RequestBody CreateCategoryRequest createCategoryRequest
  ) {
    System.out.println(createCategoryRequest);
    URI location = ServletUriComponentsBuilder
      .fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(
        categoryService.createCategory(createCategoryRequest).getId()
      )
      .toUri();
    return ResponseEntity.created(location).build();
  }

  @Operation(
    description = "Update Category",
    summary = "Update Category",
    responses = {
      @ApiResponse(responseCode = "204", content = @Content),
      @ApiResponse(
        responseCode = "409",
        content = @Content(schema = @Schema(implementation = String.class))
      ),
    }
  )
  @ApiResponses(
    value = {
      @ApiResponse(responseCode = "204", description = "No Content"),
      @ApiResponse(responseCode = "409", description = "Conflict"),
    }
  )
  @PutMapping("/{id}")
  @PatchMapping("/{id}")
  public ResponseEntity<CategoryResponse> updateCategory(
    @PathVariable UUID id,
    @RequestBody UpdateCategoryRequest updateCategoryRequest
  ) {
    this.categoryService.updateCategory(id, updateCategoryRequest);
    return ResponseEntity.noContent().build();
  }

  @Operation(
    description = "Delete Category by Id",
    summary = "Delete Category by Id",
    responses = { @ApiResponse(responseCode = "204", content = @Content) }
  )
  @ApiResponses(
    value = { @ApiResponse(responseCode = "204", description = "No Content") }
  )
  @DeleteMapping("/{id}")
  public ResponseEntity<CategoryResponse> deleteEmployee(
    @PathVariable UUID id
  ) {
    categoryService.deleteCategory(id);
    return ResponseEntity.noContent().build();
  }
}
