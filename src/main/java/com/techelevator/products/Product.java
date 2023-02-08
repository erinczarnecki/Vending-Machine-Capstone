package com.techelevator.products;

import java.math.BigDecimal;

public abstract class Product {
    //Initial stock for each product is assigned to 5
    private final int INITIAL_STOCK_QUANTITY = 5;
    private String productName = "";
    //Using BigDecimal because it is recommended when dealing with money
    private BigDecimal price = new BigDecimal(0);
    private int stock = INITIAL_STOCK_QUANTITY;

    public Product(String name, BigDecimal price) {
        this.productName = name;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    //Step 7 (ii): Each product type will display unique message when dispensed
    public abstract String getMessage();


}
