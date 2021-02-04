package com.venkat.bookstore.model.category;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.venkat.bookstore.model.book.Book;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "category",schema = "bookstore")
@JsonIgnoreProperties(value = "books")
public class Category {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private long categoryId;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY, targetEntity = Book.class)
    @JoinColumn(name = "category_id")
    @OrderBy("bookId asc")
    private List<Book> books;
}
