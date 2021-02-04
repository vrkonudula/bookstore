package com.venkat.bookstore.model.order;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "customer_order_line_item", schema = "bookstore")
public class LineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long lineItemId;

    @Column(name = "book_id")
    private long bookId;

    @Column(name = "customer_order_id")
    private long orderId;

    @Column(name = "quantity")
    private int quantity;

    public LineItem(long bookId, long orderId, int quantity) {
        this.bookId = bookId;
        this.orderId = orderId;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "LineItem{" +
                "bookId=" + bookId +
                ", orderId=" + orderId +
                ", quantity=" + quantity +
                '}';
    }
}
