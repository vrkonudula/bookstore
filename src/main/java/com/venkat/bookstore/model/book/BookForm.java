package com.venkat.bookstore.model.book;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class BookForm {
    private long bookId;
    private int price;
    private long categoryId;

    public BookForm(long bookId, int price, int points, Long categoryId) {
        this.bookId = bookId;
        this.price = price;
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "business.book.BookForm[book_id=" + bookId + "]";
    }

}
