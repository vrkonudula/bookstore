package com.venkat.bookstore.model.cart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.venkat.bookstore.model.book.BookForm;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class ShoppingCartItem {

    private int quantity;

    @JsonProperty("book")
    private BookForm bookForm;

    @JsonIgnore
    public long getBookId() {
        return bookForm.getBookId();
    }
}
