package com.venkat.bookstore.model.order;

import com.venkat.bookstore.model.cart.ShoppingCart;
import com.venkat.bookstore.model.customer.CustomerForm;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderForm {

    private ShoppingCart cart;
    private CustomerForm customerForm;
}
