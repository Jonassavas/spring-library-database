package com.jonassavas.spring_library_database.dao.impl;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import com.jonassavas.spring_library_database.TestDataUtil;
import com.jonassavas.spring_library_database.dao.impl.AuthorDaoImpl;
import com.jonassavas.spring_library_database.dao.impl.BookDaoImpl;
import com.jonassavas.spring_library_database.dao.impl.BookDaoImpl.BookRowMapper;
import com.jonassavas.spring_library_database.domain.Book;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl underTest;

    @Test 
    public void testThatCreateBookGeneratesCorrectSql(){
        Book book = TestDataUtil.createTestBook();

        underTest.create(book);

        verify(jdbcTemplate).update(
            eq("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)"),
            eq("978-1-2345-6789-0"),
            eq("The Shadow in the Attic"),
            eq(1L)
        );
    }
    
    @Test
    public void testThatFindOneBookGeneratesCorrectSql(){
        underTest.find("978-1-2345-6789-0");
        verify(jdbcTemplate).query(
                                eq("SELECT isbn FROM books WHERE isbn = ? LIMIT 1"),
                                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(),
                                eq("978-1-2345-6789-0")
        );
    }
}
