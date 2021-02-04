package com.venkat.bookstore.repositories;

import com.venkat.bookstore.model.order.CustomerOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<CustomerOrder, Long> {

    CustomerOrder findByOrderIdEquals(long orderId);
}
