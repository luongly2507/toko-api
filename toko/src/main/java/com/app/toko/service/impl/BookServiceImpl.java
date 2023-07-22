package com.app.toko.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.app.toko.entity.Album;
import com.app.toko.entity.AlbumId;
import com.app.toko.entity.Book;
import com.app.toko.entity.Category;
import com.app.toko.exception.ResourceNotFoundException;
import com.app.toko.mapper.BookMapper;
import com.app.toko.payload.request.CreateBookRequest;
import com.app.toko.payload.request.UpdateBookRequest;
import com.app.toko.payload.response.BookResponse;
import com.app.toko.repository.AlbumRepository;
import com.app.toko.repository.BookRepository;
import com.app.toko.repository.CategoryRepository;
import com.app.toko.service.BookService;
import com.app.toko.service.StorageService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public Page<BookResponse> getBooks(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(book -> bookMapper.toBookResponse(book));
    }

    @Override
    public BookResponse getBookById(UUID bookId) {
        return bookMapper.toBookResponse(bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sách này!")));
    }

    @Override
    public BookResponse createBook(MultipartFile avatar, MultipartFile[] files, CreateBookRequest createBookRequests) {

        Book newBook = bookRepository.save(bookMapper.toBook(createBookRequests));
        newBook.setCategory(categoryRepository.findById(createBookRequests.getCategory())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy danh mục!")));
        Set<Album> albums = new HashSet<>();
        String avatarPath = storageService.store(avatar);
        albums.add(albumRepository.save(
                Album.builder()
                        .id(
                                AlbumId.builder()
                                        .bookId(newBook.getId())
                                        .imageSource(avatarPath)
                                        .build())
                        .isPresentation(true)
                        .build()));
        for (MultipartFile file : files) {
            String filePath = storageService.store(file);
            albums.add(albumRepository.save(
                    Album.builder()
                            .id(
                                    AlbumId.builder()
                                            .bookId(newBook.getId())
                                            .imageSource(filePath)
                                            .build())
                            .isPresentation(false)
                            .build()));
        }
        newBook.setAlbums(albums);
        bookRepository.save(newBook);
        return bookMapper.toBookResponse(newBook);
    }

    @Override
    public BookResponse updateBook(UUID id, UpdateBookRequest updateBookRequests) {
        Book existsBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sách này!"));
        bookMapper.updateBook(existsBook, updateBookRequests);
        existsBook.setCategory(categoryRepository.findById(updateBookRequests.getCategory())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy danh mục này")));
        return bookMapper.toBookResponse(existsBook);
    }

    @Override
    public BookResponse addImage(MultipartFile file, boolean isPresentation, UUID id) {
        Book existsBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sách này!"));
        existsBook.getAlbums().add(
                Album.builder()
                        .isPresentation(isPresentation)
                        .id(
                                AlbumId.builder()
                                        .bookId(id)
                                        .imageSource(storageService.store(
                                                file))
                                        .build())
                        .build());
        return bookMapper.toBookResponse(bookRepository.save(existsBook));
    }

    @Override
    public void deleteImage(UUID bookId, String imageSource) {
        try {
            albumRepository.deleteById(AlbumId.builder().bookId(bookId).imageSource(imageSource).build());
            FileSystemUtils.deleteRecursively(storageService.load(imageSource));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBook(UUID id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Page<BookResponse> searchBookByCategoryName(Pageable pageable, String categoryName, String language) {
        List<BookResponse> listBook = new ArrayList<>();
        List<Category> children = new ArrayList<>();

        for (Category parent : categoryRepository.findAllParent()) {
            if (parent.getParent() != null)
                break;
            if (Objects.equals(parent.getName(), categoryName)) {
                System.out.println(parent.getName());
                children = categoryRepository.findAllChildren(parent.getId());
            }
        }
        if (children != null && children.size() > 0) {

            for (Category child : children) {
                listBook.addAll(bookRepository
                        .findAllByCategoryNameContainingIgnoreCaseAndLanguageContainingIgnoreCase(pageable,
                                child.getName(), language)
                        .map(book -> bookMapper.toBookResponse(book)).stream().toList());
            }
            Page<BookResponse> bookResponsePage = new PageImpl<>(listBook);
            return bookResponsePage;
        }
        return bookRepository.findAllByCategoryNameContainingIgnoreCaseAndLanguageContainingIgnoreCase(pageable,
                categoryName, language).map(book -> bookMapper.toBookResponse(book));

    }

    @Override
    public Page<BookResponse> searchBookByTitle(Pageable pageable, String title, String language) {
        System.out.println("That su ko ?" + language + title
                + bookRepository
                        .findAllByTitleContainingIgnoreCaseAndLanguageContainingIgnoreCase(pageable, title, language)
                        .stream().toList());
        return bookRepository
                .findAllByTitleContainingIgnoreCaseAndLanguageContainingIgnoreCase(pageable, title, language)
                .map(book -> bookMapper.toBookResponse(book));

    }

}
