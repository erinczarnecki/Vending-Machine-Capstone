package com.techelevator.inventory;

import com.techelevator.products.*;
import com.techelevator.transactions.TransactionLog;
import com.techelevator.transactions.Transactions;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Formatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Inventory {

    Map<String, Product> productInventory = new LinkedHashMap<>();

    public Map<String, Product> getProductInventory() {
        return productInventory;
    }

    public void setProductInventory(Map<String, Product> productInventory) {
        this.productInventory = productInventory;
    }

    //Using constructor to create Product Inventory map, using the Product class to handle changes to stock amount
    public Inventory() {
        File pathName = new File("vendingmachine.csv");

        try (Scanner scanner = new Scanner(pathName)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineArray = line.split("\\|");
                //Assigning each index of array to corresponding data type from input file
                String slotIdentifier = lineArray[0];
                String productName = lineArray[1];
                BigDecimal price = new BigDecimal(lineArray[2]);
                String productType = lineArray[3];

                //Checking product type of the line and creating corresponding object
                switch (productType) {

                    case "Chip":
                        productInventory.put(slotIdentifier, new Chip(productName, price));
                        break;

                    case "Candy":
                        productInventory.put(slotIdentifier, new Candy(productName, price));
                        break;

                    case "Drink":
                        productInventory.put(slotIdentifier, new Drink(productName, price));
                        break;

                    case "Gum":
                        productInventory.put(slotIdentifier, new Gum(productName, price));
                        break;
                }
            }

        } catch (FileNotFoundException ex) {
            ex.getMessage();
        }
    }

    //Using the showInventory for both the Main Menu and Purchase Menu options that display a list of the products
    public String showInventory() {
        StringBuilder output = new StringBuilder();
        Formatter fm = new Formatter(output);

        for (Map.Entry<String, Product> item : productInventory.entrySet()) {
            String slotIdentifier = item.getKey();
            String itemName = item.getValue().getProductName();
            String itemPrice = item.getValue().getPrice().setScale(2).toString();
            int itemStock = item.getValue().getStock();
            //Using formatter to format the output so that each column is aligned and easier to read
            fm.format("%-3s %-21s %-7s %8s ", slotIdentifier, itemName, "$" + itemPrice, "Stock: " + ((itemStock < 1) ? "SOLD OUT" : itemStock));
            output.append("\n");
        }
        fm.close();
        return output.toString();
    }

    public void purchase(String productSelection, Transactions transactions) {
        Product workingProduct = productInventory.get(productSelection);
        BigDecimal balance = transactions.getBalance();

        if (workingProduct == null) {
            System.out.println("\nProduct does not exist. Please enter a valid product ID.");
        } else if (workingProduct.getStock() < 1) {
            System.out.println("\nProduct OUT OF STOCK");
        } else {
            BigDecimal productPrice = productInventory.get(productSelection).getPrice();
            //BigDecimal.compareTo will return -1 if the product price is more than balance
            if (balance.compareTo(productPrice) >= 0) {
                BigDecimal endBalance = balance.subtract(productPrice);

                transactions.setBalance(endBalance);
                //Displays the item dispensed, cost of the selected item, the remaining balance, and the matching product type message
                System.out.println("\n" + workingProduct.getProductName() + " $" + productPrice);
                System.out.println("Money Remaining: $" + transactions.getBalance() + "\n");
                System.out.println(workingProduct.getMessage());
                workingProduct.setStock(workingProduct.getStock() - 1);
                //Logs transaction to the audit file
                TransactionLog.log(workingProduct.getProductName() + " " + productSelection + " ", balance, endBalance);

            } else {
                //Displays message if user doesn't have enough funds for purchase
                System.out.println("\nInsufficient funds. Please feed money.");
            }
        }
    }

}