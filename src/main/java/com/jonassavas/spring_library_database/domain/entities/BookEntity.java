package com.jonassavas.spring_library_database.domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Creates toString(), getters/setters
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class BookEntity {
    
    @Id
    private String isbn;

    private String title;

    @ManyToOne(cascade = CascadeType.ALL) // If we get the book back, we also retrieve the author
    @JoinColumn(name = "author_id")
    private AuthorEntity author;
}
