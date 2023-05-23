package com.app.toko.mapper;

import com.app.toko.entity.Book;
import com.app.toko.payload.request.CreateBookRequest;
import com.app.toko.payload.request.UpdateBookRequest;
import com.app.toko.payload.response.BookResponse;

public interface BookMapper {
    public BookResponse toBookResponse(Book book);

    public Book toBook(CreateBookRequest createBookRequest);

    public void updateBook(
            Book book,
            UpdateBookRequest updateBookRequest);
}
