package com.jonassavas.spring_library_database;

import com.jonassavas.spring_library_database.domain.dto.AuthorDto;
import com.jonassavas.spring_library_database.domain.dto.BookDto;
import com.jonassavas.spring_library_database.domain.entities.AuthorEntity;
import com.jonassavas.spring_library_database.domain.entities.BookEntity;

public final class TestDataUtil {

    private TestDataUtil(){

    }

    public static AuthorEntity createTestAuthorEntityA() {
        return AuthorEntity.builder()
                        //.id(1L)
                        .name("Abigail Rose")
                        .age(80)                
                        .build();
    }

    public static AuthorEntity createTestAuthorEntityB() {
        return AuthorEntity.builder()
                        //.id(2L)
                        .name("Thomas Cronin")
                        .age(44)                
                        .build();
    }

    public static AuthorEntity createTestAuthorEntityC() {
        return AuthorEntity.builder()
                        //.id(3L)
                        .name("Jesse A Casey")
                        .age(24)                
                        .build();
    }

    public static BookEntity createTestBookEntityA(final AuthorEntity author) {
        return BookEntity.builder()
                    .isbn("978-1-2345-6789-0")
                    .title("The Shadow in the Attic")
                    .author(author)
                    .build();
    }

    public static BookDto createTestBookDtoA(final AuthorDto author) {
        return BookDto.builder()
                    .isbn("978-1-2345-6789-0")
                    .title("The Shadow in the Attic")
                    .author(author)
                    .build();
    }

    public static BookEntity createTestBookEntityB(final AuthorEntity author) {
        return BookEntity.builder()
                    .isbn("978-1-2345-6789-1")
                    .title("Beyond the Horizon")
                    .author(author)
                    .build();
    }

    public static BookEntity createTestBookEntityC(final AuthorEntity author) {
        return BookEntity.builder()
                    .isbn("978-1-2345-6789-2")
                    .title("The Last Ember")
                    .author(author)
                    .build();
    }
    
}
