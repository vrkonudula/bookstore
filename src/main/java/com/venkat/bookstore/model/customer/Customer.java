package com.venkat.bookstore.model.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.venkat.bookstore.model.order.CustomerOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "customer",schema = "bookstore")
@JsonIgnoreProperties(value = "orders")
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private long customerId;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "cc_number")
    private String ccNumber;

    @Column(name = "cc_exp_date")
    private LocalDate ccExpDate;

    public Customer(String name, String address, String phone, String email, String ccNumber, LocalDate ccExpDate) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.ccNumber = ccNumber;
        this.ccExpDate = ccExpDate;
    }

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,targetEntity = CustomerOrder.class)
    @JoinColumn(name = "customer_id")
    @OrderBy("orderId asc")
    private List<CustomerOrder> orders;
}
