package com.app.toko.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.toko.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

  boolean existsByName(String name);

  @Query(nativeQuery = true, value = "SELECT * FROM Category c WHERE c.parent IS NULL")
  List<Category> findAllParent();

  @Query(nativeQuery = true, value = "SELECT * FROM Category c WHERE c.parent = :parent ")
  List<Category> findAllChildren(@Param(value = "parent") UUID parent);
}
