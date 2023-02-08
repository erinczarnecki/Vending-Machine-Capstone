package com.techelevator.transactions;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

public class TransactionsTest {
    private Transactions transactions;

    @Before
    public void setup() {
        this.transactions = new Transactions();
        //Adding money to balance to perform transactions
        transactions.feedMoney(new BigDecimal(5));
    }

    @After
    public void cleanup() {
    }

    @Test
    public void feedMoney_method_should_add_amount_selected_to_balance() {

        BigDecimal expected = new BigDecimal(5).setScale(2);
        BigDecimal actual = transactions.getBalance();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void balanceMessage_method_should_return_current_balance() {
        String expected = "Current Money Provided: $5.00";
        String actual = transactions.balanceMessage();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void dispenseChange_method_returns_correct_change_and_resets_balance_to_zero() {
        //ByteArrayOutputStream captures print statement to be used in assertion
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String expectedString = "\nYour change is 20 quarters, 0 dimes, and 0 nickels.\n";
        BigDecimal expectedBalance = new BigDecimal(0).setScale(2);
        transactions.dispenseChange();

        Assert.assertEquals(expectedString, outContent.toString());
        Assert.assertEquals(expectedBalance, transactions.getBalance());

        // Closed stream
        System.setOut(System.out);
    }

}
