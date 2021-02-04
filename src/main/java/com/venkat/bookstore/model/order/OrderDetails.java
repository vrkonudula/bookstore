package com.venkat.bookstore.model.order;

import com.venkat.bookstore.model.book.Book;
import com.venkat.bookstore.model.customer.Customer;
import lombok.Data;

import java.util.List;

@Data
public class OrderDetails {

    private CustomerOrder order;
    private Customer customer;
    private List<LineItem> lineItems;
    private List<Book> books;

    public OrderDetails(CustomerOrder order, Customer customer,
                        List<LineItem> lineItems, List<Book> books) {
        this.order = order;
        this.customer = customer;
        this.lineItems = lineItems;
        this.books = books;
    }
}
