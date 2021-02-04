package com.venkat.bookstore.service;

import com.venkat.bookstore.model.cart.ShoppingCart;
import com.venkat.bookstore.model.customer.CustomerForm;
import com.venkat.bookstore.model.order.OrderDetails;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    long placeOrder(CustomerForm form, ShoppingCart cart);

    OrderDetails getOrderDetails(long orderId);
}
