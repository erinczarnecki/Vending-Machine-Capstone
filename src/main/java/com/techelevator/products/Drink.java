package com.techelevator.products;

import java.math.BigDecimal;

public class Drink extends Product {

    public Drink(String name, BigDecimal price) {
        super(name, price);
    }

    @Override
    public String getMessage() {
        return "Glug Glug, Yum!";
    }
}
