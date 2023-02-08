package com.techelevator.products;

import java.math.BigDecimal;

public class Chip extends Product {

    public Chip(String name, BigDecimal price) {
        super(name, price);
    }

    @Override
    public String getMessage() {
        return "Crunch Crunch, Yum!";
    }
}
