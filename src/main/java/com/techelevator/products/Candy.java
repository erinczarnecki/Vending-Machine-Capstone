package com.techelevator.products;

import java.math.BigDecimal;

public class Candy extends Product {

    public Candy(String name, BigDecimal price) {
        super(name, price);
    }

    @Override
    public String getMessage() {
        return "Munch Munch, Yum!";
    }
}
