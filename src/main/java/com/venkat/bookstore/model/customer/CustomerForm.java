package com.venkat.bookstore.model.customer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CustomerForm {

    private String name;

    private String address;

    private String phone;

    private String email;

    private String ccNumber;

    private String ccExpiryMonth;

    private String ccExpiryYear;

    @JsonCreator
    public CustomerForm(
            @JsonProperty("name") String name,
            @JsonProperty("address") String address,
            @JsonProperty("phone") String phone,
            @JsonProperty("email") String email,
            @JsonProperty("ccNumber") String ccNumber,
            @JsonProperty("ccExpiryMonth") String ccExpiryMonth,
            @JsonProperty("ccExpiryYear") String ccExpiryYear) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.ccNumber = ccNumber;
        this.ccExpiryMonth = ccExpiryMonth;
        this.ccExpiryYear = ccExpiryYear;
    }
}
