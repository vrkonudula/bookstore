package com.venkat.bookstore.model.cart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class ShoppingCart {

    private static final int SURCHARGE = 500;

    private List<ShoppingCartItem> items;


    public int getSurcharge() {
        return SURCHARGE;
    }


    @JsonIgnore
    public int getComputedSubtotal() {
        return items.stream()
                .mapToInt(item -> item.getQuantity() * item.getBookForm().getPrice())
                .sum();
    }
}
