package com.techelevator.inventory;

import com.techelevator.products.*;
import com.techelevator.transactions.Transactions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Formatter;
import java.util.LinkedHashMap;
import java.util.Map;


public class InventoryTests {
    private Inventory testingInventory;
    private Map<String, Product> testMap;
    private Transactions testTransactionHasMoney;
    private Transactions testTransactionHasNoMoney;

    @Before
    public void setup() {
        testingInventory = new Inventory();

        testMap = new LinkedHashMap<>();
        //Adds items to test map to use for expected values
        testMap.put("A1", new Chip("Potato Crisps", new BigDecimal(3.05)));
        testMap.put("A2", new Chip("Stackers", new BigDecimal(1.45)));
        testMap.put("A3", new Chip("Grain Waves", new BigDecimal(2.75)));
        testMap.put("A4", new Chip("Cloud Popcorn", new BigDecimal(3.65)));
        testMap.put("B1", new Candy("Moonpie", new BigDecimal(1.80)));
        testMap.put("B2", new Candy("Cowtales", new BigDecimal(1.50)));
        testMap.put("B3", new Candy("Wonka Bar", new BigDecimal(1.50)));
        testMap.put("B4", new Candy("Crunchie", new BigDecimal(1.75)));
        testMap.put("C1", new Drink("Cola", new BigDecimal(1.25)));
        testMap.put("C2", new Drink("Dr. Salt", new BigDecimal(1.50)));
        testMap.put("C3", new Drink("Mountain Melter", new BigDecimal(1.50)));
        testMap.put("C4", new Drink("Heavy", new BigDecimal(1.50)));
        testMap.put("D1", new Gum("U-Chews", new BigDecimal(0.85)));
        testMap.put("D2", new Gum("Little League Chew", new BigDecimal(0.95)));
        testMap.put("D3", new Gum("Chiclets", new BigDecimal(0.75)));
        testMap.put("D4", new Gum("Triplemint", new BigDecimal(0.75)));

        testTransactionHasMoney = new Transactions();
        //Adding money to balance to perform transactions
        testTransactionHasMoney.feedMoney(new BigDecimal(5));
        //Declaring Transaction with balance of 0 to test insufficient funds
        testTransactionHasNoMoney = new Transactions();

    }

    @After
    public void cleanup() {
    }


    @Test
    public void inventory_constructor_creates_inventory_map() {

        int expectedSize = 16;
        Map<String, Product> expectedItem = testMap;

        Assert.assertEquals(expectedSize, testingInventory.getProductInventory().size());
        Assert.assertEquals(expectedItem.keySet(), testingInventory.getProductInventory().keySet());

    }

    @Test
    public void show_inventory_method_should_show_all_inventory() {
        //Creating String with the same format to use for expected String result
        StringBuilder expectedStringBuilder = new StringBuilder();
        Formatter fm = new Formatter(expectedStringBuilder);

        for (Map.Entry<String, Product> item : testingInventory.getProductInventory().entrySet()) {
            String slotIdentifier = item.getKey();
            String itemName = item.getValue().getProductName();
            String itemPrice = item.getValue().getPrice().setScale(2).toString();
            int itemStock = item.getValue().getStock();
            //Using formatter to format the output so that each column is aligned and easier to read
            fm.format("%-3s %-21s %-7s %8s ", slotIdentifier, itemName, "$" + itemPrice, "Stock: " + ((itemStock < 1) ? "SOLD OUT" : itemStock));
            expectedStringBuilder.append("\n");
        }
        String expectedString = expectedStringBuilder.toString();
        fm.close();
        Assert.assertEquals(expectedString, testingInventory.showInventory());
    }

    @Test
    public void purchase_method_should_decrease_stock_of_item() {

        testingInventory.purchase("A1", testTransactionHasMoney);

        Assert.assertTrue(testingInventory.getProductInventory().get("A1").getStock() == 4);
    }

    @Test
    public void item_cannot_be_purchased_if_out_of_stock() {
        //ByteArrayOutputStream captures print statement to be used in assertion
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        //Setting stock amount to 0 to mimic sold out situation
        testingInventory.getProductInventory().get("A1").setStock(0);

        testingInventory.purchase("A1", testTransactionHasMoney);

        Assert.assertTrue(testingInventory.getProductInventory().get("A1").getStock() == 0);
        Assert.assertEquals("\nProduct OUT OF STOCK\n", outContent.toString());

        // Closed stream
        System.setOut(System.out);
    }

    @Test
    public void item_cannot_be_purchased_if_insufficient_funds() {
        //ByteArrayOutputStream captures print statement to be used in assertion
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        testingInventory.purchase("A1", testTransactionHasNoMoney);

        Assert.assertTrue(testingInventory.getProductInventory().get("A1").getStock() == 5);
        Assert.assertEquals("\nInsufficient funds. Please feed money.\n", outContent.toString());

        // Closed stream
        System.setOut(System.out);
    }

    @Test
    public void invalid_product_id_will_not_purchase_invalid_item() {
        //ByteArrayOutputStream captures print statement to be used in assertion
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        testingInventory.purchase("Z1", testTransactionHasMoney);

        Assert.assertEquals("\nProduct does not exist. Please enter a valid product ID.\n", outContent.toString());

        // Closed stream
        System.setOut(System.out);
    }

}

