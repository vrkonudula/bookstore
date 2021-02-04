package com.venkat.bookstore.repositories;

import com.venkat.bookstore.model.order.LineItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineItemRepository extends CrudRepository<LineItem, Long> {
    List<LineItem> findByOrderIdEquals(long orderId);
}
