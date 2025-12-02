package com.jonassavas.spring_library_database.services;

import java.util.List;

import com.jonassavas.spring_library_database.domain.entities.BookEntity;

public interface BookService {
    BookEntity createBook(String isbn, BookEntity bookEntity);

    List<BookEntity> findAll();
}
