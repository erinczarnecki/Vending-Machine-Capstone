package com.techelevator;

import com.techelevator.inventory.Inventory;
import com.techelevator.transactions.Transactions;
import com.techelevator.view.Menu;

import java.math.BigDecimal;
import java.util.Scanner;

public class VendingMachineCLI {
    // Main Menu
    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};

    // Purchase Menu
    private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
    private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
    private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
    private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

    // Feed Money Menu
    private static final String FEED_MONEY_OPTION_FEED_1_DOLLAR = "$1";
    private static final String FEED_MONEY_OPTION_FEED_2_DOLLARS = "$2";
    private static final String FEED_MONEY_OPTION_FEED_5_DOLLARS = "$5";
    private static final String FEED_MONEY_OPTION_FEED_10_DOLLARS = "$10";
    private static final String FEED_MONEY_OPTION_FEED_20_DOLLARS = "$20";
    private static final String FEED_MONEY_OPTION_FEED_50_DOLLARS = "$50";
    private static final String FEED_MONEY_OPTION_FEED_100_DOLLARS = "$100";
    private static final String[] FEED_MONEY_OPTIONS = {FEED_MONEY_OPTION_FEED_1_DOLLAR, FEED_MONEY_OPTION_FEED_2_DOLLARS, FEED_MONEY_OPTION_FEED_5_DOLLARS, FEED_MONEY_OPTION_FEED_10_DOLLARS, FEED_MONEY_OPTION_FEED_20_DOLLARS, FEED_MONEY_OPTION_FEED_50_DOLLARS, FEED_MONEY_OPTION_FEED_100_DOLLARS};

    private Menu menu;

    public VendingMachineCLI(Menu menu) {
        this.menu = menu;
    }

    public void run() {
        welcomeMessage();
        Inventory inventory = new Inventory();
        Transactions transactions = new Transactions();

        while (true) {

            String mainMenuChoice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

            if (mainMenuChoice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                // display vending machine items
                System.out.println(System.lineSeparator() + inventory.showInventory());
            } else if (mainMenuChoice.equals(MAIN_MENU_OPTION_PURCHASE)) {
                // do purchase
                while (true) {
                    String purchaseMenuChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS, transactions);

                    if (purchaseMenuChoice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
                        String feedMoneyChoice = (String) menu.getChoiceFromOptions(FEED_MONEY_OPTIONS, transactions);
                        //Uses the user input to call on Transaction class method feedMoney()
                        switch (feedMoneyChoice) {
                            case FEED_MONEY_OPTION_FEED_1_DOLLAR:
                                transactions.feedMoney(new BigDecimal(1));
                                break;
                            case FEED_MONEY_OPTION_FEED_2_DOLLARS:
                                transactions.feedMoney(new BigDecimal(2));
                                break;
                            case FEED_MONEY_OPTION_FEED_5_DOLLARS:
                                transactions.feedMoney(new BigDecimal(5));
                                break;
                            case FEED_MONEY_OPTION_FEED_10_DOLLARS:
                                transactions.feedMoney(new BigDecimal(10));
                                break;
                            case FEED_MONEY_OPTION_FEED_20_DOLLARS:
                                transactions.feedMoney(new BigDecimal(20));
                                break;
                            case FEED_MONEY_OPTION_FEED_50_DOLLARS:
                                transactions.feedMoney(new BigDecimal(50));
                                break;
                            case FEED_MONEY_OPTION_FEED_100_DOLLARS:
                                transactions.feedMoney(new BigDecimal(100));
                                break;
                        }

                    } else if (purchaseMenuChoice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
                        //Reads user input and makes purchase of corresponding product
                        System.out.println(System.lineSeparator() + inventory.showInventory());
                        System.out.print(System.lineSeparator() + "Please enter item code >>> ");
                        Scanner userInput = new Scanner(System.in);
                        String productSelection = userInput.nextLine();
                        inventory.purchase(productSelection.toUpperCase(), transactions);
                    } else if (purchaseMenuChoice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
                        //dispenses change and resets balance to 0
                        transactions.dispenseChange();
                        break;
                    }
                }
            } else if (mainMenuChoice.equals(MAIN_MENU_OPTION_EXIT)) {
                // exit program
                System.out.println(System.lineSeparator() + " <<< Exiting Program... >>> ");
                System.exit(0);
            }
        }
    }

    public void welcomeMessage() {
        System.out.println();
        System.out.println("Welcome to the Vendo-Matic 800!");
        System.out.println();
        System.out.println("⠀⠀⠀⠀⠀⠀⣿⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⠛⣿⡿⠿⢿⣿⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⣿⠀⠀⢠⣤⠀⠀⣴⠀⠀⠀⠀⠀⣿⠀⣶⠀⣿⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⣿⠀⠰⠾⠿⠶⠾⠿⠶⠶⠶⠶⠀⣿⣀⣉⣀⣿⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀⠀⢀⣤⡀⠀⠀⣿⣏⣉⣹⣿⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⣿⠀⠐⠒⠒⠒⠒⠒⠚⠛⠓⠒⠀⣿⣯⣉⣹⣿⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⣿⠀⠀⢠⡀⠀⣾⠀⠀⣶⡆⠀⠀⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⣿⠀⠘⠛⠛⠛⠛⠛⠛⠛⠛⠛⠀⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⣿⠀⠀⣶⣦⠀⣶⣶⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⣿⠀⠈⠉⠉⠉⠉⠉⠉⠉⠉⠉⠀⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣏⣉⣹⣿⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⣿⠶⠶⠶⠶⠶⠶⠶⠶⠶⠶⠶⠶⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⣿⠀⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⡄⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⣿⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀");
        System.out.println("\n**************************************************");
    }

    public static void main(String[] args) {
        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }
}