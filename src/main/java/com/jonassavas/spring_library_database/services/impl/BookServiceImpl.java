package com.jonassavas.spring_library_database.services.impl;

import org.springframework.stereotype.Service;

import com.jonassavas.spring_library_database.domain.entities.BookEntity;
import com.jonassavas.spring_library_database.repositories.BookRepository;
import com.jonassavas.spring_library_database.services.BookService;

@Service
public class BookServiceImpl implements BookService{
    
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public BookEntity createBook(String isbn, BookEntity book){
        book.setIsbn(isbn); // Ensure that the isbn is the one provided in the url
        return bookRepository.save(book);
    }

}
