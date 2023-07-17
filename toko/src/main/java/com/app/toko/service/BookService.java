package com.app.toko.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.app.toko.payload.request.CreateBookRequest;
import com.app.toko.payload.request.UpdateBookRequest;
import com.app.toko.payload.response.BookResponse;

public interface BookService {
    public Page<BookResponse> getBooks(Pageable pageable);

    public BookResponse getBookById(UUID bookId);

    public BookResponse createBook(MultipartFile avatar, MultipartFile[] files,
            CreateBookRequest createBookRequests);

    public BookResponse updateBook(UUID id, UpdateBookRequest updateBookRequests);

    public BookResponse addImage(MultipartFile file, boolean isPresentation, UUID bookId);

    public void deleteImage(UUID bookId, String imageSource);

    public void deleteBook(UUID id);

    public Page<BookResponse> searchBookByCategoryName(Pageable pageable, String categoryName , String language);

    public Page<BookResponse> searchBookByTitle(Pageable pageable, String title , String language);
}
