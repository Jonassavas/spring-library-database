package com.jonassavas.spring_library_database.services.impl;

import org.springframework.stereotype.Service;

import com.jonassavas.spring_library_database.domain.entities.AuthorEntity;
import com.jonassavas.spring_library_database.repositories.AuthorRepository;
import com.jonassavas.spring_library_database.services.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {
    
    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity createAuthor(AuthorEntity authorEntity){
        return authorRepository.save(authorEntity);
    }
}
