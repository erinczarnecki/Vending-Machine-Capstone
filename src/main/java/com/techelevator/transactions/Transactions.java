package com.techelevator.transactions;

import java.math.BigDecimal;

public class Transactions {
    private final int QUARTER_VALUE = 25;
    private final int DIME_VALUE = 10;
    private final int NICKEL_VALUE = 5;
    private final int DOLLAR_TO_CENTS_CONVERSION = 100;

    private BigDecimal balance = new BigDecimal(0).setScale(2);

    public BigDecimal getBalance() {
        return balance.setScale(2);
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance.setScale(2);
    }

    public void feedMoney(BigDecimal moneyAdded) {
        BigDecimal currentBalance = new BigDecimal(String.valueOf(balance));
        balance = balance.add(moneyAdded);

        //Log purchase menu action (Feed Money) to the audit file
        TransactionLog.log("FEED MONEY: ", currentBalance, balance);
    }

    public String balanceMessage() {
        return "Current Money Provided: $" + balance.setScale(2);
    }

    //Triggered when Finish Transaction is selected in the Purchase Menu
    public void dispenseChange() {
        BigDecimal currentBalance = new BigDecimal(String.valueOf(balance));
        //Converting the BigDecimal into double allows for easier math
        double workingBalance = this.balance.doubleValue() * DOLLAR_TO_CENTS_CONVERSION;
        //To dispense the fewest coins, you work from the highest to the lowest denominations
        int quarters = (int) (workingBalance / QUARTER_VALUE);
        workingBalance -= (quarters * QUARTER_VALUE);
        int dimes = (int) (workingBalance / DIME_VALUE);
        workingBalance -= (dimes * DIME_VALUE);
        int nickels = (int) (workingBalance / NICKEL_VALUE);
        workingBalance -= (nickels * NICKEL_VALUE);
        //Converts the balance back to a BigDecimal for displaying as a $ value
        balance = BigDecimal.valueOf(workingBalance / DOLLAR_TO_CENTS_CONVERSION);

        //Log purchase menu action (Finish Transaction) to the audit file. Finish transaction will trigger GIVE CHANGE in audit
        TransactionLog.log("GIVE CHANGE: ", currentBalance, balance.setScale(2));

        System.out.println("\nYour change is " + quarters + " quarters, " + dimes + " dimes, and " + nickels + " nickels.");
    }

}
