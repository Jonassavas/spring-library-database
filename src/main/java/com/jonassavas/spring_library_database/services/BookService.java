package com.jonassavas.spring_library_database.services;

import java.util.List;
import java.util.Optional;

import com.jonassavas.spring_library_database.domain.entities.BookEntity;

public interface BookService {
    BookEntity createUpdateBook(String isbn, BookEntity bookEntity);

    List<BookEntity> findAll();

    Optional<BookEntity> findOne(String isbn);

    boolean isExists(String isbn);
}
