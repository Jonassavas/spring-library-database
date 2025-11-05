package com.jonassavas.spring_library_database.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Creates toString(), getters/setters
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {
    private Long id;
    private String name;
    private Integer age;
}
