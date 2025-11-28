package com.jonassavas.spring_library_database.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.jonassavas.spring_library_database.domain.dto.BookDto;
import com.jonassavas.spring_library_database.domain.entities.BookEntity;
import com.jonassavas.spring_library_database.mappers.Mapper;

@Component
public class BookMapper implements Mapper<BookEntity, BookDto>{

    private ModelMapper modelMapper;

    public BookMapper (ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Override
    public BookDto mapTo(BookEntity book) {
        return modelMapper.map(book, BookDto.class);
    }

    @Override
    public BookEntity mapFrom(BookDto bookDto) {
        return modelMapper.map(bookDto, BookEntity.class);
    }
}