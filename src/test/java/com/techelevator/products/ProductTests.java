package com.techelevator.products;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ProductTests {
    @Test
    public void getMessage_method_should_return_message_corresponding_to_product_type() {
        Product chips = new Chip("Chip Chip Chap", new BigDecimal(3));

        String actual = chips.getMessage();
        String expected = "Crunch Crunch, Yum!";

        Assert.assertEquals(expected, actual);
    }
}
