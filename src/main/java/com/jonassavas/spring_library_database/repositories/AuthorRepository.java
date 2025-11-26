package com.jonassavas.spring_library_database.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jonassavas.spring_library_database.domain.entities.AuthorEntity;

@Repository // Similar to component, it describes it as a repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long>{
    Iterable<AuthorEntity> ageLessThan(int i);

    @Query("SELECT a FROM AuthorEntity a WHERE a.age > ?1")
    Iterable<AuthorEntity> findAuthorsWithAgeGreaterThan(int age);
}
