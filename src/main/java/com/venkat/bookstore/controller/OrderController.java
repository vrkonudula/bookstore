package com.venkat.bookstore.controller;

import com.venkat.bookstore.api.ApiException;
import com.venkat.bookstore.model.order.DefaultOrderService;
import com.venkat.bookstore.model.order.OrderDetails;
import com.venkat.bookstore.model.order.OrderForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/orders")
@CrossOrigin
public class OrderController {

    @Autowired
    private DefaultOrderService orderService;

    @PostMapping(path = "")
    public ResponseEntity<OrderDetails> placeOrder(@RequestBody OrderForm orderForm) {
        try {
            long orderId = orderService.placeOrder(orderForm.getCustomerForm(),orderForm.getCart());
            if (orderId > 0)
                return ResponseEntity.ok(orderService.getOrderDetails(orderId));
            else
                throw new ApiException.InvalidParameter("Unknown error occurred");
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Order placement failed",e);
        }
    }

}
