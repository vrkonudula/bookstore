package com.venkat.bookstore.repositories;

import com.venkat.bookstore.model.customer.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findByCustomerIdEquals(long customerId);
}
