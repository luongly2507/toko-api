package com.app.toko.repository;

import com.app.toko.entity.Author;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, UUID> {}
