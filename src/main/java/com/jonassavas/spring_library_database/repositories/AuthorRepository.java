package com.jonassavas.spring_library_database.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jonassavas.spring_library_database.domain.Author;

@Repository // Similar to component, it describes it as a repository
public interface AuthorRepository extends CrudRepository<Author, Long>{
    
}
