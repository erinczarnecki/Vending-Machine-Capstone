package com.techelevator.products;

import java.math.BigDecimal;

public class Gum extends Product {

    public Gum(String name, BigDecimal price) {
        super(name, price);
    }

    @Override
    public String getMessage() {
        return "Chew Chew, Yum!";
    }
}
