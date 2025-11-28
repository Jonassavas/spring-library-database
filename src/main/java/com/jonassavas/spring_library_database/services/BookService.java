package com.jonassavas.spring_library_database.services;

import com.jonassavas.spring_library_database.domain.entities.BookEntity;

public interface BookService {
    BookEntity createBook(String isbn, BookEntity bookEntity);
}
