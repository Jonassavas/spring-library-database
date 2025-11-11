package com.jonassavas.spring_library_database.dao;

import java.util.List;
import java.util.Optional;

import com.jonassavas.spring_library_database.domain.Book;

public interface BookDao {
    void create(Book book);
    Optional<Book> findOne(String isbn);
    List<Book> find();
}
