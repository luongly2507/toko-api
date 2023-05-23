package com.app.toko.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.toko.entity.AlbumId;
import com.app.toko.exception.ResourceNotFoundException;
import com.app.toko.mapper.BookMapper;
import com.app.toko.payload.request.CreateBookRequest;
import com.app.toko.payload.request.UpdateBookRequest;
import com.app.toko.payload.response.BookResponse;
import com.app.toko.repository.AlbumRepository;
import com.app.toko.repository.BookRepository;
import com.app.toko.service.BookService;
import com.app.toko.service.StorageService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<BookResponse> getBooks() {
        return bookRepository.findAll().stream().map(book -> bookMapper.toBookResponse(book)).toList();
    }

    @Override
    public BookResponse getBookById(UUID bookId) {
        return bookMapper.toBookResponse(bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sách này!")));
    }

    @Override
    public BookResponse createBook(MultipartFile[] files, CreateBookRequest createBookRequests) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createBook'");
    }

    @Override
    public BookResponse updateBook(UUID id, UpdateBookRequest updateBookRequests) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateBook'");
    }

    @Override
    public BookResponse addImage(MultipartFile file, boolean isPresentation, UUID bookId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addImage'");
    }

    @Override
    public void deleteImage(UUID bookId, String imageSource) {
        albumRepository.deleteById(AlbumId.builder().bookId(bookId).imageSource(imageSource).build());
    }

    @Override
    public void deleteBook(UUID id) {
        bookRepository.deleteById(id);
    }

}
