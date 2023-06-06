package com.app.toko.repository;

import com.app.toko.entity.Book;
import com.app.toko.payload.response.BookResponse;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, UUID> {

    Page<Book> findAllByCategoryNameContainingIgnoreCase(Pageable pageable, String categoryName);

    Page<Book> findAllByTitleContainingIgnoreCase(Pageable pageable, String title);
}
