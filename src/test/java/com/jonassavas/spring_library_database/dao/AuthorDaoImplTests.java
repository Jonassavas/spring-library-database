package com.jonassavas.spring_library_database.dao;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import com.jonassavas.spring_library_database.dao.impl.AuthorDaoImpl;
import com.jonassavas.spring_library_database.domain.Author;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTests {
    
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDaoImpl underTest;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSql(){
        Author author = Author.builder()
                        .id(1L)
                        .name("Abigail Rose")
                        .age(80)                
                        .build();
        underTest.create(author);

        verify(jdbcTemplate).update(
            eq("INSERT INTO authors (id, name, age) VALUES (?, ?, ?)"),
            eq(1L), eq("Abigail Rose"), eq(80)
        );
    }


}
