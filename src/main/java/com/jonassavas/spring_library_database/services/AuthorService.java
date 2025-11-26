package com.jonassavas.spring_library_database.services;

import com.jonassavas.spring_library_database.domain.entities.AuthorEntity;

public interface AuthorService {
    AuthorEntity createAuthor(AuthorEntity author);
}
