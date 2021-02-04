package com.venkat.bookstore.model.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.venkat.bookstore.model.customer.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@Table(name = "customer_order", schema = "bookstore")
@NoArgsConstructor
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_order_id")
    private long orderId;

    @Column(name = "amount")
    private int amount;

    @Column(name = "date_created")
    @CreationTimestamp
    private Date dateCreated;

    @Column(name = "confirmation_number")
    private long confirmationNumber;

    @Column(name = "customer_id")
    private long customerId;

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", amount=" + amount +
                ", dateCreated=" + dateCreated +
                ", confirmationNumber=" + confirmationNumber +
                ", customerId=" + customerId +
                '}';
    }

    public CustomerOrder(int amount, long confirmationNumber, long customerId) {
        this.amount = amount;
        this.confirmationNumber = confirmationNumber;
        this.customerId = customerId;
    }

}
