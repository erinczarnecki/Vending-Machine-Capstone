package com.techelevator.transactions;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionLog {

    public static void log(String transactionType, BigDecimal startBalance, BigDecimal endBalance) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream("Log.txt", true))) {
            //Pulls date and time from the user machine and formats to the required pattern
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
            //Log to the audit file with the date/time, transaction type, start balance, and end balance
            writer.append(">" + dateFormatter.format(currentTime) + " " + transactionType + "$" + startBalance.setScale(2) + " $" + endBalance.setScale(2) + "\n");
            writer.flush();
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
    }
}





