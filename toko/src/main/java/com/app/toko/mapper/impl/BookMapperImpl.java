package com.app.toko.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.toko.entity.Book;
import com.app.toko.mapper.AlbumMapper;
import com.app.toko.mapper.BookMapper;
import com.app.toko.mapper.CategoryMapper;
import com.app.toko.payload.request.CreateBookRequest;
import com.app.toko.payload.request.UpdateBookRequest;
import com.app.toko.payload.response.BookResponse;

@Component
public class BookMapperImpl implements BookMapper {

    @Autowired
    private AlbumMapper albumMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public BookResponse toBookResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .subTitle(book.getSubTitle())
                .authors(book.getAuthors())
                .albums(albumMapper.toAlbumResponses(book.getAlbums()))
                .category(categoryMapper.toCategoryResponse(book.getCategory()))
                .price(book.getPrice())
                .quantity(book.getQuantity())
                .description(book.getDescription())
                .edition(book.getEdition())
                .language(book.getLanguage())
                .publishcationDate(book.getPublishcationDate())
                .publisher(book.getPublisher())
                .build();
    }

    @Override
    public Book toBook(CreateBookRequest createBookRequest) {
        return Book.builder()
                .title(createBookRequest.getTitle())
                .subTitle(createBookRequest.getSubTitle())
                .authors(createBookRequest.getAuthors())
                .price(createBookRequest.getPrice())
                .cost(createBookRequest.getCost())
                .quantity(createBookRequest.getQuantity())
                .description(createBookRequest.getDescription())
                .edition(createBookRequest.getEdition())
                .language(createBookRequest.getLanguage())
                .publishcationDate(createBookRequest.getPublishcationDate())
                .publisher(createBookRequest.getPublisher())
                .build();
    }

    @Override
    public void updateBook(Book book, UpdateBookRequest updateBookRequest) {
        book.setTitle(updateBookRequest.getTitle());
        book.setSubTitle(updateBookRequest.getSubTitle());
        book.setAuthors(updateBookRequest.getAuthors());
        book.setPrice(updateBookRequest.getPrice());
        book.setCost(updateBookRequest.getCost());
        book.setQuantity(updateBookRequest.getQuantity());
        book.setDescription(updateBookRequest.getDescription());
        book.setEdition(updateBookRequest.getEdition());
        book.setLanguage(updateBookRequest.getLanguage());
        book.setPublishcationDate(updateBookRequest.getPublishcationDate());
        book.setPublisher(updateBookRequest.getPublisher());
    }

}
