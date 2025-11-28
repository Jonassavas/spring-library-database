package com.jonassavas.spring_library_database.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.jonassavas.spring_library_database.domain.dto.BookDto;
import com.jonassavas.spring_library_database.domain.entities.BookEntity;
import com.jonassavas.spring_library_database.mappers.Mapper;
import com.jonassavas.spring_library_database.services.BookService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class BookController {

    private Mapper<BookEntity, BookDto> bookMapper;
    private BookService bookService;

    public BookController(Mapper<BookEntity, BookDto> bookMapper, BookService bookService){
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }
    
    @PutMapping("books/{isbn}")
    public ResponseEntity<BookDto> createBook(@PathVariable("isbn") String isbn, @RequestBody BookDto bookDto) {
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        BookEntity savedBookEntity = bookService.createBook(isbn, bookEntity);
        BookDto savedBookDto = bookMapper.mapTo(savedBookEntity);
        
        return new ResponseEntity<>(savedBookDto, HttpStatus.CREATED);
    }
}
