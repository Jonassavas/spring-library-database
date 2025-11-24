package com.jonassavas.spring_library_database.repositories;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jonassavas.spring_library_database.TestDataUtil;
import com.jonassavas.spring_library_database.domain.Author;
import com.jonassavas.spring_library_database.domain.Book;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryIntegrationTests {
    
    private BookRepository underTest;

    @Autowired
    public BookRepositoryIntegrationTests(BookRepository underTest){
        this.underTest = underTest;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.createTestAuthorA();
        Book book = TestDataUtil.createTestBookA(author);
        underTest.save(book);
        Optional<Book> result = underTest.findById(book.getIsbn());
        assertThat(result).isPresent();

        Book stored = result.get();

        assertThat(stored.getIsbn()).isEqualTo(book.getIsbn());
        assertThat(stored.getTitle()).isEqualTo(book.getTitle());
        assertThat(stored.getAuthor().getName()).isEqualTo(book.getAuthor().getName());
        assertThat(stored.getAuthor().getAge()).isEqualTo(book.getAuthor().getAge());
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.createTestAuthorA();

        Book bookA = TestDataUtil.createTestBookA(author);
        underTest.save(bookA);

        Book bookB = TestDataUtil.createTestBookB(author);
        underTest.save(bookB);

        Book bookC = TestDataUtil.createTestBookC(author);
        underTest.save(bookC);

        Iterable<Book> result = underTest.findAll();
        assertThat(result)
                .hasSize(3);
                //.containsExactly(bookA, bookB, bookC);
    }

    @Test
    public void testThatBookCanBeUpdated(){
        Author author = TestDataUtil.createTestAuthorA();

        Book bookA = TestDataUtil.createTestBookA(author);
        underTest.save(bookA);

        bookA.setTitle("UPDATED");
        underTest.save(bookA);

        Optional<Book> result = underTest.findById(bookA.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get().getTitle()).isEqualTo(bookA.getTitle());
    }

    @Test
    public void testThatBookCanBeDeleted(){
        Author author = TestDataUtil.createTestAuthorA();

        Book bookA = TestDataUtil.createTestBookA(author);
        underTest.save(bookA);

        underTest.deleteById(bookA.getIsbn());
        Optional<Book> result = underTest.findById(bookA.getIsbn());
        assertThat(result).isEmpty();
    }

}
