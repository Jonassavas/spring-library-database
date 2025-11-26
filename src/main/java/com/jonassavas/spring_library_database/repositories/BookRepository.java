package com.jonassavas.spring_library_database.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jonassavas.spring_library_database.domain.entities.BookEntity;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, String>{
    
}
