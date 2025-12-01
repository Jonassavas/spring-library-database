package com.jonassavas.spring_library_database.services;

import java.util.List;

import com.jonassavas.spring_library_database.domain.entities.AuthorEntity;

public interface AuthorService {
    AuthorEntity createAuthor(AuthorEntity author);

    List<AuthorEntity> findAll();
}
