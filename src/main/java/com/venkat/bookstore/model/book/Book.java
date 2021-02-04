package com.venkat.bookstore.model.book;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "book",schema = "bookstore")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id")
    private long bookId;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "price")
    private int price;

    @Column(name = "is_public")
    private boolean isPublic;

    @Column(name = "category_id")
    private long categoryId;

}
