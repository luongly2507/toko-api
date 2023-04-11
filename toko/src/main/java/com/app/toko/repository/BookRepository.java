package com.app.toko.repository;

import com.app.toko.entity.Book;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, UUID> {}
