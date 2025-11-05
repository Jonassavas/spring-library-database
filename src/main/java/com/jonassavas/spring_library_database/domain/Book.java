package com.jonassavas.spring_library_database.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Creates toString(), getters/setters
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    private String isbn;
    private String title;
    private Long authorId;
}
