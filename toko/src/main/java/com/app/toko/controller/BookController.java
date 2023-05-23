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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.app.toko.config.ApiEndpoint;
import com.app.toko.payload.request.CreateBookRequest;
import com.app.toko.payload.request.UpdateBookRequest;
import com.app.toko.payload.response.BookResponse;
import com.app.toko.service.BookService;

@RestController
@RequestMapping(ApiEndpoint.BOOKS)
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        return ResponseEntity.ok(this.bookService.getBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable UUID id) {
        return ResponseEntity.ok(this.bookService.getBookById(id));
    }

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@RequestParam("files") MultipartFile[] files,
            @RequestBody CreateBookRequest createBookRequest) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(
                        bookService.createBook(files, createBookRequest).getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping
    public ResponseEntity<BookResponse> addBookImage(@RequestParam("file") MultipartFile file,
            boolean isPresentation, UUID bookId) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(
                        bookService.addImage(file, isPresentation, bookId).getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable UUID id,
            @RequestBody UpdateBookRequest updateBookRequest) {
        this.bookService.updateBook(id, updateBookRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookResponse> deleteEmployee(
            @PathVariable UUID id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/image/{imageSource}")
    public ResponseEntity<BookResponse> deleteImage(
            @PathVariable UUID id, @PathVariable String imageSource) {
        bookService.deleteImage(id, imageSource);
        return ResponseEntity.noContent().build();
    }
}
