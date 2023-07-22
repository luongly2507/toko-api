package com.app.toko.repository;

import com.app.toko.entity.Book;
import com.app.toko.payload.response.BookResponse;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, UUID> {

    Page<Book> findAllByCategoryNameContainingIgnoreCaseAndLanguageContainingIgnoreCase(Pageable pageable, String categoryName , String language);

    Page<Book> findAllByTitleContainingIgnoreCaseAndLanguageContainingIgnoreCase(Pageable pageable ,String title, String language );
}
