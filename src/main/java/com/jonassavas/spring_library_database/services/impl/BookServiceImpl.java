package com.jonassavas.spring_library_database.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public BookEntity createUpdateBook(String isbn, BookEntity book){
        book.setIsbn(isbn); // Ensure that the isbn is the one provided in the url
        return bookRepository.save(book);
    }

    @Override
    public List<BookEntity> findAll(){
        return StreamSupport
                .stream(
                    bookRepository.findAll().spliterator(), 
                    false)
                .collect(Collectors.toList());
    }

    @Override
    public Page<BookEntity> findAll(Pageable pageable){
        return bookRepository.findAll(pageable);
    }

    @Override
    public Optional<BookEntity> findOne(String isbn){
        return bookRepository.findById(isbn);
    }

    @Override
    public boolean isExists(String isbn){
        return bookRepository.existsById(isbn);
    }

    @Override
    public BookEntity partialUpdate(String isbn, BookEntity bookEntity){
        bookEntity.setIsbn(isbn);

        return bookRepository.findById(isbn).map(existingBook -> {
            Optional.ofNullable(bookEntity.getTitle()).ifPresent(existingBook::setTitle);
            return bookRepository.save(existingBook);
        }).orElseThrow(() -> new RuntimeException("Book does not exist"));
    }

    @Override
    public void delete(String isbn){
        bookRepository.deleteById(isbn);
    }
}
