package com.app.toko.repository;

import com.app.toko.entity.Category;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
  boolean existsByName(String name);
}
