package com.jonassavas.spring_library_database.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.jonassavas.spring_library_database.domain.dto.AuthorDto;
import com.jonassavas.spring_library_database.domain.entities.AuthorEntity;
import com.jonassavas.spring_library_database.mappers.Mapper;

@Component
public class AuthorMapper implements Mapper<AuthorEntity, AuthorDto> {
    
    private ModelMapper modelMapper;

    public AuthorMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Override
    public AuthorDto mapTo(AuthorEntity authorEntity){
        return modelMapper.map(authorEntity, AuthorDto.class);
    }

    @Override
    public AuthorEntity mapFrom(AuthorDto authorDto){
        return modelMapper.map(authorDto, AuthorEntity.class);
    }
}
